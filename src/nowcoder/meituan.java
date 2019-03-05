package nowcoder;

import org.junit.Test;

/**
 * @author wangfy
 * @Description TODO
 * @date 2019/3/5
 **/
public class meituan {
    /**
     * @return int
     * @date 2019/3/5 18:40
     * @Description 最大差值
     * 有一个长为n的数组A，求满足0≤a≤b<n的A[b]-A[a]的最大值。
     * <p>
     * 给定数组A及它的大小n，请返回最大差值。
     * <p>
     * 测试样例：
     * [10,5],2
     * 返回：0
     * @Param [A, n]
     **/
    public int getDis(int[] A, int n) {
        //leetcode 121. 买卖股票的最佳时机
        int min = A[0];
        int res = 0;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, A[i]);
            res = Math.max(A[i] - res, res);
        }
        return res;
    }

    @Test
    public void test() {
        int res = countPath(new int[][]{{0}, {1}, {2}}, 3, 1);
        System.out.println(res);
    }

    /**
     * @return int
     * @date 2019/3/5 19:42
     * 拜访问题dfs
     *
     * @Description 现在有一个城市销售经理，需要从公司出发，去拜访市内的商家，已知他的位置以及商家的位置，但是由于城市道路交通的原因，他只能在左右中选择一个方向，在上下中选择一个方向，现在问他有多少种方案到达商家地址。
     * <p>
     * 给定一个地图map及它的长宽n和m，其中1代表经理位置，2代表商家位置，-1代表不能经过的地区，0代表可以经过的地区，请返回方案数，保证一定存在合法路径。保证矩阵的长宽都小于等于10。
     * <p>
     * 测试样例：
     * [[0,1,0],[2,0,0]],2,3
     * 返回：2
     * @Param [map, n, m]
     **/
    public int countPath(int[][] map, int n, int m) {
        int count = 0;
        int xa = 0;
        int xb = 0;
        int ya = 0;
        int yb = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    xa = i;
                    ya = j;
                } else if (map[i][j] == 2) {
                    xb = i;
                    yb = j;
                }
            }
        }
        count += dfs(map, xa, ya, 1, 1, n, m);
        count += dfs(map, xa, ya, -1, -1, n, m);
        if (xa != xb && ya != yb) {
            count += dfs(map, xa, ya, 1, -1, n, m);
            count += dfs(map, xa, ya, -1, 1, n, m);
        }


        return count;
    }

    public int dfs(int[][] map, int x, int y, int r, int c, int row, int col) {
        if (x < 0 || x >= row || y < 0 || y >= col || map[x][y] == -2 || map[x][y] == -1) {
            return 0;
        }
        if (map[x][y] == 2) {
            return 1;
        }

        int count = 0;
        int t = map[x][y];
        map[x][y] = -2;

        count += dfs(map, x + r, y, r, c, row, col);
        count += dfs(map, x, y + c, r, c, row, col);

        map[x][y] = t;
        return count;

    }

}
