/**
 * @author husky
 * @date 2019/4/5 10:24
 */
public class TryConcurrency {
    public static void main(String[] args) {
//        readFromDataBase();
//        writeDataToFile();
//        Thread t1 = new Thread("custom-thread"){
//            @Override
//            public void run() {
//                for(int i  = 0 ;i < 1000; i++){
//                    println("task 1=>" + i);
//                    try {
//                        Thread.sleep(1000L);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        t1.start();
//
//        for(int j = 0; j< 1000; j++){
//            println("task 2=>" + j);
//        }


        //read db and write file concurrency
        new Thread("read db"){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000*5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                readFromDataBase();
            }
        }.start();

        new Thread("write file"){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000*5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                writeDataToFile();
            }
        }.start();

    }

    private static void readFromDataBase(){
        //read data from database and handle it

        try {
            println("Begin read data from db.");
            Thread.sleep(10*1000);
            println("read data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println(" read handle data done and successfully ");
    }

    private static void writeDataToFile(){
        //write data to file and handle it

        try {
            println("Begin write data to file.");
            Thread.sleep(10*1000);
            println("write data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("write handle data done and successfully ");
    }

    private static void println(String message){
        System.out.println(message);
    }
}
