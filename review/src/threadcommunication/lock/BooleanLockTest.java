package threadcommunication.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @author husky
 * @date 2019/7/17 11:21
 */
public class BooleanLockTest {


    /**
     * @param lock
     */
    private final Lock lock = new BooleanLock();

    public void syncMethod() {
        //加锁
        try{
            lock.lock();
            int randomInt = current().nextInt(10);
            System.out.println(currentThread() + " get the lock");
            TimeUnit.SECONDS.sleep(randomInt);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void syncMethodTimeoutable() {
        //加锁
        try{
            lock.lock(1000);
            int randomInt = current().nextInt(10);
            System.out.println(currentThread() + " get the lock");
            TimeUnit.SECONDS.sleep(randomInt);
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (TimeoutException e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BooleanLockTest blt = new BooleanLockTest();
        IntStream.range(0,10)
                .mapToObj(i->new Thread(blt::syncMethod))
                .forEach(Thread::start);
    }
}
