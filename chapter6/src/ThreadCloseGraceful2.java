/**
 * @author husky
 * @date 2019/4/17 17:45
 */
public class ThreadCloseGraceful2 {

    private static class WorkderThread extends Thread{
        @Override
        public void run() {
            while(true){
                if(Thread.interrupted()){
                    break;
                }
                //----------------dosomething
            }
        }
    }

    public static void main(String[] args) {
        WorkderThread workderThread = new WorkderThread();
        workderThread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        workderThread.interrupt();
    }
}
