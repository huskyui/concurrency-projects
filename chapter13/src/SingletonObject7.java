import java.util.stream.IntStream;

/**
 * 枚举类型
 * @author husky
 * @date 2019/5/4 14:37
 */
public class SingletonObject7 {
    private SingletonObject7(){

    }

    private enum Singleton {
        /**
         * instance
         * */
        INSTANCE;
        private final SingletonObject7 instance;

        Singleton(){
            instance = new SingletonObject7();
        }

        public SingletonObject7 getInstance(){
            return instance;
        }
    }

    public static SingletonObject7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1,50).forEach(i->{
            new Thread(String.valueOf(i)){
                @Override
                public void run() {
                    System.out.println(SingletonObject7.getInstance());
                }
            }.start();
        });
    }


}
