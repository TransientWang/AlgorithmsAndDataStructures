package test.backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangfy
 * @Description TODO
 * @date 2019/3/26
 **/
public class BackTrackingOne {
    /**
     * @date 2019/3/26 12:15
     * @return java.util.List<java.util.List<java.lang.Integer>>
     * @Description 216. 组合总和 III(reveiw)
     * @Param [k, n]
     **/
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        backTrack(res, k, n, new ArrayList<Integer>(), 0, 1, Math.min(10, n));
        return res;
    }

    private void backTrack(List<List<Integer>> res, int k, int n, List<Integer> list, Integer sum, Integer begin, Integer end) {
        if (list.size() == k && sum == n) {
            res.add(list);
            return;
        }
        for (int i = begin; i < end; i++) {
            List<Integer> nList = new ArrayList<>(list);
            nList.add(i);
            backTrack(res, k, n,nList , sum + i, i + 1, end);
        }

    }

    @Test
    public void test() {
        List<List<Integer>> res = combinationSum3(3, 7);
        res.forEach(o -> o.forEach(var -> System.out.println(var)));


    }
}
