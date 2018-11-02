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


    /**
     * @return int
     * @Description TODO   没想明白
     * 这道题要求解码方法，跟之前那道 Climbing Stairs 爬梯子问题 非常的相似，但是还有一些其他的限制条件，
     * 比如说一位数时不能为0，两位数不能大于26，其十位上的数也不能为0，出去这些限制条件，根爬梯子基本没啥区别，
     * 也勉强算特殊的斐波那契数列，当然需要用动态规划Dynamci Programming来解。建立一位dp数组，长度比输入数组长多2，
     * 全部初始化为1，因为斐波那契数列的前两项也为1，然后从第三个数开始更新，对应数组的第一个数。对每个数组首先判断其是否为0，
     * 若是将改为dp赋0，若不是，赋上一个dp值，此时相当如加上了dp[i - 1], 然后看数组前一位是否存在，如果存在且满足前一位不是0，
     * 且和当前为一起组成的两位数不大于26，则当前dp值加上dp[i - 2], 至此可以看出来跟斐波那契数组的递推式一样，代码如下：
     * @Param [s]
     * @Line 110
     **/
    public int numDecodings(String s) {
        //如果s[k] != 0那么他就等于 s[k-1]s[k] +s[k-2]s[k]  也就是前两种情况的组合 。
        //如果s[k] ==0 那么他只能和s[k-1]组成一位编码数，也就是s[k-1]s[k]已经是固定的了 所以s[k] =s[k-2]s[k]
        //即s[k]先置为0然后在加上s[k-2]的数量 ，如果s[k] != 0 那么 如果他不是第二位（）s[k]就等于
        if (s.isEmpty() || (s.length() > 1 && s.charAt(0) == '0')) return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {        // 注意：是dp.length
            if (s.charAt(i - 1) == '0') {
                dp[i] = 0;
            } else {
                dp[i] = dp[i - 1];
            }
//            dp[i] = (s.charAt(i - 1) == '0') ? 0 : dp[i - 1];
            if (i > 1 && (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6'))) {
                dp[i] += dp[i - 2];
            }
        }
        Arrays.stream(dp).forEachOrdered(o -> System.out.print(o + " "));
        System.out.println();
        return dp[s.length()];
    }

    @Test
    public void testOne() {

        out.println(numDecodingsOne("12203"));

    }

    /**
     * @return int
     * @Description 这个是解码方法自己的实现
     * 一、分析问题的子结构
     * 如果长度为0 结果是0
     * 如果码串第一位为 0 则0不在解码范围之内 也不能与后面的数组进行组合  所以 直接返回 0
     * 剩下的情况类似排列组合计算索引i位置解码总数的的时候可以看成0到i-1 与 0到i-2的组合值
     * 因为前两位如果在10到20之间这两为就有三种解码方式，这三种需要与索引i位置的数字分别组合
     * 限界值就是如果前两位小于10或者前两位大于26的时候
     * 小于10的时候0在前说明明i-2与i-3绑定了他们两个解码数相同。这样i-1,i-2就只有一种组合。那我们只考虑i-1位置的就可以了，
     * 大于26时候0在后面的话不符合编码逻辑，因为假如 为40则没有这个编码 0也不能与后面的数字组和。
     * 所以i-1,i-2有0种有效组合则后面的全都是0了，索引i的位置也就置为0。这样所有情况就 都考虑到了
     * 二、递归的定义最优值
     * f(n)       0     ,n.length =0
     * f(n) ==    0     ,n.length >0 and n[0] ==0
     *  r[i] =  r[i-1] +r[i-2]        , s[i-1] !=0 and s[i-0] ==1 or  s[i-0] ==2 and s[i-2] <=6
     *       =  r[i-1]                  ,s[i-2]  == 0
     *       =  r[i-2]                  ,s[i-1] == 0 and s[i-2] <=2
     * f(n)       r[n.length-1]         ,other
     *
     * @Param [s]
     * @Line 147
     **/
    public int numDecodingsOne(String s) {
        if (s.length() == 0) return 0;
        if (s.startsWith("0")) return 0;
        // 边界条件
        int[] r = new int[s.length() + 1]; //辅助数组
        r[0] = 1;                        //初始化 r[0]方便计算
        int r1 = 0;  //计算r[i-1]
        int r2 = 0;  //计算r[i-2]
        for (int i = 1; i < r.length; i++) {      //注意i是从i=1开始的 这样在遍历s的索引时候 是从 i-1=0开始的
            //当计算第一位的时候，其实第一位到这里不会为0但是r[i-1]==1
            //s第一位到现在一定为 1 所以不用计算r2，即r[i] = 1+0 =1;

            r1 = s.charAt(i - 1) == '0' ? 0 : r[i - 1]; //当前一位i-1为0的时候 说明s[i-1]只能和s[i-2]绑定组成10或者20
                                                        //r[i-2]与r[i-1]只能组合出一种解法相当于他俩绑定了，
                                                        // 所以r[i-2]有几种解法，r[i]就有几种解法
            r2 = i > 1 ? Integer.valueOf(s.substring(i - 2, i)) : 0; //r2在i大于1的情况下计算s[i-2,i-1]的值，如果在11到26之间
                                                                    //说明s[i-2,i-1]两种解码方式，需要分别与是s[i]组合所以
                                                                    //r[i]=r[i-1]+r[i-2]
                                                                    //但是10也在范围内，因为0比较特殊索引为0的位置 在计算r1时为0
                                                                    //在这里如果排除在外就计算不到10 的这种情况  所以要包括10
            r[i] = (r2 >= 10 && r2 <= 26) ? r1 + r[i - 2] : r1 + 0;

        }
        Arrays.stream(r).forEachOrdered(o -> System.out.print(o + " "));
        System.out.println();
        return r[r.length - 1];
    }

}
