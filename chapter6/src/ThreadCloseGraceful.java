/**
 * @author husky
 * @date 2019/4/17 17:33
 */
public class ThreadCloseGraceful {

    private static class WorkerThread extends Thread{
        private volatile boolean flag = true;
        @Override
        public void run() {
            while(flag){

            }
        }

        public void shutdown(){
            this.flag = false;
        }
    }

    public static void main(String[] args) {
        WorkerThread workerThread = new WorkerThread();
        workerThread.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        workerThread.shutdown();

    }
}
