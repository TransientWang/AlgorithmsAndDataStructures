package test.dynamicProgramming;

import org.junit.Test;

/**
 * @author wangfy
 * @Description 动态规划
 * @date 2018/11/8
 */

public class DPTwo {


    /**
     * @return int
     * @Description 爬楼梯
     * @Param [n]
     * @Line 21
     **/
    public int climbStairs(int n) {
        if (n == 1) return 1;

        int r[] = new int[n];
        r[0] = 1;
        r[1] = 2;
        for (int i = 2; i < n; i++) {
            r[i] = r[i - 2] + r[i - 1];
        }
        return r[n - 1];
    }

    public int maxProfit(int[] prices) {
        if (prices.length == 0 || prices.length == 1) return 0;
        int result = 0;
        int min = prices[0];
        for (int i = 0; i < prices.length; i++) {

            result = prices[i] - min < result ? result : prices[i] - min;
            min = Math.min(min, prices[i]);
        }
        return result;
    }

    /**
     * @return int
     * @Description 最大子序列
     * 当前最大值sum加上 当前值 还比当前值小的时候
     * 说明sum已经小于0 没有再计算的意义
     * sum重新初始化为当前值就可以了
     * @Param [nums]
     * @Line 62
     **/
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        int result = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] < nums[i]) sum = nums[i];
            else sum += nums[i];
            if (sum > result) result = sum;
        }
        return result;
    }

    /**
     * @return int
     * @Description 打家劫舍
     * 这这个方法的思想 是挨家挨户都偷一遍
     * 偷当前户的时候 选出从第一户到 前两户能偷得的最大值
     * 求得得最大値与当前户相加
     * @Param [nums]
     * @Line 71
     **/
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        for (int i = 2; i < nums.length; i++) {
            int max = nums[0];
            for (int j = 0; j <= i - 2; j++) {
                max = max > dp[j] ? max : dp[j];
            }
            dp[i] = max + nums[i];
        }
        return Math.max(dp[nums.length - 2], dp[nums.length - 1]);
    }

    /**
     * @return int
     * @Description 打家劫舍
     * 动态规划思想
     * dp数组保存小偷 偷到第几户 能得到的最大财产
     * 如果偷当前户 加上 前两户之前得到的最大值
     * 比 不偷当前户（不偷当前户的所能得到的最大值，跟偷前一户的一样）
     * 大就偷当前的 ，否则就不偷当前的
     * @Param [nums]
     * @Line 92
     **/
    public int robTwo(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[nums.length - 1];


    }

    /**
     * @return int
     * @date 2019/3/8 14:55
     * @Description 264. 丑数 II(review)
     * @Param [n]
     **/
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i = 1; i < n; i++) {
            int min = Math.min(2 * ugly[i1], Math.min(3 * ugly[i2], 5 * ugly[i3]));

            if (min == 2 * ugly[i1]) {
                i1++;
            }
            if (min == 3 * ugly[i2]) {
                i2++;
            }
            if (min == 5 * ugly[i3]) {
                i3++;
            }
            ugly[i] = min;
        }
        return ugly[n - 1];
    }

    /**
     * @date 2019/3/8 15:26
     * @return int
     * @Description 313. 超级丑数(review)
     * @Param [n, primes]
     **/
    public int nthSuperUglyNumber(int n, int[] primes) {

        int[] mins = new int[primes.length];
        int[] ugly = new int[n];
        ugly[0] = 1;
        int[] hp = new int[primes.length];
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                mins[j] = primes[j] * ugly[hp[j]];
                min = Math.min(min, mins[j]);
            }

            for (int k = 0; k < primes.length; k++) {
                if (min == primes[k] * ugly[hp[k]]) {
                    hp[k]++;
                }
            }
            ugly[i] = min;
        }

        return ugly[n - 1];
    }

    @Test
    public void testOne() {

        System.out.println(nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}));
    }
}
