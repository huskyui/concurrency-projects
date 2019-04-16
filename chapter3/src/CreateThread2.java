/**
 * @author husky
 * @date 2019/4/16 10:52
 */
public class CreateThread2 {
    private static int counter = 1;

    public static void main(String[] args) {
        for (int i = 0;i < Integer.MAX_VALUE ; i++){
            new Thread(()->{
                Byte[] byteArray = new Byte[1024*1000];
                while(true){

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
