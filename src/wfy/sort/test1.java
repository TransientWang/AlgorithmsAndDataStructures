package wfy.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class test1 {
    @Test
    public void aaa() {
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


    public static void insert(int a[]) {
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            for (int j = (i - 1); j >= 0; j--) {
                if (temp < a[j]) {
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    public static void shellinsert(int a[]) {
        int h = a.length / 4;
        while (h >= 1) {
            for (int i = h; i < a.length; i++) {
                int temp = a[i];
                for (int j = (i - h); j >= 0; j--) {
                    if (temp > a[j]) {
                        a[j + 1] = a[j];
                        a[j] = temp;
                    }
                }
                h /= 2;
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
    public void test() {
        merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
    }
}
