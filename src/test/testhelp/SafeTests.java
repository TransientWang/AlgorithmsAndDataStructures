package test.testhelp;

import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * @author wangfy
 * @Description TODO
 * @date 2018/11/22
 */

public class SafeTests extends USafeTest {

    ExecutorService executor = Executors.newFixedThreadPool(1);

    public void safeInvoke(Method method, long overTime, Object o, Object... args) throws Exception {
        var futureTask = new FutureTask<Void>(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                USafeInvoke(method, o, args);
                return null;
            }
        });

        executor.submit(futureTask);
        try {
            futureTask.get(overTime, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("运行超时");
        }
        executor.shutdown();
    }

}
