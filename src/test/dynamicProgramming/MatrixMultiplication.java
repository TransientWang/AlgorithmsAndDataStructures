package test.dynamicProgramming;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void test(){

    }
    /**
     * @Description 1000里的超级回文
     * 1 4 9  121 484
     * @Param [L, R]
     * @return int
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
     * @Description 两数之和 用一个map保存target - nums[i]
     *       如果map里有nums[i]
     *       就找到了
     * @Param [nums, target]
     * @return int[]
     * @Line 76
     **/
    public int[] twoSum(int[] nums, int target) {
        int[] as = new int[2];
        Map<Integer,Integer> l = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!l.containsKey(nums[i])){
                l.put(target - nums[i],i);
            }else {

                as[1] = i;
                as[0] = l.get(nums[i]);


            }

        }
        return as;
    }
}
