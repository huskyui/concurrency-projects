package threadsafeandsyn;

import java.util.concurrent.TimeUnit;

/**
 * @author husky
 * @date 2019/7/11 14:54
 */
public class ThisMonitor {
    public synchronized void method1(){
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void method2(){
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void method3(){
        synchronized (this){
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
        ThisMonitor thisMonitor = new ThisMonitor();
        new Thread(thisMonitor::method1,"t1").start();
        new Thread(thisMonitor::method3,"t3").start();
    }

}
