package test.greedychoice;

import org.junit.Test;

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


    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();

        if (p.length() == 1) return s.length() == 1 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');

        if (p.charAt(1) != '*') {
            if (s.isEmpty()) return false;
            return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1));
        }

        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
            if (isMatch(s, p.substring(2))) return true;
            s = s.substring(1);
        }

        return isMatch(s,p.substring(2));

    }

    @Test
    public void testOne() {
        System.out.println(isMatch("abbbbbba",
                "ab*a"));

    }
}
