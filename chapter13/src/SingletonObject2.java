/**
 * 在多线程情况下是不安全的,因为在getIntsance中判断的时候,可能会造成多个实例出现
 * 实现懒加载,但线程不安全
 * @author husky
 * @date 2019/5/3 21:02
 */
public class SingletonObject2 {
    private static SingletonObject2 instance;

    private SingletonObject2(){
        //empty
    }

    public static SingletonObject2 getInstance(){
        if(null == instance){
            instance = new SingletonObject2();
        }
        return instance;
    }
}
