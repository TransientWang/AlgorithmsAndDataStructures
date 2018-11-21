package test.testhelp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wangfy
 * @Description TODO
 * @date 2018/11/22
 */

public class USafeTest implements Test {

    @Override
    public void USafeInvoke(Method method, Object o, Object... args) {
        long startTime = System.currentTimeMillis();
        Class[] types = method.getParameterTypes();
        System.out.print("开始测试：" + method.getName() + " 方法" +
                "测试用例：");
        for (int i = 0; i < types.length; i++) {
            System.out.print(types[i].getName() + ":" + args[i] + "   ");
        }
        System.out.println();

        try {
            System.out.println("结果:" + method.invoke(o, args));
            long endTime = System.currentTimeMillis();
            System.out.println("耗时:" + (endTime - startTime) + " Millis");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println("运行超时");
        }

    }


}
