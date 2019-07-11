package createthread;

import java.util.concurrent.TimeUnit;

/**
 * @author husky
 * @date 2019/7/10 11:38
 */
public class WorkRunnable implements Runnable {
    private static int index;
    private static final int MAX = 50;

    @Override
    public void run() {
        while(index<=MAX){
            System.out.println(Thread.currentThread().getName()+" sell ticket " + (index++) );
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
