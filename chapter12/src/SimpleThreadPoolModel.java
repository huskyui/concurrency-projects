import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author husky
 * @date 2019/4/25 16:40
 */
public class SimpleThreadPoolModel {
    private static final int DEFAULT_SIZE = 10;
    private final int size;

    private final static LinkedList<Runnable> RUNNABLE_THREAD_QUEUE = new LinkedList<Runnable>();
    private static volatile int seq = 0;
    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL_";
    private final static ThreadGroup GROUP = new ThreadGroup("Pool_Group");
    private final static List<WorkerTaskThread> THREAD_DQUEUE = new ArrayList<WorkerTaskThread>();

    public SimpleThreadPoolModel() {
        this(DEFAULT_SIZE);
    }

    public SimpleThreadPoolModel(int size) {
        this.size = size;
        init();
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            createWorkTask();
        }
    }

    private void createWorkTask() {
        WorkerTaskThread task = new WorkerTaskThread(GROUP, THREAD_PREFIX + (seq++));
        task.start();
        THREAD_DQUEUE.add(task);
    }

    // 线程池缓存队列
    public void submit(Runnable runnable) {
        synchronized (RUNNABLE_THREAD_QUEUE) {
            RUNNABLE_THREAD_QUEUE.addLast(runnable);
            RUNNABLE_THREAD_QUEUE.notifyAll();
        }
    }

    // 枚举
    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD
    }

    /**
     * 静态内部类
     *
     **/
    private static class WorkerTaskThread extends Thread {
        private volatile TaskState taskState = TaskState.FREE;

        public WorkerTaskThread(ThreadGroup group, String name) {
            super(group, name);
        }

        public TaskState getTaskState() {
            return this.taskState;
        }

        public void close() {
            this.taskState = TaskState.DEAD;
        }


        @Override
        public void run() {
            OUTER: while (this.taskState != TaskState.DEAD) {
                Runnable runnable;
                synchronized (RUNNABLE_THREAD_QUEUE) {
                    while (RUNNABLE_THREAD_QUEUE.isEmpty()) {
                        try {
                            taskState = TaskState.BLOCKED;
                            RUNNABLE_THREAD_QUEUE.wait();
                        } catch (InterruptedException e) {
                            break OUTER;
                        }
                    }
                }
                runnable = RUNNABLE_THREAD_QUEUE.removeFirst();
                if (runnable != null) {
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }
    }

    public static void main(String[] args) {
        SimpleThreadPoolModel threadPool = new SimpleThreadPoolModel();

        for (int i = 0; i < 40; i++) {
            Thread t = new Thread(new Runnable() {

                public void run() {
                    System.out.println(Thread.currentThread() + " start.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + " finished.");
                }
            });

            //
            threadPool.submit(t);
        }
    }
}
