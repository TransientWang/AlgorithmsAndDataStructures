package test.dynamicProgramming;

/**
 * @author wangfy
 * @Description 矩阵连乘
 * @date
 */

public class MatrixMultiplication {


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
}
