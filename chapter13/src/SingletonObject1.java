/**
 * can't lazy load
 * 饿汉式,不能懒加载
 * @author husky
 * @date 2019/5/3 20:42
 */
public class SingletonObject1 {
    private static final SingletonObject1 instance = new SingletonObject1();

    private SingletonObject1(){
        //empty
    }

    private static SingletonObject1 getInstance(){
        return instance;
    }

}
