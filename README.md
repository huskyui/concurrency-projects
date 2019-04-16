###try concurrency
    在这个文件中，我们希望可以两个方法可以同时进行
    
### stackSize 参数
 stackSize表示一个线程的栈大小，如果是0将会被忽略
### 在Thread.sleep(1000L)
在sleep的时候，如果是5s,可以Thread.sleep(5_000);
### 守护线程（Daemon Thread）
    1.线程在设置为守护线程时，会随着main函数结束后结束
    2.设置守护线程的时候，需要在start()方法之前使用setDeamon(boolean on)
    3.创建一个线程，该线程为守护线程，该线程结束时，子线程也会结束
 
### join
    join 当前线程等待子线程的终止。
    当子线程运行耗时时，主线程会优先子线程结束，而有些时候，主线程需要用到子线程的数据，
    所以会等待子线程运行结束再运行主线程，会用join方法
   [join详解](https://www.jianshu.com/p/367fa66bf3f2)