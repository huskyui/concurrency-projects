###不错的教程
[java concurrency](https://www.callicoder.com/java-concurrency-multithreading-basics/)

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
### 优雅结束线程
    1.在Thread的子类里面放一个private voliate boolean flag，再创建一个方法，
    shutdown方法，可以置为flase的
    2.在子类的while(true)中使用，if(Thread.interrupt()){break;}
    并不是正确的
### interrupt
    interrupt在线程中是一个
### 强制中断线程
    两个线程，执行线程作为子线程并设为守护线程，外部线程打断，也就会把子线程强制关闭
### synchronized 
    1.让共享数据串性化去运行。
### this,class锁
    在编写sychronized方法时，会用到this锁
    而在编写static静态方法时，会用到class锁
### notify和notifyAll区别
    首先wait()会导致当前线程等待直到其他线程调用notify或者notifyAll方法
    notify()会唤醒一个单独线程在等待该对象的监视器。如果有很多在等待，只有一个会被唤醒，选择是随意的
    notifyAll()唤醒所有线程，所有线程进行竞争，但是只有一个成功
### 生产者消费者
    描述：案例
    多个摄像机拍摄地铁上的人，然后存储在memery里面，然后有很多机器分析数据，
    生产者和消费者就是圆环，互相追赶、
### wait和sleep区别
    1.wait是Object里面的方法，sleep是Thread的方法
    2.sleep不会释放object monitor(Lock),但是wait会释放monitor并且加入到这个monitor的等待队列里面
    3.sleep不需要依赖monitor，而wait需要
    4.sleep不要被唤醒，而wait需要
### 显示锁设计
    1.需要有一个状态flag
    2.currentThread
    3.Collection来存储阻塞的线程
### 4.为什么lock中while，
    因为多线程中，每个线程的run方法中都用lock和unlock包裹，所以，同一时间段，只有一个人获取到锁
    只有一个线程获取到锁，其他线程就不会获取到，其他线程使用while不断轮询，希望获得锁。一个获得，其他都还在持续轮询
### 如何创建简易线程池
    1.线程池参数:线程数目,运行线程列表,任务队列列表
    2.线程池里面的线程需要封装,需要有对应的状态,FREE,BLOCK,RUNNING,DEAD
    3.线程池刚开始要要全部运行起来,但是会阻塞住
    4.添加任务时,唤醒所有阻塞的线程

### 官方线程池java.util.concurrent.ThreadPoolExecutors
    1.首先创建线程池,放置参数
    2.大致逻辑,创建好线程池后,部署任务,而任务一般是实现Runnable接口的
    3.部署任务是threadPool.execute(task)时,会进行逻辑判断
    会执行addWorker,因为线程池里面线程并不可能把所有task立刻执行完,而是分批次的
    所有就会产生,如果多出的任务,应该怎么办,这里我们选择比较简单的把任务储存起来,BlockingQueue
    4.在addWorker时,其实会创建Worker,奥义就在这个run()方法里面,里面封装了一个
    runWorker()方法.会重复获取task并且运行它

### 单例
    1.由于是单例,所以不能不如构造函数给别人,所以是私有的
    2.