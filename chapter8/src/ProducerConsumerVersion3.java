import java.util.stream.Stream;

/**
 * @author husky
 * @date 2019/4/19 20:20
 */
public class ProducerConsumerVersion3 {
    private int i = 1;

    final private Object LOCK = new Object();

    private volatile boolean isProduced = false;

    private void produce(){
        synchronized (LOCK){
            while(isProduced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            System.out.println("p->"+i+"----------"+Thread.currentThread().getName());
            LOCK.notifyAll();
            isProduced = true;
        }
    }

    private void consume(){
        synchronized (LOCK){
            while(!isProduced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("c->"+i+"--------"+Thread.currentThread().getName());
            LOCK.notifyAll();
            isProduced = false;
        }
    }

    public static void main(String[] args) {
        ProducerConsumerVersion3 pc = new ProducerConsumerVersion3();
        Stream.of("p1","p2").forEach((n)->{
            new Thread(n){
                @Override
                public void run() {
                    while(true){
                        pc.produce();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        });

        Stream.of("c1","c2").forEach((n)->{
            new Thread(n){
                @Override
                public void run() {
                    while(true){
                        pc.consume();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        });
    }

}
