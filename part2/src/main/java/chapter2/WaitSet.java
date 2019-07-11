package chapter2;


import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.stream.IntStream;

/**
 * @author husky
 * @date 2019/5/8 17:58
 */
public class WaitSet {
    private static final Object lock = new Object();

    /**
     * 1.所有线程都会有一个wait set,用于存放调用该对象的wait方法之后进步block阻塞状态线程
     * 2.线程被notify之后,不一定立即得到执行
     * 3.wait set中唤醒顺序并不是先进先出的
     * */
    public static void main(String[] args) {

        IntStream.rangeClosed(1,10).forEach(i->{
            new Thread(String.valueOf(i)){
                @Override
                public void run() {
                    synchronized (lock) {
                        try {
                            Optional.of(Thread.currentThread().getName()+"will come to wait set")
                                    .ifPresent(System.out::println);
                            lock.wait();
                            Optional.of(Thread.currentThread().getName()+"will leave wait set")
                                    .ifPresent(System.out::println);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }.start();
        });

        IntStream.rangeClosed(1,10).forEach(i->{
            synchronized (lock){
                lock.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
