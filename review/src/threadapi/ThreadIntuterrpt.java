package threadapi;

import java.util.concurrent.TimeUnit;

/**
 * @author husky
 * @date 2019/7/10 16:25
 */
public class ThreadIntuterrpt {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("oh i am be interrupted");
            }
        });
        thread.start();
        //short block and make sure thread is started
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
