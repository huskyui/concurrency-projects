/**
 * @author husky
 * @date 2019/4/20 10:34
 */
public class DifferenceOfWaitAndSleep {
    private final static Object MONITOR = new Object();

    public static void main(String[] args) {
        m2();
    }

    public static void m1(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void m2(){
        synchronized (MONITOR) {
            try {
                MONITOR.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sleepNotReleaseMonitor(){
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
