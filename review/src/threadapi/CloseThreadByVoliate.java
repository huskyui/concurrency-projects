package threadapi;

import java.util.concurrent.TimeUnit;

/**
 * @author husky
 * @date 2019/7/11 11:39
 */
public class CloseThreadByVoliate {
    static class WorkThread extends Thread{
        private volatile boolean flag = false;
        @Override
        public void run() {
            System.out.println("i will start work");
            while(!flag&&!isInterrupted()){
                //正在执行
            }
            System.out.println("i will be exiting");
        }

        public void close(){
            this.flag = true;
            this.interrupt();
        }
    }

    public static void main(String[] args) {
        WorkThread workThread = new WorkThread();
        workThread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("System will be interrupt");
        workThread.close();
    }


}
