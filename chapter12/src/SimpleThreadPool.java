import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author husky
 * @date 2019/4/25 15:20
 */
public class SimpleThreadPool {

    private final int size;

    private final int queueSize;

    private final DiscardPolicy discardPolicy;

    private final static int DEFAULT_SIZE = 10;

    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;

    public volatile  boolean destory = false;

    public boolean isDestory(){
        return this.destory;
    }

    public final static DiscardPolicy DEFAULT_DISCARD_POLICY = ()->{
      throw new DiscardException("Discard this task.");
    };

    private static volatile int seq = 0;

    private final static String STRING_THREAD_PREFIX = "SIMPLE_THREAD_POOL-";


    private final static ThreadGroup GROUP = new ThreadGroup("Pool_Group");

    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    public SimpleThreadPool(){
        this(DEFAULT_SIZE,DEFAULT_TASK_QUEUE_SIZE,DEFAULT_DISCARD_POLICY);
    }



    public SimpleThreadPool(int size,int queueSize,DiscardPolicy discardPolicy){
        this.size = size;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    public int getSize() {
        return size;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void submit(Runnable runnable){
        if(destory){
            throw new IllegalStateException("The thread pool already destory and not allow submit task.");
        }
        synchronized (TASK_QUEUE){
            if(TASK_QUEUE.size()>queueSize){
                discardPolicy.discard();
            }
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    private void createWorkTask(){
        WorkerTask task = new WorkerTask(GROUP,STRING_THREAD_PREFIX+(seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }

    private void init(){
        for(int i = 0;i<size;i++){
            createWorkTask();
        }
    }

    public void shutdown() throws InterruptedException {

        System.out.println("进入shutdown方法");
        //这里就设计的特别好,sleep 50毫秒之后,会再次执行查看task列是否空
        while(!TASK_QUEUE.isEmpty()){
            Thread.sleep(50);
        }
        int initVal = THREAD_QUEUE.size();
        //查看工作的线程是否已经
        while(initVal > 0){
            for(WorkerTask task : THREAD_QUEUE){
                if(task.getTaskState() == TaskState.BLOCKED){
                    task.interrupt();
                    task.close();
                    initVal--;
                }else{
                    Thread.sleep(10);
                }
            }
        }
        this.destory = true;
        System.out.println("The Thread pool deposed");
    }

    private enum TaskState{
        FREE,RUNNING,BLOCKED,DEAD
    }

    public static class DiscardException extends RuntimeException{
        DiscardException(String message){
            super(message);
        }
    }

    public interface DiscardPolicy{
        void discard() throws DiscardException;
    }

    private static class WorkerTask extends Thread{

        private volatile  TaskState taskState = TaskState.FREE;

        public WorkerTask(ThreadGroup group,String name){
            super(group,name);
        }

        public TaskState getTaskState(){
            return this.taskState;
        }
        @Override
        public void run(){
            OUTER:
            while(this.taskState!=TaskState.DEAD){
                Runnable runnable;
                synchronized (TASK_QUEUE){
                    while (TASK_QUEUE.isEmpty()){
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            break OUTER;
                        }
                    }
                }
                runnable = TASK_QUEUE.removeFirst();
                if (runnable!=null){
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }

            }
        }

        public void close(){
            this.taskState = TaskState.DEAD;
        }
    }
}
