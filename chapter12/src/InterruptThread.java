import java.util.Date;

import static java.lang.Thread.interrupted;

/**
 * @author husky
 * @date 2019/4/29 21:34
 */
public class InterruptThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
           while(true){
               if (interrupted()) {
                   break;
               }
               try {
                   Thread.sleep(100);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

               System.out.println("i" + new Date());
           }
        });
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
    }
}
