package future;

import java.util.concurrent.*;

/**
 * @author husky
 * @date 2019/6/21 11:44
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Task task = new Task();
        Future<Integer> result = executor.submit(task);
        executor.shutdown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程在执行方法");
        try {
            System.out.println("task运行结果" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("执行完");


    }




}
