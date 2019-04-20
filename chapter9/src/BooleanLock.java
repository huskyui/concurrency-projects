import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author husky
 * @date 2019/4/20 17:47
 */
public class BooleanLock implements Lock {
    //the initvalue is false indicated the lock is free( other Thread can get this)
    private boolean initValue;

    private Collection<Thread>  blockedThreadCollections = new ArrayList<>();

    @Override
    public synchronized void lock() throws InterruptedException {
        while(initValue){
            blockedThreadCollections.add(Thread.currentThread());
            this.wait();
        }
        System.out.println(Thread.currentThread().getName()+" get the lock");
        blockedThreadCollections.remove(Thread.currentThread());
        this.initValue = true;
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeOutException {

    }

    @Override
    public synchronized void unlock() {
        this.initValue = false;
        System.out.println(Thread.currentThread().getName()+" release the Thread");
        this.notifyAll();
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedThreadCollections);
    }

    @Override
    public int getBlockSize() {
        return blockedThreadCollections.size();
    }
}
