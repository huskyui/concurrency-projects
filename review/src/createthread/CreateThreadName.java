package createthread;

import java.util.stream.IntStream;

/**
 * @author husky
 * @date 2019/7/10 11:47
 */
public class CreateThreadName {
    public static void main(String[] args) {
        IntStream.rangeClosed(0,10)
                .boxed()
                .map(i->new Thread(
                    ()->System.out.println(Thread.currentThread().getName())
                )).forEach(Thread::start);
    }
}
