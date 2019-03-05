package nowcoder;

import java.util.Scanner;

/**
 * @author wangfy
 * @Description
 * 牛牛以前在老师那里得到了一个正整数数对(x, y), 牛牛忘记他们具体是多少了。
 * 但是牛牛记得老师告诉过他x和y均不大于n, 并且x除以y的余数大于等于k。
 * 牛牛希望你能帮他计算一共有多少个可能的数对。
 * 输入包括两个正整数n,k(1 <= n <= 10^5, 0 <= k <= n - 1)。
 *
 * 输出描述:
 * 对于每个测试用例, 输出一个正整数表示可能的数对数量。
 *
 * 输入例子1:
 * 5 2
 *
 * 输出例子1:
 * 7
 * @date 2019/3/5
 **/
public class a1 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        long n = in.nextInt();
        int k = in.nextInt();
        long count = 0;
        if (k == 0) {
            System.out.println(n * n);
        } else {
            for (int i = k + 1; i <= n; i++) {
                count += ((n / i) * (i - k));
                if (n % i >= k) {
                    count += (n % i - k + 1);
                }
            }
            System.out.println(count);
        }


    }


}
