package test.dynamicProgramming;

import org.junit.Test;

import static java.lang.System.in;
import static java.lang.System.out;


import java.util.*;

/**
 * @author wangfy
 * @Description 矩阵连乘
 * @date
 */

public class DpOne {


    /**
     * @return void
     * @Description 矩阵乘法
     * @Param [a, b]
     * @Line 17
     **/
    public int[][] matrix(int a[][], int[][] b) {
        if (a.length != b[0].length) {
            return null;
        }

        //矩阵c的行数
        int x = a.length;
        //矩阵c的列数
        int y = b[0].length;
        //矩阵相乘的中间乘数 也就是b的行数
        int mid = b.length;
        //矩阵c
        int[][] c = new int[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                for (int k = 0; k < mid; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return c;


    }

    @Test
    public void test() {

    }

    /**
     * @return int
     * @Description 1000里的超级回文
     * 1 4 9  121 484
     * @Param [L, R]
     * @Line 52
     **/
    public int superpalindromesInRange() {
        //String L, String R
//        var list = new ArrayList<Integer>(50);
//        for (int i = R; i >= L; i--) {
//
//        }
        return (int) Math.sqrt(484);
    }

    /**
     * @return int[]
     * @Description 两数之和 用一个map保存target - nums[i]
     * 如果map里有nums[i]
     * 就找到了
     * @Param [nums, target]
     * @Line 76
     **/
    public int[] twoSum(int[] nums, int target) {
        int[] as = new int[2];
        Map<Integer, Integer> l = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!l.containsKey(nums[i])) {
                l.put(target - nums[i], i);
            } else {

                as[1] = i;
                as[0] = l.get(nums[i]);


            }

        }
        return as;
    }

    @Test
    public void testOne() {

        out.println(numDecodings("10101"));

    }
    /**
     * @Description TODO   没想明白
     * 这道题要求解码方法，跟之前那道 Climbing Stairs 爬梯子问题 非常的相似，但是还有一些其他的限制条件，
     * 比如说一位数时不能为0，两位数不能大于26，其十位上的数也不能为0，出去这些限制条件，根爬梯子基本没啥区别，
     * 也勉强算特殊的斐波那契数列，当然需要用动态规划Dynamci Programming来解。建立一位dp数组，长度比输入数组长多多2，
     * 全部初始化为1，因为斐波那契数列的前两项也为1，然后从第三个数开始更新，对应数组的第一个数。对每个数组首先判断其是否为0，
     * 若是将改为dp赋0，若不是，赋上一个dp值，此时相当如加上了dp[i - 1], 然后看数组前一位是否存在，如果存在且满足前一位不是0，
     * 且和当前为一起组成的两位数不大于26，则当前dp值加上dp[i - 2], 至此可以看出来跟斐波那契数组的递推式一样，代码如下：
     *
     *
     * @Param [s]
     * @return int
     * @Line 110
     **/
    public int numDecodings(String s) {
        if (s.isEmpty() || (s.length() > 1 && s.charAt(0) == '0')) return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; ++i) {        //dp[i-1] ==0 的时候 dp[i] = dp[i-2]
            dp[i] = (s.charAt(i - 1) == '0') ? 0 : dp[i - 1];
            if (i > 1 && (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6'))) {
                dp[i] += dp[i - 2];
            }
        }
        Arrays.stream(dp).forEachOrdered(o -> System.out.print(o +" "));
        System.out.println();
        return dp[s.length()];
    }


}
