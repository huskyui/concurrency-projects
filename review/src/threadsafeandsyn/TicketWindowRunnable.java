package threadsafeandsyn;

import java.util.concurrent.TimeUnit;

/**
 * @author husky
 * @date 2019/7/11 14:14
 */
public class TicketWindowRunnable implements Runnable {
    private int index;
    private static final int MAX = 500;
    private final static Object MENTX = new Object();
    @Override
    public void run() {
        synchronized (MENTX) {
            while (index <= MAX) {
                System.out.println(Thread.currentThread().getName() + " sell ticket " + index++);
            }
        }
    }

    public static void main(String[] args) {
        final TicketWindowRunnable ticketWindowRunnable = new TicketWindowRunnable();
        Thread thread = new Thread(ticketWindowRunnable,"一号");
        Thread thread2 = new Thread(ticketWindowRunnable,"二号");
        Thread thread3 = new Thread(ticketWindowRunnable,"三号");
        Thread thread4 = new Thread(ticketWindowRunnable,"4号");
        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
