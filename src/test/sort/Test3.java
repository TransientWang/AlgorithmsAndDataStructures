package test.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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




    /**
     * @return void
     * @Description 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     * <p>
     * 说明:
     * <p>
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     * @Param [nums1, m, nums2, n]
     * @Line 110
     **/

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        while (m < nums1.length && i < m) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else {
                int t = m - 1;
                while (i <= t) {
                    nums1[t + 1] = nums1[t];     //插入排序部分 要从后面往前面遍历，比较方便，不会出现当数组长度短的时候 索引溢出的情况
                    t--;
                }
                nums1[i] = nums2[j++];
                m++;
                i++;
            }
        }
        while (j < n) {
            nums1[m++] = nums2[j++];
        }
        Arrays.stream(nums1).forEachOrdered(o -> System.out.print(o + " "));
    }

    @Test
    public void testS() {
        merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
    }

    /**
     * @return int
     * @Description 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     * <p>
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     * <p>
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     *
     * @Param [n]
     * @Line 152
     **/
    public int firstBadVersion(int n) {
        if (isBadVersion(1)) return 1;
        int left = 1, right = n;
        int mid = 0;
        while (left < right) {
            mid = left / 2 + right / 2;
            if (isBadVersion(mid)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    public boolean isBadVersion(int n) {
        return false;
    }
}
