import java.sql.Time;

/**
 * @author husky
 * @date 2019/4/16 16:39
 */
public class ThreadJoin3 {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(new CaptureRunnable(10000L,"mac1"));
        Thread t2 = new Thread(new CaptureRunnable(15000L,"mac2"));
        Thread t3 = new Thread(new CaptureRunnable(9000L,"mac3"));
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        long end = System.currentTimeMillis();
        System.out.println("all tasks finished spend"+(end-start));


    }


}

class CaptureRunnable implements Runnable{
    private long spendTime;
    private String machineName;

    public CaptureRunnable(long spendTime, String machineName) {
        this.spendTime = spendTime;
        this.machineName = machineName;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(spendTime);
            System.out.println(machineName+"finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}