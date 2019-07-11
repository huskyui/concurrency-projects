package threadlocal;

/**
 * @author husky
 * @date 2019/7/8 19:59
 */
public class Demo2 {
    public static class MyRunnable implements Runnable {

        private ThreadLocal threadLocal = new ThreadLocal();

        @Override
        public void run() {
            System.out.println(threadLocal.get());
            threadLocal.set((int) (Math.random() * 100D));
            System.out.println(threadLocal.get());
        }
    }

    public static void main(String[] args) {
        MyRunnable sharedRunnableInstance = new MyRunnable();
        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);
        thread1.start();
        thread2.start();
    }

}
