package threadapi;

import java.util.concurrent.TimeUnit;

/**
 * @author husky
 * @date 2019/7/10 16:47
 */
public class ThreadIsInterrupted2 {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("i am interrupted?"+isInterrupted());
                }
            }
        };
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread is interrupted? :%s\n",thread.isInterrupted());
        thread.interrupt();
        System.out.printf("Thread is interrupted? :%s\n",thread.isInterrupted());
    }
}
