import java.util.Optional;

/**
 * Thread简单api
 * @author husky
 * @date 2019/4/16 15:30
 */
public class ThreadSimpleApi {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(100_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        t1.start();
        Optional.of(t1.getId()).ifPresent(System.out::println);
        Optional.of(t1.getName()).ifPresent(System.out::println);
        Optional.of(t1.getPriority()).ifPresent(System.out::println);
    }
}
