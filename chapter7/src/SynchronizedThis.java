/**
 * 运行时，可以证明this锁的存在
 * @author husky
 * @date 2019/4/18 16:52
 */
public class SynchronizedThis {
    public static void main(String[] args) {
        ThisLock thisLock = new ThisLock();

        Thread t1 = new Thread(()->{
            thisLock.m1();
        },"t1");
        Thread t2 = new Thread(()->{
            thisLock.m2();
        },"t2");
        t1.start();
        t2.start();
    }
}

class ThisLock{
    public synchronized void m1(){
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void m2(){
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
