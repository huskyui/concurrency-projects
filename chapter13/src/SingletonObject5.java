/**
 *
 * 加入volatile可以避免空指针异常,也就是dcl
 * @author husky
 * @date 2019/5/3 23:12
 */
public class SingletonObject5 {
    private static volatile  SingletonObject5 instance;

    private SingletonObject5(){

    }

    private static SingletonObject5 getInstance(){
        if(null == instance){
            synchronized (SingletonObject5.class){
                if(null == instance){
                    return new SingletonObject5();
                }
            }
        }
        return instance;
    }
}
