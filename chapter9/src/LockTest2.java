import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author husky
 * @date 2019/4/21 14:11
 */
public class LockTest2 {
    public static void main(String[] args) {
        BooleanLock lock = new BooleanLock();
        Stream.of("T1","T2","T3","T4","T5","T6").forEach(name->{
            new Thread(()->{
                try {
                    try {
                        lock.lock(1000);
                    } catch (Lock.TimeOutException e) {
                        Optional.of(Thread.currentThread().getName()+" time out").ifPresent(System.out::println);
                    }
                    work();
                    System.out.println(lock.getBlockedThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            },name).start();
        });
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
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
