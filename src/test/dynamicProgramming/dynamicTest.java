package test.dynamicProgramming;

import com.sun.source.tree.IfTree;
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
        maxArray(arr);
        System.out.println(maxArray1(arr, arr.length));
        System.out.println(maxArray2(arr));

    }

    /**
     * @return void
     * @Description 最大子数组和问题
     * 采用动态规划 考虑一个问题，只要当前数组加上下一个值的和大于0就说明和是增加的不论和是否减小，
     * 也就是说明负数不可能作为最大自数组的开始，因为它一定会让这个数组减小，
     * 当前面子数组的和为小于0时，加上后面的自数组也一定会让总的和变小。
     * 所以可以直接放弃，从下一位从新计算新的和。
     * 由于只遍历了一遍数组，所以时间复杂度为分O（N）
     * @Param [arr]
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
     * @return int
     * @Description 最大自数组问题
     * 当前currSum加上下一个数 还没有下一个数大的时候（说明之前保存的最大自数组和还没有当前的
     * 大，而且之前的最大值已经被保存，可以放心的将当前的数保存到当前最大值里面），就把currSum设为下一个数
     * maxSum 没有currSum大时，更新cmaxSum的值
     * @Param [a, n]
     * @Line 160
     **/
    public int maxArray1(int[] a, int n) {
        int currSum = 0;
        int maxSum = a[0];
        for (int j = 0; j < n; j++) {
            currSum = (a[j] > currSum + a[j]) ? a[j] : currSum + a[j];
            maxSum = (maxSum > currSum) ? maxSum : currSum;
        }
        return maxSum;
    }

    /**
     * @return int
     * @Description 最大字数组
     * 三个for循环
     * 第一个循环从前到后遍历数组
     * 第二个循环，从第一个循环到的位置开始一直到数组结束
     * 第三个循环从，第一个循环到的位置开始，一直到第二个循环到的位置（想当与具体求出每一个子数组）
     * 这样就遍历了整个数组里所有的子数组了然后在进行比较
     * @Param [a]
     * @Line 180
     **/
    public int maxArray2(int[] a) {
        var sum = 0;
        var maxSum = a[0];
        for (var i = 0; i < a.length; i++) {
            for (var j = i; j < a.length; j++) {
                for (var k = i; k < j; k++) {
                    sum += a[k];
                }
                maxSum = sum > maxSum ? sum : maxSum;
                sum = 0;
            }
        }

        return maxSum;

    }

    /**
     * @return void
     * @Description 最长公共个子序列问题
     * 用一个二维数组做辅助
     * 分为三种情况，设字符串chars1长度为m,chars2为n。
     * 1、当chars1[m]==chars2[n]时 找到一个公共子序列chars[1]
     * 2、当chars1[m]!=chars2[n]时 2、公共子序列必然在chars1[m-1]与chars2[n]或者3、chars1[m]与chars2[n-1]之间
     * @Param [chars1, chars2]
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

    @Test
    public void palindromeTest() {
        String test = "w12234543221abcdcbiuoiuo";
        System.out.println(findPalindrome(test));
    }

    /**
     * @return java.lang.String
     * @Description 动态规划寻找字符串里的最长回文子串
     * realResult为最长回文串默认值为palindrome[0]（没有回文就返回第一个就好了），result为当前最长子串
     * 从字符串的palindrome[1]开始，用一个h变量记录回文的边界
     * 当以i为中心的两个对称的字符串相等时就更新当前最长回文的值
     * 当不满足条件时候，就把i右移h-1位（因为h等于1，所以要减去1）
     * 再把h重置为1，再比较当前最长回文串和最长回文realResult的长度来更新最长回文串
     * 最后返回最长回文串
     * 时间复杂度：因为只遍历了一遍字符串，while只是判断回文长度，所以时间复杂度为O（n）
     * @Param [palindrome]
     * @Line 280
     **/
    public String findPalindrome(String palindrome) {
        String result = "";
        String realResult = palindrome.substring(0, 1);
        int h = 1;
        for (int i = 1; i < palindrome.length(); i++) {
            while ((i + h) < palindrome.length() && (i - h) >= 0 && palindrome.charAt(i + h) == palindrome.charAt(i - h)) {
                result = palindrome.substring(i - h, i + h + 1);
                h++;
            }
            i = i + h - 1;
            h = 1;
            realResult = result.length() > realResult.length() ? result : realResult;
        }
        return realResult;
    }


    @Test
    public void xtest() {
        System.out.println(cornSolution(3));
    }

    /**
     * @return int
     * @Description TODO 硬币兑换问题没解决
     * @Param [num]
     * @Line 324
     **/
    public int cornSolution(int num) {
        int[] arr = new int[100 + 1];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = 1;
//        }
        arr[1] = 1;
        arr[2] = 1;
        int[] cor = new int[]{1, 2, 5, 10};

        for (int i = 1; i <= num; i++) {
            for (int j = 0; j < cor.length; j++) {
                if (i >= cor[j])
                    arr[i] = arr[i] + arr[i - cor[j]];
            }

        }

        Arrays.stream(arr).forEachOrdered(var -> System.out.print(var + " "));
        return 0;
    }

    @Test
    public void tests() {
//        uniquePaths1(1, 2);
        int[][] path = {{0, 1, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] pa = {{1, 0}, {0, 0}};
        System.out.println(uniquePathsWithObstacles(pa));
    }

    /**
     * @return void
     * @Description 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * 一、定义最优解的子结构、
     *     先考虑 m=1,n>1的情况也就是说只有一行  所以这行上的每一步都只有一种解法。解  ：1
     *            当m >1,n==1是 情况同上
     *      定义一个二维数组代表从m到n有多少种路径，那么 下一步路径数 就是m-1,n 与m,n-1 的和
     * 二、递归的定义最优解：
     *
     *
     *              +
     *              |  m=1 || n==1   path[i][j] == 1
     *      f(m,n) =|  f(m,n) = f(m-1,n)+f(m,n-1)
     *              |
     *              +
     * @Param [m, n]
     * @Line 357
     **/
    public void uniquePaths1(int m, int n) {
        int[][] path = new int[m][n];

        for (int i = 0; i < m; i++) {
            path[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            path[0][i] = 1;
        }
        if (n > 1)
            path[0][1] = 1;
        if (m > 1)
        path[1][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                path[i][j] = path[i - 1][j] + path[i][j - 1];

            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(path[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * @Description 路径问题   输入有1代表此路不通
     * @Param [obstacleGrid]
     * @return int
     * @Line 407
     **/
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = -1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(obstacleGrid[i][j] + " ");
            }
            System.out.println();
        }
        if (obstacleGrid[0][0] ==-1){
            return 0;
        }
        if (m == 1 || n == 1) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (obstacleGrid[i][j] == -1) {
                        return 0;
                    }
                }
            }
            return 1;
        }

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] != -1)
                obstacleGrid[i][0] = 1;
            else {
                obstacleGrid[i][0] = 0;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] != -1)
                obstacleGrid[0][i] = 1;
            else {
                obstacleGrid[0][i] = 0;
                break;
            }
        }


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int x = obstacleGrid[i - 1][j] == -1 ? 0 : obstacleGrid[i - 1][j];
                int y = obstacleGrid[i][j - 1] == -1 ? 0 : obstacleGrid[i][j - 1];
                if (obstacleGrid[i][j] != -1)
                    obstacleGrid[i][j] = x + y;
                else
                    obstacleGrid[i][j] = 0;
            }
        }

