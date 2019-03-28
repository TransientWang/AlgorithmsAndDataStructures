package test.divideAndConquer;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author wangfy
 * @Description 分治法
 * @date 2018/10/19
 */

public class test {
    @Test
    public void test() {
        Random random = new Random(1000);
        int a[] = new int[1000];
        for (int i = 0; i < 1000; i++) {
            a[i] = Math.abs(random.nextInt() % 1000);
        }
        Arrays.stream(a).forEachOrdered(var -> System.out.print(var + " "));
        System.out.println();
//        System.out.println(randomizedSelect(a, 0, a.length - 1, 434));
        Arrays.stream(a).sorted().forEachOrdered(var -> System.out.print(var + " "));

    }

    /**
     * @return int
     * @Description 找到数组中第k小的数
     * 先进快速排序，然后判断k是否小于等于哨兵位置长度-1
     * 如果小于的话，说明第k小的元素就在左边，则可以在左边进行插入排序，找到第k小的元素
     * 如果等于cur位置，说明哨兵就是第k小的值
     * 否则，第k小的元素一定在哨兵右边里面，为 第k-哨兵长度个
     * 则可以递归调用来寻找值
     * <p>
     * 注意，k是从1开始的
     * @Param [a, left, right, k]
     * k代表第k小的数
     * @Line 15
     **/
    public void randomizedSelect(int a[], int left, int right, int k) {
        if (left >= right) {
            return;
        }
        int i, j, temp;
        int cur = a[left];
        i = left;
        j = right;
        while (i != j) {
            while (i < j && a[j] <= cur) j--;
            while (i < j && a[i] >= cur) i++;
            if (i < j) {
                temp = a[j];
                a[j] = a[i];
                a[i] = temp;
            }
        }
        a[left] = a[j];
        a[j] = cur;

    }

    @Test
    public void ttt() {
        int[] n1 = {1, 3};
        int[] n2 = {2};

    }


    @Test
    public void tests111() {
        int[] a = {7, 6, 5, 4, 3, 2, 1};
        randomizedSelect(a, 0, a.length - 1, 5);
        Arrays.stream(a).forEachOrdered(o -> System.out.print(o + " "));
    }

    /**
     * @return int
     * @Description 插入排序找出第k小的数
     * @Param [a, left, right, k]
     * @Line 45
     **/
    public int insertSort(int[] a, int left, int right, int k) {
        int j = 0;
        for (int i = left + 1; i <= right; i++) {
            int temp = a[i];
            j = i - 1;
            while (j >= left && a[j] < temp) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = temp;
        }
        System.out.print("插入：");
        Arrays.stream(a).forEachOrdered(var -> System.out.print(var + " "));
        System.out.println();
        return a[left + k - 1];
    }

    @Test
    public void test1() {
        int[] arrs = {3, 2, 3};
        System.out.println(majorityElement(arrs));

    }


    /**
     * @return int
     * @Description 众数
     * 摩尔投票法  利用了分治法的思想
     * 假定第一个数为所找的众数
     * 然后定义一个计数器  用来投票用
     * 因为现在 已经有了一个 众数  那么 这个投票器的初始值就应该为1
     * 继续向下遍历  遇到相同的计数器 +1  不同的计数器-1
     * 当计数器为0的时候 代表前面所遍历过得的数字被分成了相等的两部分 其中众数占了一半
     * 这时候就要 假定当前索引位置的数字是众数 然后重复上述的过程
     * 因为众数的数量大于数组的一半，所以到最后假定的众数就是我们要找的众数
     * @Param [nums]
     * @Line 117
     **/
    public int majorityElement(int[] nums) {
        int count = 1;
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (num == nums[i]) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                num = nums[i];
                count = 1;
            }
        }
        return num;
    }

    class Solution {
        public int maxProfit(int[] prices) {
            int i = 0;
            int valley = prices[0];
            int peak = prices[0];
            int maxprofit = 0;
            while (i < prices.length - 1) {
                while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                    i++;
                valley = prices[i];
                while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                    i++;
                peak = prices[i];
                maxprofit += peak - valley;
            }
            return maxprofit;
        }
    }

    /**
     * @return int
     * @date 2019/3/28 11:15
     * @Description 215. 数组中的第K个最大元素(review)
     * 快排+二分
     * @Param [nums, k]
     **/
    public int findKthLargest(int[] nums, int k) {
//        Queue<Integer> heap = new PriorityQueue<>();
//        for (int num : nums) {
//            heap.offer(num);
//            if (heap.size() > k) {
//                heap.poll();
//            }
//        }
//        return heap.poll();
        return search(0, nums.length - 1, k, nums);
    }

    public int search(int left, int right, int k, int[] nums) {
        int m = find(left, right, nums);
        if (m - left + 1 == k) return nums[m];
        else if (k < m - left + 1) return search(left, m - 1, k, nums);
        return search(m + 1, right, k - (m - left + 1), nums);
    }

    public int find(int left, int right, int[] nums) {
        int l = left, r = right, tmp = nums[left];
        while (l < r) {
            while (l < r && nums[r] < tmp) r--;
            while (l < r && nums[l] >= tmp) l++;
            if (l < r) {
                int t = nums[l];
                nums[l] = nums[r];
                nums[r] = t;
            }
        }
        nums[left] = nums[l];
        nums[l] = tmp;
        return l;
    }


}
