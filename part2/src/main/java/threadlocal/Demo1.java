package threadlocal;

import java.util.Optional;

/**
 * @author husky
 * @date 2019/7/8 17:37
 */
public class Demo1 {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("huskyui");
        Thread t1 = new Thread("thread-1"){
            @Override
            public void run() {
                Optional.of("CurrentThreadName: " + Thread.currentThread().getName() + " " + threadLocal.get())
                        .ifPresent(System.out::println);
            }
        };
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Optional.of("CurrentThreadName: " + Thread.currentThread().getName() + " " + threadLocal.get())
                .ifPresent(System.out::println);
        Optional.of("CurrentThreadName: " + Thread.currentThread().getName() + " " + threadLocal.get())
                .ifPresent(System.out::println);
        threadLocal.remove();
        Optional.of("CurrentThreadName: " + Thread.currentThread().getName() + " " + threadLocal.get())
                .ifPresent(System.out::println);
    }
}
