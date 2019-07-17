package threadcommunication.lock;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author husky
 * @date 2019/7/17 10:16
 */
public interface Lock {
    void lock() throws InterruptedException;

    void lock(long millis) throws InterruptedException, TimeoutException;

    void unlock();

    List<Thread> getBlockedThreads();
}
