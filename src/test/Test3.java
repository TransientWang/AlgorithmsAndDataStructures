package com.example.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Test3 {

    public static void qiuckSort(int[] a, int left, int right) {
        if (left > right)
            return;

        int temp = a[left];
        int i = left;
        int j = right;
        int t;

        while (i < j) {
            while (i < j && a[j] >= temp) j--;
            while (i < j && a[i] <= temp) i++;
            if (i != j) {
                t = a[j];
                a[j] = a[i];
                a[i] = t;
            }
        }
        a[left] = a[i];
        a[i] = temp;

        qiuckSort(a, left, i - 1);
        qiuckSort(a, i + 1, right);
    }

    @Test
    public void test() {
        Random random = new Random(30);
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt() % 50;
            System.out.print(a[i] + "   ");
        }
        System.out.println();
//        qiuckSort(a,0,a.length-1);
        selSort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + "   ");

        }
    }


    public static void insertSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            for (int j = (i - 1); j >= 0; j--) {
                if (a[j] < temp) {
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    public static void shelltSort(int[] a) {
        int h = a.length / 3;
        while (h >= 1) {
            for (int i = h; i < a.length; i++) {
                int temp = a[i];
                for (int j = (i - h); j >= h; j -= h) {
                    if (a[j] < temp) {
                        a[j + h] = a[j];
                        a[j] = temp;
                    }

                }
            }
            h /= 2;
        }
    }

    public static void bubble(int[] a) {
        for (int i = a.length; i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (a[j + 1] > a[j]) {
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                }
            }
        }
    }
//    public static void selSort(int[] a){
//        for (int i = 0; i < a.length -1; i++) {
//            int minindex = i;
//            for (int j = i+1; j < a.length; j++) {
//             if (a[j]< a[minindex]){
//                 minindex = j;
//             }
//            }
//            int t = a[minindex];
//            a[minindex] = a[i];
//            a[i] = t;
//        }
//
//    }


    public void selSort(int[] a) {
        int minIndex = 0;
        int temp;
        for (int i = 0; i < a.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[minIndex] > a[j]) {
                    minIndex = j;
                }
            }
            temp = a[minIndex];
            a[minIndex] = a[i];
            a[i] = temp;
        }

    }

    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    @Test
    public void test1() {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
//      listNode.next.next = new ListNode(3);

        System.out.println(ReverseList(listNode).val);
    }

    public ListNode ReverseList(ListNode head) {
        //用三个节点作为辅助  pre  cur aft
        //pre节点保存上一个节点  cur 节点保存当前反转的节点 aft保存当前节点 反转后断开的下一节点
        if (head == null)
            return null;            //头结点为null直接返回
        ListNode pre = head;
        ListNode cur = null;
        if (pre.next != null) {           //如果下一节点不为空，cur保存下一节点
            cur = pre.next;
            if (cur.next == null) {        //如果cur节点的下一节点为空。说明当前就有两个节点 ，应该断开头结点的下一节点，
                     //并将cur节点的下一节点，指向头结点 。链表反转完成
                pre.next = null;
                cur.next = pre;
                return cur;
            }
        } else {
            return pre;
        }
        pre.next = null; //将头结点的下一个节点断开，防止前两个节点变成环
        ListNode aft = cur.next;   //保存aft节点
        while (cur != null) {
            cur.next = pre;   //cur节点的下一节点指向pre 反转
            pre = cur;      //pre后移
            cur = aft;      //cur后移
            if (aft.next != null)
                aft = aft.next;    //如果aft后面还有节点 ，aft后移
            else {
                cur.next = pre;  //如果没有，立即反转后移一位的cur节点
                break;
            }
        }
        return cur;
    }
}
