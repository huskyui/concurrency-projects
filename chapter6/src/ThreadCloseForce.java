/**
 * @author husky
 * @date 2019/4/17 21:08
 */
public class ThreadCloseForce {
    public static void main(String[] args) {
        ThreadService threadService = new ThreadService();
        long start = System.currentTimeMillis();
        threadService.execute(()->{
//            while(true){
//                //加载的东西特别大
//
//            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadService.shutdown(10000);
        System.out.println("历时："+(System.currentTimeMillis()-start));
    }

}
