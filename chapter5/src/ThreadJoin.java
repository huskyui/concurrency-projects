import java.util.stream.IntStream;

/**
 * join 会让两个线程并发执行但是main线程是最后执行
 * @author husky
 * @date 2019/4/16 15:58
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            IntStream.range(1,1000)
                    .forEach(i-> System.out.println(Thread.currentThread().getName()+"---index---"+i));
        });
        Thread t2 = new Thread(()->{
            IntStream.range(1,1000)
                    .forEach(i-> System.out.println(Thread.currentThread().getName()+"---index---"+i));
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        IntStream.range(1,1000)
                .forEach(i-> System.out.println(Thread.currentThread().getName()+"---index---"+i));

    }
}
