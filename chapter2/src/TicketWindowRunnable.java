/**
 * @author husky
 * @date 2019/4/5 17:01
 */
public class TicketWindowRunnable implements Runnable{
    private int index = 1;
    private final static int MAX = 50;

    @Override
    public void run() {
        while(index<=MAX){
            System.out.println(Thread.currentThread()+"的号码是"+index++);
        }
    }
}
