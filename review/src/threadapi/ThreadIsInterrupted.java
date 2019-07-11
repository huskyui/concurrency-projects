package threadapi;

import java.util.concurrent.TimeUnit;

/**
 * @author husky
 * @date 2019/7/10 16:31
 */
public class ThreadIsInterrupted {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true){
                    //do nothibf,just empty loop
                }
            }
        };
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread is interrupted ? %s\n",thread.isInterrupted());
        thread.interrupt();
        System.out.printf("Thread is interrupted ? %s\n",thread.isInterrupted());
    }
}
