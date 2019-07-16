package threadcommunication.single;

import java.util.concurrent.TimeUnit;

/**
 * @author husky
 * @date 2019/7/12 11:44
 */
public class EventClient {
    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();
        new Thread(()->{
            for(;;){
                eventQueue.offer(new EventQueue.Event());
            }
        },"producer").start();

        new Thread(()->{
            for(;;){
                eventQueue.take();
                //模拟消费event消耗时间
                try {
                    TimeUnit.MICROSECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"consumer").start();
    }
}
