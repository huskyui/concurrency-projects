package chapter2;

/**
 * @author husky
 * @date 2019/7/4 22:41
 */
public class WaitSet2 {
    private static final Object lock = new Object();

    public static void work(){
        System.out.println("begin.....");
        synchronized (lock){
            try {
                System.out.println("thread beging....");
                lock.wait();
                System.out.println("thread ending....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                work();
            }
        }.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock){
            lock.notify();
        }
    }
}