//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(obstacleGrid[i][j] + " ");
//            }
//            System.out.println();
//        }

        return obstacleGrid[m - 1][n - 1];
    }

    @Test
    public void tesss() {
        int[][] ss = {{1, 3, 1}, {1, 5, 1}};
        System.out.println(minPathSum(ss));
    }

    /**
     * @Description 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     *
     * 说明：每次只能向下或者向右移动一步。
     * 一、分析最优解的结构特征
     *     假设极端情况  当只有一行一列时  最优解就是grid[0][0]
     *     有一行或者一列时 最优解 就是 这一行或者一列的和
     *     在多行多列的情况时从自顶点向下来看 到每个点的最小距离为能到达它的两个前顶点中最小的一个解
     * @Param [grid]
     * @return int
     * @Line 489
     **/
    public int minPathSum(int[][] grid) {
        int[][] res = new int[grid.length][grid[0].length];
        res[0][0] = grid[0][0];

        for (int j = 1; j < grid.length; j++) {
            res[j][0] = grid[j][0] + res[j - 1][0];
        }

        for (int i = 1; i < grid[0].length; i++) {
            res[0][i] = grid[0][i] + res[0][i - 1];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                res[i][j] = res[i - 1][j] < res[i][j - 1]?res[i - 1][j] + grid[i][j]:res[i][j - 1] + grid[i][j];
            }
        }
        return res[grid.length-1][grid[0].length-1];
    }
}