package test;

import org.junit.Test;
import wfy.tree.UnderflowException;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class TestPro {
    @Test
    public void testWiteBox() {
        int i = 0;
        try {

            i++;
            System.out.println("测试" + i);


        } finally {
            i++;
            System.out.println("finally" + i);
        }

    }

    @Test
    public void t() throws NoSuchFieldException, IllegalAccessException {

        String a = "sss";

        Field field = a.getClass().getDeclaredField("value");
        field.setAccessible(true);
        char[] chars = (char[]) field.get(a);

        chars[2] = 'S';
        System.out.println(a);


    }

    @Test
    public void list() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.subList(1, 3).clear();
        System.out.println(list);
        Integer[] integers = new Integer[]{1, 3, 4, 5, 6};
        List<Integer> list1 = Arrays.asList(integers);
        System.out.println(list1.get(1));


        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        for (String temp : a) {
            if ("2".equals(temp)) {
                a.remove(temp);
            }
        }
        System.out.println(a);
    }

    @Test
    public void yy() {
        BeeperControl beeperControl = new BeeperControl();
        beeperControl.beepForAnHour();
    }

    class BeeperControl {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);//创建线程池

        void beepForAnHour() {

            Runnable beeper = new Runnable() {
                public void run() {
                    System.out.println("beep");
                }
            };

            ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper,
                    1, 1, TimeUnit.SECONDS);

            ScheduledFuture<?> bee = scheduler.schedule(new Runnable() {
                public void run() {

                    beeperHandle.cancel(true);
                }
            }, 5, TimeUnit.SECONDS);

            try {
                beeperHandle.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        }
    }
}
