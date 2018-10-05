package com.example.demo;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class InsertSort {
//    public void insertSort(int[] a) {
//        for (int i = 1; i < a.length; i++) {
//            int temp = a[i];
//            for (int j = (i - 1); j >= 0; j--) {
//                if (temp > a[j]) {
//                    a[j + 1] = a[j];
//                    a[j] = temp;
//                }
//            }
//        }
//    }

    public static void insertSort(int[] a){
        int temp = 0;
        for (int i = 1; i < a.length; i++) {
            temp=a[i];
            for (int j = i; j >=0 ; j--) {
                if (temp > a[j]){
                  a[j+1] = a[j]; // a[j]后移
                  a[j] = temp; //temp赋值给A[j]
                }

            }
        }

    }
    public void shellSort(int[] a) {
        int h = a.length / 3;
        while (h >= 1) {
            for (int i = h; i < a.length; i++) {
                int temp = a[i];
                for (int j = (i - h); j >= 0; j--) {
                    if (temp > a[j]) {
                        a[j + 1] = a[j];
                        a[j] = temp;
                    }
                }
                h = h / 2;
            }
        }
    }

//    public static void quickSort(int[] a, int left, int right) {
//
//        int temp = a[left]; //哨兵
//        int t = 0;
//        int i = left;
//        int j = right;
//        while (i != j) {
//            while (i < j && a[j] >= temp) j--;
//            while (i < j && a[i] <= temp) i++;
//            if (i < j) {
//                t = a[i];
//                a[i] = a[j];
//                a[j] = t;
//            }
//        }
//        a[left] = a[i];   //哨兵交换
//        a[i] = temp;
//        quickSort(a, left, i - 1);
//        quickSort(a, i + 1, right);
//    }

    public static void quickSort(int[] a,int left,int right){
        if (left>right)   //必须要判断
            return;

        int i = left;
        int j = right;
        int temp =a[left];
        int t;

        while (i!=j){
            while (i<j && a[j] >= temp) j--;
            while (i<j && a[j] <= temp) i++;
            if (i<j){
                t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }

       a[left] = a[i];
        a[i] = temp;

        quickSort(a,left,i-1);
        quickSort(a,i+1,right);

    }

    public static void main(String[] args) {
        int[] a = {4, 9,1, 5, 6, 10, 6};
        quickSort(a, 0, a.length - 1);
//        insertSort(a);
        Arrays.stream(a).forEachOrdered(o -> System.out.println(o));
    }
    @Test
    public void test() {

    }
}
