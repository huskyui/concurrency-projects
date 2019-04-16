import java.util.stream.IntStream;

/**
 * @author husky
 * @date 2019/4/16 16:08
 */
public class ThreadJoin2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+" is running");
                Thread.sleep(100_000);
                System.out.println(Thread.currentThread().getName()+" is done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t1.join(100);

        System.out.println("all tasks done");
        IntStream.range(1,1000).forEach(i->{
            System.out.println(i+"---"+Thread.currentThread().getName());
        });
    }
}
