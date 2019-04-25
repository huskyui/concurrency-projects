/**
 * @author husky
 * @date 2019/4/21 16:01
 */
public class ThreadException {

    public static void main(String[] args) {
        int a  = 10;
        int b = 0;
        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int result = a / b;
            System.out.println(result);
        });
        t1.setUncaughtExceptionHandler((t,e)->{
            System.out.println(t.getName());
            System.out.println(e.getMessage());
        });
        t1.start();
    }
}
