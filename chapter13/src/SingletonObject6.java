/**
 * @author husky
 * @date 2019/5/3 23:22
 */
public class SingletonObject6 {

    private SingletonObject6(){

    }

    private static class InstanceHolder {
        private final static SingletonObject6 instance = new SingletonObject6();
    }

    public static SingletonObject6 getInstance(){
        return InstanceHolder.instance;
    }
}
