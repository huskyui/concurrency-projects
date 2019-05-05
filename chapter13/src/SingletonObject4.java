/**
 * double check
 * 解决了懒加载以及单例,以及性能问题,但是有问题,可能会出现空指针异常
 * 在private SingletonObject()会造成fielda或者fieldB未被赋值,导致空指针
 * @author husky
 * @date 2019/5/3 21:31
 */
public class SingletonObject4 {
    private static SingletonObject4 instance;
    private int fieldA;
    private int fieldB;

    private SingletonObject4(){
        fieldA = Integer.MIN_VALUE;
        fieldB = Integer.MAX_VALUE;
    }

    public static SingletonObject4 getInstance(){
        if(null == instance){
            synchronized (SingletonObject4.class){
                if(null == instance){
                    instance = new SingletonObject4();
                }
            }
        }
        return instance;
    }

}
