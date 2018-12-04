package test.sort;

import org.junit.Test;

import java.util.*;

/**
 * @author wangfy
 * @Description TODO
 * @date 2018/12/3
 */

public class Test2 {
    @Test
    public void test() {
        int t = kthSmallest(new int[][]{
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        }, 8);
        System.out.println(t);
    }

    public static List<Integer> countSmaller(int[] nums) {
        List<Integer> tmp = new ArrayList<>(nums.length);
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            if (Collections.binarySearch(tmp, num) == -1) {

            }
            System.out.println();

        }
        return tmp;
    }

    /**
     * @Description 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
     * 请注意，它是排序后的第k小元素，而不是第k个元素。
     * 这种找第几个的题目，基本都用堆来实现
     * @Param [matrix, k]
     * @return int
     * @Line 41
     **/
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2)
                    return 1;
                else if (o1 > o2)
                    return -1;
                else
                    return 0;
            }
        });
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int matrixs = matrix[i][j];
                if (maxHeap.size() < k) {
                    maxHeap.add(matrixs);
                } else if (matrixs > maxHeap.peek()) {
                    continue;
                } else {
                    maxHeap.poll();
                    maxHeap.add(matrixs);
                }
            }
        }

        return maxHeap.peek();
    }
}
