package nowcoder;

import java.util.*;

/**
 * @author wangfy
 * @Description 为了不断优化推荐效果，今日头条每天要存储和处理海量数据。假设有这样一种场景：我们对用户按照它们的注册时间先后来标号，对于一类文章，每个用户都有不同的喜好值，我们会想知道某一段时间内注册的用户（标号相连的一批用户）中，有多少用户对这类文章喜好值为k。因为一些特殊的原因，不会出现一个查询的用户区间完全覆盖另一个查询的用户区间(不存在L1<=L2<=R2<=R1)。
 *
 *
 *
 * 输入描述:
 * 输入： 第1行为n代表用户的个数 第2行为n个整数，第i个代表用户标号为i的用户对某类文章的喜好度 第3行为一个正整数q代表查询的组数  第4行到第（3+q）行，每行包含3个整数l,r,k代表一组查询，即标号为l<=i<=r的用户中对这类文章喜好值为k的用户的个数。 数据范围n <= 300000,q<=300000 k是整型
 *
 * 输出描述:
 * 输出：一共q行，每行一个整数代表喜好值为k的用户的个数
 *
 * 输入例子1:
 * 5
 * 1 2 3 3 5
 * 3
 * 1 2 1
 * 2 4 5
 * 3 5 3
 * @date 2019/3/9
 **/
public class bytedance {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int t = in.nextInt();
            if (!map.containsKey(t))
                map.put(t, new ArrayList<Integer>());

            map.get(t).add(i + 1);
        }


        int t = in.nextInt();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int k = 0; k < t; k++) {
            int lo = in.nextInt();
            int hi = in.nextInt();
            int score = in.nextInt();
            int count = 0;
            List<Integer> list = map.get(score);
            if (list != null) {
                for (Integer integer : list) {
                    if (integer >= lo && integer <= hi) {
                        count++;
                    }
                }
            }
            queue.add(count);
        }
        for (Integer integer : queue) {
            System.out.println(integer);
        }

    }
}
