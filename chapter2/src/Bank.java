/**
 * @author husky
 * @date 2019/4/5 16:16
 */
public class Bank {
    public static void main(String[] args) {
        TicketsWindow t1 = new TicketsWindow("一号柜台");
        TicketsWindow t2 = new TicketsWindow("二号柜台");
        TicketsWindow t3 = new TicketsWindow("三号柜台");
        TicketsWindow t4 = new TicketsWindow("四号柜台");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
