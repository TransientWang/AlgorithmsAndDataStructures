package test.greedychoice;

import org.junit.Test;
import test.testhelp.TestHelper;
import test.testhelp.safeTest;

import java.util.*;

/**
 * @author wangfy
 * @Description 贪心选择
 * @date 2018/10/31
 */

public class GreedyOne {

    @Test
    public void hh() {
        System.out.println(removeKdigits("9",
                1));
    }

    public String removeKdigits1(String num, int k) {
        char[] chars = num.toCharArray();


        return "0";

    }

    /**
     * @return java.lang.String
     * @Description 从数字中移走K个字符串  使得移除后的数字最小
     * 遍历字符串 如果当前比下一个大移除当前的  也就说是优先删除高位数大的的数字
     * 最后删除最前面有0的
     * @Param [num, k]
     * @Line 30
     **/
    public String removeKdigits(String num, int k) {
        StringBuilder stringBuilder = new StringBuilder(num);
        int i = 0;
        while (k > 0 && i < stringBuilder.length() - 1) {
            while (k > 0 && i < stringBuilder.length() - 1 && stringBuilder.charAt(i) > stringBuilder.charAt(i + 1)) {
                stringBuilder.deleteCharAt(i);
                k--;
                //像1234560  k =3 这样的数  遍历到6时候才会删除6此时为123450就需要回退 一位比较5与0 就剩0的有下面处理
                if (i > 0) {
                    i--;
                }
            }
            i++;

        }

        //删除最高位为0但是 只剩一个0由下面返回
        while (stringBuilder.length() > 1 && stringBuilder.charAt(0) == '0') {
            stringBuilder.deleteCharAt(0);
        }
        //有一位这种情况 会 直接 跳过筛选 则 K不为0直接截取就好
        String result = stringBuilder.toString().substring(0, stringBuilder.length() - k);
        if (result.length() == 0) {
            return "0";
        } else {
            return result;
        }
    }


    /**
     * @return boolean
     * @Description 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
     * '.' 匹配任意单个字符。
     * '*' 匹配零个或多个前面的元素。
     * 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
     * 贪心选择问题
     * @Param [s, p]
     * @Line 68
     **/
    @safeTest()
    public boolean myMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();

        if (p.length() == 1) return s.length() == 1 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');

        if (p.charAt(1) != '*') {
            if (s.isEmpty()) return false;
            return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && myMatch(s.substring(1), p.substring(1));
        }

        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
            if (myMatch(s, p.substring(2))) return true;
            s = s.substring(1);
        }

        return myMatch(s, p.substring(2));

    }

    /**
     * @return boolean
     * @Description 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符串（包括空字符串）。
     * @Param [s, p]
     * @Line 100
     **/
    @safeTest(1000)
    public boolean isMatch(String s, String p) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int testHelps(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (int) time;
    }

    @Test
    public void testOne() {

        Object[] params = new Object[]{new String[]{"1","2"}
                , new String[]{"3","4"}, new String[]{"5","6"}, new String[]{"7","8"}};
        Object[] param1s = new Object[]{100L
                ,100L};
        Object[] param1ss = new Object[]{"dad"
                ,"sdawd"};
        TestHelper.caseTest(getClass(), "isMatch", params);


    }


    /**
     * @Description
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0
     * ？找出所有满足条件且不重复的三元组。
     *
     * 那么我们来分析一下这道题的特点，要我们找出三个数且和为0，那么除了三个数全是0的情况之外，
     * 肯定会有负数和正数，我们还是要先fix一个数，然后去找另外两个数，
     * 我们只要找到两个数且和为第一个fix数的相反数就行了，既然另外两个数不能使用Two Sum的那种解法来找，
     * 如果能更有效的定位呢？我们肯定不希望遍历所有两个数的组合吧，所以如果数组是有序的，那么我们就可以
     * 用双指针以线性时间复杂度来遍历所有满足题意的两个数组合。
     * 我们对原数组进行排序，然后开始遍历排序后的数组，这里注意不是遍历到最后一个停止，
     * 而是到倒数第三个就可以了。这里我们可以先做个剪枝优化，就是当遍历到正数的时候就break，
     * 为啥呢，因为我们的数组现在是有序的了，如果第一个要fix的数就是正数了，
     * 那么后面的数字就都是正数，就永远不会出现和为0的情况了。
     * 然后我们还要加上重复就跳过的处理，处理方法是从第二个数开始，
     * 如果和前面的数字相等，就跳过，因为我们不想把相同的数字fix两次。
     * 对于遍历到的数，用0减去这个fix的数得到一个target，
     * 然后只需要再之后找到两个数之和等于target即可。
     * 我们用两个指针分别指向fix数字之后开始的数组首尾两个数，
     * 如果两个数和正好为target，则将这两个数和fix的数一起存入结果中。
     * 然后就是跳过重复数字的步骤了，两个指针都需要检测重复数字。
     * 如果两数之和小于target，则我们将左边那个指针i右移一位，
     * 使得指向的数字增大一些。同理，如果两数之和大于target，则我们将右边那个指针j左移一位，
     * 使得指向的数字减小一些
     * @Param [nums]
     * @return java.util.List<java.util.List<java.lang.Integer>>
     * @Line 294
     **/
    public List<List<Integer>> threeSumOne(int[] nums) {

        nums = Arrays.stream(nums).sorted().toArray();
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length <= 2) {
            return res;
        }

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num > 0) break;
            else if (i > 0 && num == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == -num) {
                    List<Integer> list = new ArrayList<>(3);
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                    while (left < right && nums[left] == nums[left + 1]) ++left;
                    while (left < right && nums[right] == nums[right - 1]) --right;
                    left++;
                    right--;
                } else if (nums[left] + nums[right] < -num) ++left;
                else --right;
            }
        }
        return res;
    }
}
