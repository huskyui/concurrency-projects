import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author husky
 * @date 2019/4/20 17:47
 */
public class BooleanLock implements Lock {
    //the initvalue is false indicated the lock is free( other Thread can get this)
    private boolean initValue;
    private Thread currentThread;

    private Collection<Thread>  blockedThreadCollections = new ArrayList<>();

    @Override
    public synchronized void lock() throws InterruptedException {
        while(initValue){
            blockedThreadCollections.add(Thread.currentThread());
            this.wait();
        }
        System.out.println(Thread.currentThread().getName()+" get the lock");
        blockedThreadCollections.remove(Thread.currentThread());
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if(mills<=0){
            lock();
        }
        long endTime = System.currentTimeMillis()+mills;
        long hasRemaining = mills;
        while(initValue){
            System.out.println("CurrentThread"+Thread.currentThread().getName()+" has remaining "+hasRemaining);
            if(hasRemaining <= 0){
                throw new TimeOutException("Time out");
            }
            blockedThreadCollections.add(Thread.currentThread());
            this.wait(hasRemaining);
            hasRemaining = endTime - System.currentTimeMillis();
        }
        System.out.println(Thread.currentThread().getName()+" get the lock");
        blockedThreadCollections.remove(Thread.currentThread());
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {
        if(currentThread==Thread.currentThread()) {
            this.initValue = false;
            System.out.println(Thread.currentThread().getName() + " release the Thread");
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedThreadCollections);
    }

    @Override
    public int getBlockSize() {
        return blockedThreadCollections.size();
    }
}
