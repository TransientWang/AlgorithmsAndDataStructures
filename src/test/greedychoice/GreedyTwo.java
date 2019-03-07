package test.greedychoice;

import org.junit.Test;
import test.testhelp.TestHelper;
import test.testhelp.safeTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author wangfy
 * @Description TODO
 * @date 2018/11/5
 */

public class GreedyTwo {
    /**
     * @return int
     * @Description 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * @Param [prices]
     * @Line 14
     **/
    @safeTest(300)
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int tmp = prices[0];
        int i = 0;
        int sum = 0;
        while (i < prices.length) {
            int num = prices[i];
            if (num > tmp) {
                sum += num - tmp;
                tmp = prices[i];
            } else if (num < tmp) {
                tmp = num;
            } else {
                i++;
            }
        }
        return sum;
    }

    @Test
    public void testOne() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        TestHelper.test(getClass(), "maxProfit", prices);

    }

    /**
     * @return boolean
     * @Description 给定一个整数数组，判断是否存在重复元素。
     * <p>
     * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
     * @Param [nums]
     * @Line 59
     **/
    public boolean containsDuplicate(int[] nums) {
        String s = "dawdaw";
        s.indexOf("l");
        if (nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return false;
        }
        nums = Arrays.stream(nums).sorted().toArray();
        int tmp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (tmp == num) return true;
            tmp = num;

        }
        return false;
    }

    /**
     * @return int
     * @date 2019/3/2 12:16
     * @Description 330. 按要求补齐数组(review)
     * @Param [nums, n]
     **/
    public int minPatches(int[] nums, int n) {

        long max = 1;
        int cnt = 0;
        for (int i = 0; max <= n; ) {
            if (i < nums.length && max >= nums[i]) {
                max += nums[i];
                i++;

            } else {
                max += max;
                cnt++;
            }
        }
        return cnt;

    }

    public void find(int count, int total, Integer[] lock, Double[] percent) {
        total -= 1;
        Collections.sort(Arrays.asList(lock));
        Arrays.stream(lock).forEachOrdered(a -> System.out.println(a));
        int[][] dp = new int[lock.length][total];
        dp[0][total] = lock[0];
        for (int i = 1; i < lock.length; i++) {
            if (dp[i][total] > dp[i - 1][total]) {

            }
        }

    }


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n;
        int k;
        while (s.hasNextInt()) {
            for (int i = 0; i < 5; i++) {
                System.out.println(s.nextInt());
            }
            System.out.println("ssss");
        }
//        while (s.hasNext()) {
//            n = s.nextInt();
//            k = s.nextInt();
//            int max = 0;//被重新叫醒后可得的最高分。
//            int sum = 0;//表示总的分数
//            int[] a = new int[n];
//            int[] t = new int[n];
//            for (int i = 0; i < n; i++) {
//                a[i] = s.nextInt();
//            }
//
//            int now = 0;
//            for (int i = 0; i < n; i++) {
//                t[i] = s.nextInt();
//                now += t[i] * a[i];
//            }
//
//            int res = now;
//            for (int i = 0; i < n; ) {
//                if (t[i] == 0) {
//                    now += 1 * a[i];
//                }
//                if (++i >= k) {
//                    res = Math.max(res, now);
//                    if (i - k < n && i - k >= 0) {
//                        if (t[i - k] == 0) {
//                            now -= 1 * a[i - k];
//                        }
//                    }
//                }
//
//            }
//            System.out.println(res);
//
//        }

    }


    /**
     * @date 2019/3/7 11:32
     * @return java.lang.String
     * @Description 316. 去除重复字母
     * @Param [s] 
     **/
    public String removeDuplicateLetters(String s) {
        char[] ch = s.toCharArray();
        boolean[] use = new boolean[26];
        char[] result = new char[26];
        int cur = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            int index = c - 'a';
            if (use[index]) {
                continue;
            }
            for (int j = cur - 1; j >= 0; j--) {
                char d = result[j];
                if (d - c > 0 && s.substring(i).contains(d + "")) {
                    cur = j;
                    use[d - 'a'] = false;
                    continue;
                }
                break;
            }
            result[cur++] = c;
            use[index] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : result) {
            if (c == '\u0000') {
                continue;
            }
            sb.append(c);
        }

        return sb.toString();
    }

    @Test
    public void sssss() {
        System.out.println(removeDuplicateLetters("cbacdcbc"));

    }
}
