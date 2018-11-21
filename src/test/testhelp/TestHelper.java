package test.testhelp;

import org.junit.Assert;

import java.beans.ParameterDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangfy
 * @Description TODO
 * @date 2018/11/3
 */

public class TestHelper {


    public static void test(Class clazz, String methodName, Object... args) {
        try {
            Object o = clazz.getDeclaredConstructor().newInstance();
            Method[] methods = clazz.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                method.setAccessible(true);
                if (method.getName() == methodName) {
                    InvokeAdapter.Invoke(method, o, args);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





//    public static void caseTest(Class clazz, String methodName, Object... args) {
//        try {
//            Object o = clazz.getDeclaredConstructor().newInstance();
//            Method[] methods = clazz.getDeclaredMethods();
//            for (int i = 0; i < methods.length; i++) {
//                Method method = methods[i];
//                Annotation annotation = null;
//                if (method.getName() == methodName) {
//                    method.setAccessible(true);
//                    InvokeAdapter.caseInvoke(method, o, args);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
