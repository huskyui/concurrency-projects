package threadapi;

import java.util.concurrent.TimeUnit;

/**
 * @author husky
 * @date 2019/7/10 17:17
 */
public class ThreadInterrupted4 {
    public static void main(String[] args) {
//        //判断当前线程是否被中断
//        System.out.println("Main thread is interrupted? " + Thread.interrupted());
//        //中断当前线程
//        Thread.currentThread().interrupt();
//        //判断当前线程是否被中断
//        System.out.println("Main thread is interrupted? " + Thread.currentThread().isInterrupted());
        //判断当前线程是否被中断
        System.out.println("Main thread is interrupted? " + Thread.currentThread().isInterrupted());
        //中断当前线程
        Thread.currentThread().interrupt();
        //判断当前线程是否被中断
        System.out.println("Main thread is interrupted? " + Thread.interrupted());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("I will be interrupted still");
        }

    }
}
