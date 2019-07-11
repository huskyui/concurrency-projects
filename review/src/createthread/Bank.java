package createthread;

/**
 * @author husky
 * @date 2019/7/10 11:42
 */
public class Bank {
    public static void main(String[] args) {
        final WorkRunnable workRunnable = new WorkRunnable();
        Thread thread1 = new Thread(workRunnable,"窗口一");
        Thread thread2 = new Thread(workRunnable,"窗口二");
        Thread thread3 = new Thread(workRunnable,"窗口三");
        Thread thread4 = new Thread(workRunnable,"窗口四");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
