package test.greedychoice;

import org.junit.Test;

/**
 * @author wangfy
 * @Description TODO
 * @date 2018/10/15
 */

public class ActivitySelection {

    @Test
    public void test() {
        int[] s = new int[]{1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
        int[] f = new int[]{4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16};
//        recursiceSelector(s, f, 0, 11);
        GreedyActivitySelector(s, f);
    }


    /**
     * @return void
     * @Description 活动选择问题
     * 迭代
     * @Param [s, f, k, n]
     * @Line 23
     **/
    public void recursiceSelector(int[] s, int[] f, int k, int n) {
        System.out.println(k + 1);
        int m = k + 1;
        while (m < n && s[m] <= f[k]) {
            m += 1;
        }
        if (m < n) {
            recursiceSelector(s, f, m, n);
        }

    }

    /**
     * @return void
     * @Description 递归方法
     * @Param [s, f]
     * @Line 45
     **/
    public void GreedyActivitySelector(int[] s, int[] f) {
        int n = s.length;
        int k = 0;
        for (int i = 1; i < n; i++) {
            if (s[i] >= f[k]) {
                System.out.println(k + 1);
                k = i;
            }
        }
        System.out.println(k + 1);
    }
}
