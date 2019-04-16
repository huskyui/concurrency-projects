###如何查看后台线程
    在cmd中输入jconsole,会启动一个application，在里面可以查看
    各式各样的正在运行的线程
###在main函数中创建了两个线程
    在main函数中创建了两个线程后，并且start()后，main函数就嗝屁了
###在线程sleep(1000*10L)后，会立即执行吗？
    不会，因为线程的生命周期中，sleep(1000*10L)后，会进入阻塞状态，
    有可能会进入runnable状态，然后等待cpu dispatch（调度）之后才可能会
    进入running状态