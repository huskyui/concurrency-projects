package executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author husky
 * @date 2019/4/25 21:59
 */
public class TestThreadPoolExector {
    public static void main(String[] args) {
        long currentTimeMills = System.currentTimeMillis();


        //创建一个线程
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5,7,3,
                TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(5));
        for(int i = 0;i <= 10;i++){
            String task = "task= "+ i;
            System.out.println("创建任务并提交线程池中: "+task);
            threadPool.execute(new ThreadPoolTask(task));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try{
            //等待所有线程执行完毕当前任务
            threadPool.shutdown();
            boolean loop  = true;
            do{
                //等待两秒
                loop = !threadPool.awaitTermination(2,TimeUnit.SECONDS);
            }while(loop);

        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("耗时: "+(System.currentTimeMillis()-currentTimeMills));
        }


    }
}
