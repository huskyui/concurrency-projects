package threadapi;

import java.util.concurrent.TimeUnit;

/**
 * @author husky
 * @date 2019/7/10 16:01
 */
public class ThreadSleep {
    public static void main(String[] args) {
        new Thread(()->{
            long starttime = System.currentTimeMillis();
            sleep(2);
            long endtime = System.currentTimeMillis();
            System.out.println(String.format("Total spend %d ms",(endtime-starttime)));
        }).start();
        long starttime = System.currentTimeMillis();
        sleep(3);
        long endtime = System.currentTimeMillis();
        System.out.println(String.format("Main Thread Total spend %d ms",(endtime-starttime)));
    }

    private static void sleep(long ms){
        try {
            TimeUnit.SECONDS.sleep(ms);
        }catch (InterruptedException e){

        }
    }
}
