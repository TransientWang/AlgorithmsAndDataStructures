package test.testhelp;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wangfy
 * @Description TODO
 * @date 2018/11/3
 */

public class InvokeAdapter {
    public static void Invoke(Method method, Object o, Object... args) throws Exception {

        Annotation annotation = null;
        Long overTime = 0L;
        if ((annotation = method.getDeclaredAnnotation(safeTest.class)) != null)
            overTime = ((safeTest) annotation).value();
        if (overTime == 0L) {
            USafeInvoke(method, o, args);
        } else {
            safeInvoke(method, overTime, o, args);

        }
    }

    public static void caseInvoke(Method method, Object o, Object... args) throws Exception {
        Long overTime = 0L;
        Annotation annotation = null;
        if ((annotation = method.getDeclaredAnnotation(safeTest.class)) != null)
            overTime = ((safeTest) annotation).value();

        if (overTime == 0L) {
            for (int j = 0; j < args.length; j++) {
                if (args[j].getClass().isArray()) {
                    Object[] arg = (Object[]) args[j];
                    USafeInvoke(method, o, arg);
                } else {
                    Object arg = args[j];
                    USafeInvoke(method, o, arg);
                }
            }
        } else {
            for (int j = 0; j < args.length; j++) {
                Object[] arg = (Object[]) args[j];
                safeInvoke(method, overTime, o, arg);
            }
        }
    }

    private static void safeInvoke(Method method, long overTime, Object o, Object... args) throws Exception {
        Object lock = new Object();
        Runnable runnable = new InvokeAdapter.HelpRunnables(lock, method, o, args);
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
        synchronized (lock) {
            lock.wait(overTime);
        }
        if (thread.isAlive()) {
            System.out.println("运行超时");
        }


    }

    private static void USafeInvoke(Method method, Object o, Object... args) {
        long startTime = System.currentTimeMillis();
        System.out.print("开始测试：" + method.getName() + " 方法" +
                "测试用例：");
        Class[] types = method.getParameterTypes();
        for (int i = 0; i < types.length; i++) {
            System.out.print(types[i].getName() + ":" + args[i] + "   ");
        }

        System.out.println();
        try {
            System.out.println("结果:" + method.invoke(o, args));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗时:" + (endTime - startTime) + " Millis");

    }

    static class HelpRunnables implements Runnable {

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
