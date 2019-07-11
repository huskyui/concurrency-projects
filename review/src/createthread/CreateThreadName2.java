package createthread;

import java.util.stream.IntStream;

/**
 * @author husky
 * @date 2019/7/10 13:56
 */
public class CreateThreadName2 {
    private final static String PREFIX = "ALEX-";

    public static void main(String[] args) {
        IntStream.rangeClosed(0,4).mapToObj(CreateThreadName2::createThread)
                .forEach(Thread::start);
    }

    private static Thread createThread(Integer i){
        return new Thread(()-> System.out.println(Thread.currentThread().getName()),PREFIX+i);
    }
}
