package test.greedychoice;

import org.junit.Test;

/**
 * @author wangfy
 * @Description TODO
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
}
