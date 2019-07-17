package threadcommunication.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author husky
 * @date 2019/7/17 11:56
 */
public class BooleanLockTest3 {


    public static void main(String[] args) throws InterruptedException {
        BooleanLockTest blt = new BooleanLockTest();
        new Thread(blt::syncMethodTimeoutable,"T1").start();
        TimeUnit.MICROSECONDS.sleep(2);
        Thread t2 = new Thread(blt::syncMethodTimeoutable,"T2");
        t2.start();
        TimeUnit.MICROSECONDS.sleep(10);
    }
}
