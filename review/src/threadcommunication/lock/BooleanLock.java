package threadcommunication.lock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

/**
 * @author husky
 * @date 2019/7/17 10:18
 */
public class BooleanLock implements Lock {
    /**
     * currentThread: 当前线程
     * */
    private Thread currentThread;

    /**
     * locked false: 当前没有被任何线程获取,或者已经被释放,
     *        true: 表示当前某个线程获取
     * */
    private boolean locked = false;

    /**
     * blockThreadList 存储阻塞的线程列表
     */
    private final List<Thread> blockedThreadList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this){
            while (locked){
                final Thread tempThread = currentThread();
                try {
                    if (!blockedThreadList.contains(currentThread())) {
                        blockedThreadList.add(currentThread());
                    }
                    this.wait();
                }catch (InterruptedException e){
                    blockedThreadList.remove(tempThread);
                    throw e;
                }
            }
            blockedThreadList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException,TimeoutException {
        synchronized (this){
            if(mills <= 0){
                this.lock();
            }else{
                long remainingMills = mills;
                long endMills = currentTimeMillis() + remainingMills;
                while(locked){
                    if (remainingMills <= 0){
                        throw new TimeoutException("can not get the lock during " + mills + " ms.");
                    }
                    final Thread tempThread = currentThread();
                    try {
                        if (!blockedThreadList.contains(currentThread())) {
                            blockedThreadList.add(currentThread);
                        }
                        this.wait(remainingMills);
                        remainingMills = endMills - currentTimeMillis();
                    }catch (InterruptedException e){
                        blockedThreadList.remove(tempThread);
                        throw e;
                    }
                }
                blockedThreadList.remove(currentThread());
                this.locked = true;
                this.currentThread = currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this){
            if(currentThread == currentThread()){
                this.locked = false;
                Optional.of(currentThread().getName() + "release the lock.")
                        .ifPresent(System.out::println);
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedThreadList);
    }
}
