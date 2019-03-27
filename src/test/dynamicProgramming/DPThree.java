package test.dynamicProgramming;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wangfy
 * @Description TODO
 * @date 2019/3/22
 **/
public class DPThree {
    /**
     * @return int
     * @date 2019/3/22 11:36
     * @Description 377. 组合总和 Ⅳ
     * @Param [nums, target]
     **/
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int num : nums) {
                if (i + num <= target) {
                    dp[i + num] += dp[i];
                }
            }
        }
        return dp[target];

    }

    /**
     * @return int
     * @date 2019/3/24 14:46
     * @Description 413. 等差数列划分
     * @Param [A]
     **/
    public int numberOfArithmeticSlices(int[] A) {
        int r = 0, res = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                r++;
                res += r;
            } else {
                r = 0;
            }
        }
        return res;
    }

    /**
     * @return int
     * @date 2019/3/25 16:16
     * @Description 221. 最大正方形(review)
     * @Param [matrix]
     **/
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int res = 0;
//        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
//        for (int x = 1; x < matrix.length + 1; x++) {
//            for (int y = 1; y < matrix[0].length + 1; y++) {
//                if (matrix[x - 1][y - 1] == '1') {
//                    dp[x][y] = Arrays.stream(new int[]{dp[x - 1][y], dp[x][y - 1], dp[x - 1][y - 1]}).min().getAsInt() + 1;
//                    res = Math.max(dp[x][y], res);
//                }
//            }
//        }
        int[] dp = new int[matrix[0].length];
        for (int x = 0; x < matrix.length; x++) {
            int len = 0;
            for (int y = 0; y < matrix[0].length; y++) {
                dp[y] = matrix[x][y] == '1' ? dp[y] + 1 : 0;
                len = dp[y] > res ? len + 1 : 0;
                if (len == res + 1) {
                    res++;
                    len = 0;
                }
            }
        }
        return res * res;
    }

    /**
     * @return int
     * @date 2019/3/27 8:42
     * @Description 322. 零钱兑换(reveiw)
     * @Param [coins, amount]
     **/
    public int coinChange(int[] coins, int amount) {
        int[] money = new int[amount + 1];
        Arrays.fill(money, 1, amount + 1, amount + 1);
        for (int coin : coins) {
            for (int i = coin; i < amount + 1; i++) {
                money[i] = Math.min(money[i - coin] + 1, money[i]);
            }
        }
        return money[amount] == amount + 1 ? -1 : money[amount];
    }

    @Test
    public void test() {
        char[][] arr = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};

        maximalSquare(arr);
    }

}
