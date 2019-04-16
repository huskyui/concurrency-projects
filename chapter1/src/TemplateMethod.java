/**
 * @author husky
 * @date 2019/4/5 15:52
 */
public class TemplateMethod {
    public final void print(String message){
        System.out.println("############");
        wrapPrint(message);
        System.out.println("############");
    }

    protected void wrapPrint(String message){

    }

    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod(){
            @Override
            protected void wrapPrint(String message) {
                System.out.println("*"+message+"*");
            }
        };
        t1.print("hello,template methods");
    }
}
