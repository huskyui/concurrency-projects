/**
 * @author husky
 * @date 2019/4/17 21:31
 */
public class BankVersion2 {

    public static void main(String[] args) {
        BankRunnable bankRunnable = new BankRunnable();
        Thread window1 = new Thread(bankRunnable,"一");
        Thread window2 = new Thread(bankRunnable,"二");
        Thread window3 = new Thread(bankRunnable,"三");
        window1.start();
        window2.start();
        window3.start();

    }
}
