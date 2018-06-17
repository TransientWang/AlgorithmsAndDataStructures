package wfy.sort;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;

public class BubbleSort {
    public static  <T extends Comparable<? super T>> void sort(T[] a){
        boolean falg = false;
        T temp;
        for (int i = a.length; i >= 1 ; i--) {
            for (int j = 0; j < i -1; j++) {
                if (a[j+1].compareTo(a[j])< 0){
                    temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;

                }

                falg =true;

            }
            //每次排序过后，最大值都会在最右边。下次排序在就不用管最右边得了
            if (falg == false)
                return;
        }

    }

    public static void main(String[] args) {
        Integer[] arr = {34,8,64,51,32,21};
        sort(arr);
        Arrays.stream(arr).forEachOrdered(o -> System.out.println(o));
    }
}
