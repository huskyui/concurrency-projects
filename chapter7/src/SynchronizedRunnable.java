/**
 * @author husky
 * @date 2019/4/18 11:05
 */
public class SynchronizedRunnable  implements Runnable{
    private int index = 1;
    //readonly data
    private final static int MAX = 500;
    @Override
    public void run() {
        while(true){
            if(ticket()){
                break;
            }
        }
    }

    private synchronized boolean ticket(){
        //1.getField
        if(index > MAX){
            return false;
        }
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //index++ =>index = index+1
        //1.get Field index
        //2.index = index + 1
        //3.put field index

        System.out.println(Thread.currentThread().getName()+"---"+(index++));
        return false;
    }
}
