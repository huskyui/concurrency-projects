/**
 * @author husky
 * @date 2019/4/17 21:29
 */
public class BankRunnable implements Runnable{
    private int index = 1;
    private int MAX = 500;
    private final Object Monitor = new Object();

    @Override
    public void run() {
        while(true){
            synchronized (Monitor) {
                if (index > MAX) {
                    break;
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("柜台" + Thread.currentThread().getName() + "出售第" + (index++));
            }
        }
    }
}
