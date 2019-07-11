package threadapi;

import java.util.concurrent.TimeUnit;

/**
 * @author husky
 * @date 2019/7/11 11:23
 */
public class CloseThreadByInterruptException {
    public static void main(String[] args) {
        Thread workThread = new Thread(){
            @Override
            public void run() {
                System.out.println("i will start work");
                for(;;){
                    //working
                    //使用时间模拟工作
                    long start = System.currentTimeMillis();
                    long end = start + 1000 * 5;
                    while(System.currentTimeMillis() <= end){
                        //do nothing
                    }
                    try{
                        TimeUnit.MICROSECONDS.sleep(1);
                    }catch (InterruptedException e){
                        break;
                    }
                }
                System.out.println("i will be exiting");
            }
        };
        workThread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("work thread will be closed");
        workThread.interrupt();
    }
}
