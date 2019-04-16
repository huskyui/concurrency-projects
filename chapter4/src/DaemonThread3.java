/**
 * 线程的子线程为守护线程，子线程结束时，线程不会退出
 * @author husky
 * @date 2019/4/16 12:00
 */
public class DaemonThread3 {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            Thread t2 = new Thread(()->{
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t2.setDaemon(true);
            t2.start();

            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        });
        t.start();
    }


}
