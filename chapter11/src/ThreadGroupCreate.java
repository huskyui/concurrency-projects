/**
 * @author husky
 * @date 2019/4/25 10:40
 */
public class ThreadGroupCreate {
    public static void main(String[] args) {
//        System.out.println(Thread.currentThread().getName());
//        System.out.println(Thread.currentThread().getThreadGroup().getName());
        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1,"t1"){
            @Override
            public void run() {
                while(true){
                    try {
                        System.out.println(Thread.currentThread().getThreadGroup());
                        System.out.println(Thread.currentThread().getThreadGroup().getParent());
                        Thread.sleep(10_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();

    }
}
