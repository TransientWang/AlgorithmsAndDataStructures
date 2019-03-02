package test.greedychoice;

import org.junit.Test;
import test.testhelp.TestHelper;
import test.testhelp.safeTest;

import java.util.Arrays;

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
     * @Description 给定一个整数数组，判断是否存在重复元素。
     *
     * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
     * @Param [nums]
     * @return boolean
     * @Line 59
     **/
    public boolean containsDuplicate(int[] nums) {
        String s ="dawdaw";
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
     * @date 2019/3/2 12:16
     * @return int
     * @Description 330. 按要求补齐数组(review)
     * @Param [nums, n] 
     **/
    public int minPatches(int[] nums, int n) {

        long max = 1;
        int cnt = 0;
        for (int i = 0;max<=n;) {
            if( i < nums.length && max >= nums[i]){
                max += nums[i];
                i++;

            } else {
                max += max;
                cnt++;
            }
        }
        return cnt;

    }
}
