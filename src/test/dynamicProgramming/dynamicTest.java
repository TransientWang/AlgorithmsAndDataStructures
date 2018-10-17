package test.dynamicProgramming;

import org.junit.Test;


public class dynamicTest {
    @Test
    public void test1() {
        //相应长度钢条的价格
        int[] p = new int[]{1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
//        System.out.println(menoizedCutRod(p, 10));
        System.out.println(bottomUpRod(p, 10));
    }

    //钢条切割问题
    //带备忘的自顶向下法
    //将钢条从左边割下长度为I的一段，只对右边剩下的长度为n-i的一端继续进行切割（递归求解），
    //对左边的一端则不在进行切割。即问题分解的方式为：将长度为n的钢条分解为左边开始一段
    //以及剩余部分继续分解的结果，这样，不做任何切割的方案就可以描述为：第一段的长度为n
    //收益为Pn,剩余部分长度为0.对应的收益为r0=0。
    //rn = max(pi+rn-i)(1<=i<=n)
    //此公式中，原问题的最优解只包含一个相关的子问题的解（右端剩余部分）的解，而不是两个。
    public int menoizedCutRod(int[] p, int n) {
        //数组r代表长度为n的钢条的价格，所有可能价格，包括n=0(钢条长度为0)所以r的长度为n+1。
        int[] r = new int[p.length + 1];
        for (int i = 0; i < p.length + 1; i++) {
            r[i] = -1;
        }
        return memoizedCutRodAux(p, n, r);
    }

    public int memoizedCutRodAux(int[] p, int n, int[] r) {
        //如果r[n]不为-1。说明已经计算过长度为n的钢条的最优价格，直接返回
        if (r[n] >= 0) {
            System.out.println("已计算r[" + n + "]:" + r[n]);
            return r[n];
        }
        int q = -1;
        //n=长度为0的时候直接返回 0
        if (n == 0) {
            q = 0;
        } else {
            //把钢条切割成两段，每一段再递归的切割求优解
            //想当于最开始只有一寸，然后累加知道n
            //但是在这里是由n开始的，所以是自定向下的
            //计算切割第i寸钢条和n-i的最优解的和 的结果和 q 的最大値
            for (int i = 1; i <= n; i++) {
                q = Math.max(q, p[i - 1] + memoizedCutRodAux(p, n - i, r));
            }
        }
        r[n] = q;
        return q;
    }

    //自底向上求解
    public int bottomUpRod(int[] p, int n) {
        //保存最优解
        int[] r = new int[n + 1];
        int q;
        //求解i寸的最优解
        for (int i = 1; i <= n; i++) {
            q = -1;
            //在从0到j长度里通过比较求出当前最优解
            for (int j = 1; j <= i; j++) {
                q = Math.max(q, p[j - 1] + r[i - j]);
            }
            r[i] = q;
        }
        return r[n];
    }


    @Test
    public void test2() {
        int[] v = {8, 10, 6, 3, 7, 2};
        int[] w = {4, 6, 2, 2, 5, 1};
        System.out.println(bagSolution(w, v, 12));
    }

    /**
     * @return void
     * @Description 0-1背包问题
     * w:物品的重量  v:物品的价值 j:背包的容量
     * @Param [w, v, j]
     * @Line 77
     **/
    public int bagSolution(int[] w, int[] v, int j) {

        if (w.length > 0 && w[0] > j) {
            return 0;
        }
        int[][] m = new int[w.length][j];
        j -= 1;
        m[0][j] = v[0];
        int q = 0;
        for (int i = 1; i < w.length; i++) {
            if (m[i - 1][j] > m[i - 1][j - w[i]] + v[i]) {
                m[i][j] = m[i - 1][j];
            } else {
                m[i][j - w[i]] = m[i - 1][j - w[i]] + v[i];
                if (m[i][j - w[i]] > q) {
                    q = m[i][j - w[i]];
                }
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int k = 0; k < j + 1; k++) {
                System.out.print(m[i][k] + " ");
            }
            System.out.println();
        }
        return q;
    }

    @Test
    public void test() {
        int[] arr = new int[]{-1, 1, -10, 2, -1, 3, 4, -1, 5, -2, -3};
        int[] arr1 = new int[]{-3, -2, -3};
        maxArray(arr1);
    }

    /**
     * @Description 最大自数组和问题
     * 采用动态规划 考虑一个问题，只要当前数组加上下一个值的和大于0就说明和是增加的不论和是否减小，
     * 也就是说明负数不可能作为最大自数组的开始，因为它一定会让这个数组减小，
     * 当前面子数组的和为小于0时，加上后面的自数组也一定会让总的和变小。
     * 所以可以直接放弃，从下一位从新计算新的和。
     * 由于只遍历了一遍数组，所以时间复杂度为分f（o）
     * @Param [arr]
     * @return void
     * @Line 126
     **/
    public void maxArray(int[] arr) {
        //当前计算的自数组最大值
        int sum = 0;
        //保存整个数组范围内求得的最大自数组和
        int Max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            //先求取当前最大自数组的和
            sum += arr[i];
            //如果当前和大于整个数组范围内求得的最大自数组的和Max，即可更新Max
            Max = Math.max(sum, Max);
            //如果当前计算的自数组的和已经小于0，在加上以后求得的最大自数组也只会让Max变小
            //再让其参与计算已经没有意义。所以可以把当前最大自数组和置为0重新开始计算
            if (sum < 0) {
                sum = 0;
            }

        }
        System.out.println(Max);

    }

