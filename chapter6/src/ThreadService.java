/**
 * @author husky
 * @date 2019/4/17 18:06
 */
public class ThreadService {
    private Thread executeThread;
    private boolean finished = false;
    public void execute(Runnable task){
        executeThread = new Thread(){
            @Override
            public void run() {
                Thread runner = new Thread(task);
                runner.setDaemon(true);
                runner.start();
                try{
                    runner.join();
                    finished = true;
                }catch (InterruptedException e){

                }
            }
        };

        executeThread.start();
    }

    public void shutdown(long mils){
        long currentTime = System.currentTimeMillis();
        while(!finished){
            if((System.currentTimeMillis()-currentTime)>= mils){
                System.out.println("任务超时，需要结束他！");
                executeThread.interrupt();
                break;
            }

            try{
                Thread.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }


    }
}
