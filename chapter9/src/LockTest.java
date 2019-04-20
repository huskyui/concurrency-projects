import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * @author husky
 * @date 2019/4/20 17:58
 */
public class LockTest {

    public static void main(String[] args) {


        BooleanLock lock = new BooleanLock();
        Stream.of("T1","T2","T3","T4","T5","T6").forEach(name->{
            new Thread(()->{
                try {
                    lock.lock();
                    work();
                    System.out.println(lock.getBlockedThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            },name).start();
        });
    }

    private static void work(){
        System.out.println(Thread.currentThread().getName()+" is working");
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
