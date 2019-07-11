package threadapi;

import java.util.concurrent.TimeUnit;

/**
 * @author husky
 * @date 2019/7/10 17:06
 */
public class ThreadInterrupted3 {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true){
                    System.out.println(Thread.interrupted());
                }
            }
        };
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
