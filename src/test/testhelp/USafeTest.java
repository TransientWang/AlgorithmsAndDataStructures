package test.testhelp;

import java.lang.reflect.Array;
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
        StringBuilder stringBuilder = new StringBuilder();
        long startTime = System.currentTimeMillis();
        Class[] types = method.getParameterTypes();
        stringBuilder.append("测试方法：" + method.getName() + " 测试用例：");
        for (int i = 0; i < types.length; i++) {
            if (args[i].getClass().isArray()) {
                stringBuilder.append("[ ");
                for (int j = 0; j < Array.getLength(args[i]); j++) {
                    if (j != Array.getLength(args[i])-1) {
                        stringBuilder.append(Array.get(args[i], j) + ",");
                    } else {
                        stringBuilder.append(Array.get(args[i], j));
                    }
                }
                stringBuilder.append("]  ");
            } else {
                stringBuilder.append(types[i].getName() + ":" + args[i] + "   ");
            }
        }
        stringBuilder.append("\r\n");

        try {
            stringBuilder.append("结果:" + method.invoke(o, args) + "\r\n");
            long endTime = System.currentTimeMillis();
            stringBuilder.append("耗时:" + (endTime - startTime) + " Millis\r\n");
            System.out.println(stringBuilder.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();

        } catch (InvocationTargetException e) {
            System.out.println("运行超时");

        }

    }


}
