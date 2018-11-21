package test.testhelp;

import java.lang.reflect.Method;

/**
 * @author wangfy
 * @Description TODO
 * @date 2018/11/22
 */

public interface Test {
    public  void USafeInvoke(Method method, Object o, Object... args);


}
