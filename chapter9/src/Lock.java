import java.util.Collection;

/**
 * @author husky
 * @date 2019/4/20 16:57
 */
public interface Lock {
    class TimeOutException extends Exception{
        public TimeOutException(String message){
            super(message);
        }
    }

    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException,TimeOutException;

    void unlock();

    Collection<Thread> getBlockedThread();

    int getBlockSize();
}
