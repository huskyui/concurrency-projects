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

    private final static int DEFAULT_SIZE = 10;

    private final static int QUEUE_SIZE = 2000;

    private static volatile int seq = 0;

    private final static String STRING_THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    private final static ThreadGroup GROUP = new ThreadGroup("Pool_Group");

    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    public SimpleThreadPool(){
        this(DEFAULT_SIZE,QUEUE_SIZE);

    }

    public SimpleThreadPool(int size,int queueSize){
        this.size = size;
        this.queueSize = queueSize;
        init();
    }

    public void submit(Runnable runnable){
        synchronized (TASK_QUEUE){
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

    private enum TaskState{
        FREE,RUNNING,BLOCKED,DEAD
    }

    public class DiscardException extends RuntimeException{
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
