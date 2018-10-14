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
}
