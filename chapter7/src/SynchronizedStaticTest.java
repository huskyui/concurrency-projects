/**
 * 可以证明class锁的存在
 * @author husky
 * @date 2019/4/18 17:15
 */
public class SynchronizedStaticTest {
    public static void main(String[] args) {
        SynchronizedStatic synchronizedStatic = new SynchronizedStatic();
        new Thread("t1"){
            @Override
            public void run() {
                synchronizedStatic.m1();
            }
        }.start();
        new Thread("t2"){
            @Override
            public void run() {
                synchronizedStatic.m2();
            }
        }.start();

    }
}
