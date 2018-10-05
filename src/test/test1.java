package com.example.demo;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class test1 {
    @Test
    public void aaa(){
        System.out.println(16 | 16);
    }
    @Test
    public void aaaa() {
        Random r = new Random(30);
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++) {

            a[i] = r.nextInt() % 100;
        }
        Arrays.stream(a).forEachOrdered(o -> System.out.println(o));
        System.out.println();
        shellinsert(a);
        Arrays.stream(a).forEachOrdered(o -> System.out.println(o));
    }

    public static void quickSort(int[] a, int left, int right) {
        if (left > right)
            return;

        int temp = a[left];
        int t = 0;
        int i = left;
        int j = right;
        while (i != j) {
            while (i < j && a[j] >= temp) j--;
            while (i < j && a[i] <= temp) i++;
            if (i < j) {
                t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        a[left] = a[i];
        a[i] = temp;
        quickSort(a, left, i - 1);
        quickSort(a, i + 1, right);
    }



    public static void insert(int a[]){
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            for (int j = (i-1); j >= 0; j--) {
                if (temp < a[j]){
                    a[j+1] = a[j];
                      a[j] = temp;
                }
            }
        }
    }

    public static void shellinsert(int a[]) {
        int h = a.length/4;
        while (h >= 1) {
            for (int i = h; i < a.length; i++) {
                int temp = a[i];
                for (int j = (i - h); j >=0; j--) {
                    if (temp > a[j]) {
                        a[j + 1] = a[j];
                        a[j] = temp;
                    }
                }
                h/=2;
            }
        }
    }
    //判断条件只能是 i j
    public static void sort(int[] a, int left, int riggt) {
        if (left > riggt)
            return;
        int temp = a[left];
        int t;
        int i = left;
        int j = riggt;

        while (i != j) {
            while (i < j && a[j] >= temp) j--;
            while (i < j && a[i] <= temp) i++;
            if (i < j) {
                t = a[j];
                a[j] = a[i];
                a[i] = t;
            }
        }
        if (i == j) {
            a[left] = a[i];
            a[i] = temp;
        }
        sort(a, left, i - 1);
        sort(a, i + 1, riggt);


    }



}
