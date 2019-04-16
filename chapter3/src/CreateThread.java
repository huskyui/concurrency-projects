/**
 * @author husky
 * @date 2019/4/6 11:51
 */
public class CreateThread {
    private static int counter = 0;

    public static void main(String[] args) {
        try {
            add(0);
        }catch (Error e){
            e.printStackTrace();
            System.out.println(counter);
        }
    }

    public static void add(int i){
        ++counter;
        add(i+1);
    }
}
