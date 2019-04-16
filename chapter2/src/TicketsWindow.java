/**
 * @author husky
 * @date 2019/4/5 16:16
 */
public class TicketsWindow extends Thread{
    private final String name;
    private final int MAX = 50;
    private static int index = 1;

    public TicketsWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while(index <= MAX){
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前柜台:"+name+"当前的号码是:"+(index++));
        }
    }
}
