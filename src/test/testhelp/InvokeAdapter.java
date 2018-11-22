package test.testhelp;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;

/**
 * @author wangfy
 * @Description TODO
 * @date 2018/11/3
 */

public class InvokeAdapter {

    ExecutorService executorService = ThreadPoolSinglton.CACHETHREADPOOL.getThreadPool();

    public static void Invoke(Method method, Object o, Object... args) throws Exception {

        Annotation annotation = null;
        Long overTime = 0L;
        if ((annotation = method.getDeclaredAnnotation(safeTest.class)) != null)
            overTime = ((safeTest) annotation).value();
        if (overTime == 0L) {
            USafeTest uSafeTest = new USafeTest();
            uSafeTest.USafeInvoke(method, o, args);
        } else {
            SafeTests safeTests = new SafeTests();
            safeTests.safeInvoke(method, overTime, o, args);

        }
    }

//    public static void caseInvoke(Method method, Object o, Object... args) throws Exception {
//        Long overTime = 0L;
//        Annotation annotation = null;
//        if ((annotation = method.getDeclaredAnnotation(safeTest.class)) != null)
//            overTime = ((safeTest) annotation).value();
//
//        if (overTime == 0L) {
//
//            for (int j = 0; j < args.length; j++) {
//                if (args[j].getClass().isArray()) {
//                    Object[] arg = (Object[]) args[j];
//
//                } else {
//                    Object arg = args[j];
//                    USafeInvoke(method, o, arg);
//                }
//            }
//        } else {
//            for (int j = 0; j < args.length; j++) {
//                Object[] arg = (Object[]) args[j];
//                safeInvoke(method, overTime, o, arg);
//            }
//        }
//    }


}
