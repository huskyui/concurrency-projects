import java.util.Date;

/**
 * @author husky
 * @date 2019/4/17 16:43
 */
public class ThreadInterrupt {
    private static final Object MONITOR = new Object();
    public static void main(String[] args) throws InterruptedException {
//        Thread t = new Thread(){
//            @Override
//            public void run() {
//                while(true){
//                    synchronized (MONITOR){
//                        try {
//                            MONITOR.wait(10);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        };
        Thread t = new Thread(()->{
            while(true){
                synchronized(MONITOR){
                    try {
                        MONITOR.wait(100);
                        System.out.println(new Date());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(Thread.interrupted());
                    }
                }
            }
        });
        t.start();
        Thread.sleep(1_000);
        System.out.println(t.isInterrupted());
        t.interrupt();
        System.out.println(t.isInterrupted());
    }

}