    /**
     * @Description 最长公共个子序列问题
     * 用一个二维数组做辅助
     * 分为三种情况，设字符串chars1长度为m,chars2为n。
     * 1、当chars1[m]==chars2[n]时 找到一个公共子序列chars[1]
     * 2、当chars1[m]!=chars2[n]时 2、公共子序列必然在chars1[m-1]与chars2[n]或者3、chars1[m]与chars2[n-1]之间
     * @Param [chars1, chars2]
     * @return void
     * @Line 158
     **/
    public void LSC(char[] chars1, char[] chars2) {
        //辅助数组 记录子序列的长度
        int[][] chess = new int[chars1.length][chars2.length];
        for (int i = 1; i < chars1.length; i++) {
            for (int j = 1; j < chars2.length; j++) {
                //从序列的最后一位开始
                if (chars1[i] == chars2[j]) {
                    //如果相等，最长子序列长度加一
                    chess[i][j] = chess[i - 1][j - 1] + 1;
                } else {
                    //不行等，当前序列长度等于上面两种情况里大的一种
                    chess[i][j] = Math.max(chess[i - 1][j], chess[i][j - 1]);
                }

            }
        }
        for (int i = 0; i < chess.length; i++) {
            System.out.println();
            for (int j = 0; j < chess[i].length; j++) {
                System.out.print(chess[i][j] + " ");
            }

        }
        System.out.println();
        StringBuilder stringBuilder = new StringBuilder();
        int k, j;
        k = chars1.length - 1;
        j = chars2.length - 1;
        //根据辅助数组来找出最长子序列
        while (k >= 1 && j >= 1) {
            if (chars1[k] == chars2[j]) {
                stringBuilder.append(chars1[k]);
                k--;
                j--;
            } else {
                //如果上面情况2里的哪一种情况较大，就把相应的序列减位置减小
                if (chess[k][j - 1] > chess[k - 1][j]) {
                    j--;
                } else {
                    k--;
                }
            }

        }

        System.out.println(stringBuilder.reverse());
    }

    @Test
    public void LscTest() {
        char[] chars1 = "awangkaugt".toCharArray();
        char[] chars2 = "cwangznhyd".toCharArray();
        LSC(chars1, chars2);

    }
}