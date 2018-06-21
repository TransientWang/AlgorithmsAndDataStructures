package Lamda;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GoTest {

    public static void main(String[] args) {


        RunningMan runningMan = new RunningMan() {
            @Override
            public RunningMan begin() {
                System.out.println("欢迎大家收看跑男");
                return null;
            }

            @Override
            public RunningMan middle() {
                System.out.println("下面我们来进行游戏环节");
                return null;
            }

            @Override
            public void end() {

                System.out.println("本期节目尾声");
            }
        };

        RunningMan proxInstance = (RunningMan) Proxy.newProxyInstance(runningMan.getClass().getClassLoader(),
                runningMan.getClass().getInterfaces(), new InvocationHandler() {

                    int sum;

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object o = method.invoke(runningMan, args);
                        switch (method.getName()) {
                            case "begin":
                                System.out.println("本期节目开始");
                                return proxy;

                            case "middle":
                                System.out.println("进入下一环节");
                                sum++;
                                return proxy;

                            case "end":
                                System.out.println("一共进行了：" + sum + "个环节的游戏");
                                System.out.println("本期节目增加了一个回忆往期环节，播放短片后结束本期节目");

                            default:
                                return o;

                        }


                    }
                });
        proxInstance.begin().middle().middle().middle().middle().end();


    }
}
