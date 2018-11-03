package test.testhelp;

import java.util.concurrent.*;

/**
 * @author wangfy
 * @Description TODO
 * @date 2018/11/3
 */

public enum ThreadPoolSinglton {

    CACHETHREADPOOL;

    public ExecutorService getThreadPool() {
        return threadPool;
    }

    private ExecutorService threadPool = new ThreadPoolExecutor(4, 20, 5, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            System.out.println("创建线程");
            Thread deamonThread = new Thread(r);
            deamonThread.setDaemon(true);
            return deamonThread;
        }
    });


}
