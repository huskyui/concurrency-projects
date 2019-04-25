package executors;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author husky
 * @date 2019/4/25 22:00
 */
public class ThreadPoolTask implements Runnable, Serializable {
    private Object attachData;

    ThreadPoolTask(Object attachData){
        this.attachData = attachData;
    }

    @Override
    public void run() {
        System.out.println("开始执行任务: "+attachData+" 使用的是线程池,线程名称为: "+Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("完成任务: "+attachData+" 使用线程池,线程名称: " + Thread.currentThread().getName());
    }
}
