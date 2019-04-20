import java.util.*;

/**
 * @author husky
 * @date 2019/4/20 14:26
 */
public class CaptureService {
    private static  LinkedList<Control> controls = new LinkedList<>();
    private static final int MAX_WORKER = 5;

    public static void main(String[] args) {
        List<Thread> workThread = new ArrayList<>();

        Arrays.asList("M1","M2","M3","M4","M5","M6","M7","M8","M9","M10").stream()
                .map(CaptureService::createCaptureThread)
                .forEach(t->{
                    t.start();
                    workThread.add(t);
                });
        workThread.stream().forEach(t-> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Optional.of("All of capture work finished").ifPresent(System.out::println);
    }

    public static Thread createCaptureThread(String name){
        return new Thread(()->{
            Optional.of("The worker["+Thread.currentThread().getName()+"] begin capture data")
                    .ifPresent(System.out::println);
            synchronized (controls){
                while(controls.size() > MAX_WORKER){
                    try {
                        controls.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                controls.addLast(new Control());
            }

            Optional.of("the worker ["+Thread.currentThread().getName()+"] doing  capture data.").ifPresent(System.out::println);

            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (controls){
                Optional.of("the worker ["+Thread.currentThread().getName()+
                        "] has finished").ifPresent(System.out::println);
                controls.removeFirst();
                controls.notifyAll();
            }
        },name);
    }

    private static class Control{

    }
}
