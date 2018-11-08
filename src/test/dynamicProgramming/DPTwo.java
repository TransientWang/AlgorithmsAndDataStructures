package test.dynamicProgramming;

import org.junit.Test;

import java.util.logging.Level;

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

    public int robTwo(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], dp[1]);
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i]);
        }

        return dp[nums.length - 1];


    }

    @Test
    public void testOne() {
        System.out.println(robTwo(new int[]{2, 7, 9, 3, 1}));
    }
}
