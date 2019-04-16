/**
 * @author husky
 * @date 2019/4/16 17:20
 */
public class JoinTest2 {
    public static void main(String[] args) {
        Thread boyThread  = new BoyTread();
        boyThread.start();
    }

    static class BoyTread extends Thread{
        @Override
        public void run() {
            System.out.println("男孩女孩准备去逛街");
            Thread girlThread  = new GirlThread();
            girlThread.start();
            try {
                girlThread.join(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("男孩等待2s，发现没来，决定一个人去逛街");
        }
    }

    static class GirlThread extends Thread{
        @Override
        public void run() {
            System.out.println("女孩开始化妆");
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("女孩化妆完成");
        }
    }
}
