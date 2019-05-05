/**
 * 加一把锁可以解决,这把锁是class锁,SingletonObject3.class
 * 但是会造成效率低下
 * 实现了线程安全,但性能不高
 * @author husky
 * @date 2019/5/3 21:08
 */
public class SingletonObject3 {
    private static SingletonObject3 instance;

    private SingletonObject3(){
        //empty
    }

    public synchronized static SingletonObject3 getInstance(){
        if(null == instance){
            instance = new SingletonObject3();
        }
        return instance;
    }
}
