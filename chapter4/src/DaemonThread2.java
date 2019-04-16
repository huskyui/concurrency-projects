/**
 * 创建一个线程，该线程为守护线程，该线程结束时，子线程也会结束
 * @author husky
 * @date 2019/4/16 11:54
 */
public class DaemonThread2 {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            new Thread(()->{
                try {
                    Thread.sleep(1000L*100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.setDaemon(true);
        t.start();
        System.out.println(Thread.currentThread().getName());
    }
}
