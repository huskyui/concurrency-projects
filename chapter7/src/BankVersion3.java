/**
 * @author husky
 * @date 2019/4/18 11:04
 */
public class BankVersion3 {
    public static void main(String[] args) {
        SynchronizedRunnable bankRunnable = new SynchronizedRunnable();
        Thread window1 = new Thread(bankRunnable,"一");
        Thread window2 = new Thread(bankRunnable,"二");
        Thread window3 = new Thread(bankRunnable,"三");
        window1.start();
        window2.start();
        window3.start();

    }
}
