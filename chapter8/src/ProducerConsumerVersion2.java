/**
 * @author husky
 * @date 2019/4/19 18:35
 */
public class ProducerConsumerVersion2 {
    private int i = 0;

    final private Object LOCK = new Object();

    private volatile boolean isProduced = false;

    private void produce(){
        synchronized (LOCK){
            //已生产
            if(isProduced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                i++;
                System.out.println("p->"+i);
                LOCK.notify();
                isProduced = true;
            }
        }
    }

    private void consume(){
        synchronized (LOCK){
            if(isProduced){
                System.out.println("c->"+i);
                LOCK.notify();
                isProduced = false;
            }else{
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumerVersion2 pc = new ProducerConsumerVersion2();
        new Thread("produce"){
            @Override
            public void run() {
                while(true){
                    pc.produce();
                }
            }
        }.start();

        new Thread("consume"){
            @Override
            public void run() {
                while(true) {
                    pc.consume();
                }
            }
        }.start();
    }

}
