package test.stringquestion;

import org.junit.Test;
import test.testhelp.TestHelper;
import test.testhelp.safeTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangfy
 * @Description 字符串
 * @date 2018/11/5
 */

public class StringQuestionOne {

    @Test
    public void testOne() {
        int[] ints = {1, 2, 3, 4, 5, 6, 7};
        TestHelper.test(getClass(), "rotate2", ints, 3);
    }
    /**
     * @Description 可以采取反转的方法，先反转前n-k个元素，再反转后k个元素，
     * 然后再将整个数组反转，就能得到该数组旋转k个元素的结果了
     * @Param [nums, k]
     * @return void
     * @Line 27
     **/
    @safeTest(300)
    public void rotate(int[] nums, int k) {
        if (k > nums.length)
            k %= nums.length;
        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
//        Arrays.stream(nums).forEachOrdered(o -> System.out.print(o + " "));
    }

    public void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int tmp = nums[left];
            nums[left++] = nums[right];
            nums[right--] = tmp;
        }
    }

    public void rotate2(int[] nums, int k) {
        if (nums.length == 0 || (k %= nums.length) == 0) {
            return;
        }
        int length = nums.length;
        int start = 0;
        int i = 0;
        int cur = nums[i];
        int cnt = 0;

        while (cnt++ < length) {
            i = (i + k) % length; //计算索引新的位置
            int t = nums[i]; //临时保存
            nums[i] = cur;    //
            if (i == start) {
                ++start;
                ++i;
                cur = nums[i];
            } else {
                cur = t;
            }
        }
    }

}
