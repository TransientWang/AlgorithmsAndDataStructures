package test.testhelp;

import java.lang.reflect.Method;

/**
 * @author wangfy
 * @Description TODO
 * @date 2018/11/22
 */

public class SafeTests extends USafeTest {

    public  void safeInvoke(Method method, long overTime, Object o, Object... args) throws Exception {
        Object lock = new Object();
        Runnable runnable = new HelpRunnables(lock, method, o, args);
        Thread tThread = new Thread(runnable);
        tThread.setDaemon(true);
        tThread.start();
        synchronized (lock) {
            lock.wait(overTime);
        }
        if (tThread.isAlive()) {
            tThread.stop();
        }
    }

    private class HelpRunnables implements Runnable {

        Object lock;
        Method method;
        Object o;
        Object[] args;

        public HelpRunnables(Object lock, Method method, Object o, Object[] args) {
            this.lock = lock;
            this.method = method;
            this.o = o;
            this.args = args;
        }

        public void run() {
            USafeInvoke(method, o, args);
            synchronized (lock) {
                lock.notify();
            }
        }
    }
}
