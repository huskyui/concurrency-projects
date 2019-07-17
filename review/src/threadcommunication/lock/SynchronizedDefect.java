package threadcommunication.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author husky
 * @date 2019/7/16 18:29
 */
public class SynchronizedDefect {
    /**
     * this lock
     * */
    public synchronized void syncMethod(){
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedDefect synchronizedDefect = new SynchronizedDefect();
        Thread t1  = new Thread(synchronizedDefect::syncMethod,"t1");
        t1.start();
        /**
         * make sure thread t1 start
         * */
        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(synchronizedDefect::syncMethod,"t2");
        t2.start();
        /**
         * make sure thread t2 start
         * */
        TimeUnit.SECONDS.sleep(1);
        t2.interrupt();
        System.out.println(t2.isInterrupted());
        System.out.println(t2.getState());

    }
}
