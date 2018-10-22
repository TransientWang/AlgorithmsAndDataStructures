package test.backtracking;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wangfy
 * @Description 回溯法
 * @date 2018/10/22
 */

public class backtracking {

    @Test
    public void test() {
        int[] w = {2, 5, 4, 2};
        int[] v = {6, 3, 5, 4};
        bagSolution(10, w, v);
    }

    /**
     * @return void
     * @Description 0-1背包问题回溯法
     * @Param [w, weight, Price]
     * @Line 15
     **/
    public void bagSolution(int w, int[] weight, int[] Price) {
        int num = weight.length;
        int cp = 0, bp = 0; //cp当前价值，bp
        int cw = 0, bestp = 0; //当前重量 ,当前最优价值
        int[] bestx = new int[num];
        int[] x = new int[num]; //x[i]表示第i个物品是否放入购物车
        int sumw = 0, sumv = 0;
        for (int i = 0; i < num; i++) {
            sumv += weight[i];
            sumv += Price[i];
        }

        if (sumv < w) {
            bestp = sumv;
            System.out.println("所有物品均可放入购物车，价值为：" + bestp);
        }
        backTrack(0, num - 1, w, Price, weight, bestx, x, cw, cp, bestp);

    }

    /**
     * @return int
     * @Description 计算上界, 当遇到装不下时，判断剩下没计算过的价值和是否大于当前最优值
     * 如果大于，说明还有意义，则可以继续往下计算
     * @Param [i, n]
     * i：第i中物品  ,n物品总数
     * @Line 31
     **/
    private int bound(int i, int n, int[] Price, int cp) {
        int rp = 0;
        for (int j = i; j < n; j++) {
            rp += Price[j];
        }

        return cp + rp;
    }

    /**
     * @return void
     * @Description 减枝函数
     * @Param [t, n, w, price, weight, bestx, x, cw, cp, bestp]
     * t:当前层数
     * n：最大层数
     * @Line 58
     **/
    private void backTrack(int t, int n, int w, int[] price, int[] weight, int[] bestx, int[] x, int cw, int cp, int bestp) {

        //如果当前成熟大于最大层数,已到达叶子结点
        if (t > n) {
            //将当前最优步数值赋值给bestx
            //保存当前最优解
            for (int i = 0; i <= n; i++) {
                bestx[i] = x[i];

            }
            //将当前最优值赋值给bestp
            //保存当前最优值
            bestp = cp;
            System.out.println(bestp);
            Arrays.stream(bestx).forEachOrdered(var -> System.out.print(var + " "));
            System.out.println();
            return;
        }
        //如果当前总重量+下一个物品的重量 大于总重量
        if (cw + weight[t] <= w) {
            //当前节点可以选择
            x[t] = 1;
            //计算当前总重量
            cw += weight[t];
            //计算当前总价值
            cp += price[t];
            //继续计算下一层
            //走左子树
            backTrack(t + 1, n, w, price, weight, bestx, x, cw, cp, bestp);
            //回溯回溯到上一个节点
            cw -= weight[t];
            cp -= price[t];
        }
        //当前节点+下一节点超重，计算上界，
        // 满足条件则极速计算右子树
        if (bound(t + 1, n, price, cp) > bestp) {
            x[t] = 0;
            backTrack(t + 1, n, w, price, weight, bestx, x, cw, cp, bestp);
        }


    }
}
