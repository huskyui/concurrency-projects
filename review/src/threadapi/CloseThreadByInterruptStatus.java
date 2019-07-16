package threadapi;

import java.util.concurrent.TimeUnit;

/**
 * @author husky
 * @date 2019/7/11 11:16
 */
public class CloseThreadByInterruptStatus {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                while(!isInterrupted()){
                    //do something
                }
            }
        };
        t1.start();
        // ensure thread is running
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Thread will be closed");
        t1.interrupt();

    }
}
