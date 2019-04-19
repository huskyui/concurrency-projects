/**
 * @author husky
 * @date 2019/4/19 16:55
 */
public class ProducerConsumerVersion1 {
    private int i = 1;

    final private Object LOCK = new Object();

    private void produce(){
        synchronized (LOCK){
            System.out.println("p->" + (i++));
        }
    }

    private void consume(){
        synchronized (LOCK){
            System.out.println("c->" + i);
        }
    }

    public static void main(String[] args) {
        ProducerConsumerVersion1 pc = new ProducerConsumerVersion1();
        new Thread("P"){
            @Override
            public void run() {
                while(true){
                    pc.produce();
                }
            }
        }.start();

        new Thread("C"){
            @Override
            public void run() {
                while (true) {
                    pc.consume();
                }
            }
        }.start();
    }
}
