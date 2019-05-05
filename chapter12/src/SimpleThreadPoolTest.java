import java.util.stream.IntStream;

/**
 * @author husky
 * @date 2019/4/25 15:57
 */
public class SimpleThreadPoolTest {
    public static void main(String[] args) {
        SimpleThreadPool pool = new SimpleThreadPool();


        for(int i = 0;i < 40;i++){
            Thread t1=  new Thread(()->{
                System.out.println("The runnable be serviced by"+Thread.currentThread()+" started");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The runnable be serviced by"+Thread.currentThread()+" finished");
            });
            pool.submit(t1);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            pool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        pool.submit(()->{
//            System.out.println("new Task");
//        });



    }
}
