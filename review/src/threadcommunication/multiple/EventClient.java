package threadcommunication.multiple;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author husky
 * @date 2019/7/12 14:19
 */
public class EventClient {
    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();
        IntStream.rangeClosed(0,2).mapToObj(i->new Thread("producer-"+i){
            @Override
            public void run() {
                for(;;) {
                    eventQueue.offer(new EventQueue.Event());
                }
            }
        }).forEach(Thread::start);

        IntStream.rangeClosed(0,3).mapToObj(i->new Thread("consumer-"+i){
            @Override
            public void run() {
                for(;;){
                    eventQueue.take();
                    try {
                        TimeUnit.MICROSECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).forEach(Thread::start);
    }
}
