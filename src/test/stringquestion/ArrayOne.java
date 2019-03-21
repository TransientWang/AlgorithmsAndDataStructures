package test.stringquestion;


import org.junit.Test;

import java.util.*;

/**
 * @author wangfy
 * @Description TODO
 * @date 2018/12/8
 **/
public class ArrayOne {
    /**
     * @return java.util.List<int ]>
     * @date 2018/12/8 11:01
     * @Description 将每条建筑的横线段分解成左上右上两个顶点，将所有这些点按横坐标大小升序排列
     * 从左至右遍历这些点，每遍历到一个左顶点，将此点代表的建筑高度放入大顶堆height中
     * 每次到一个左顶点，先比较此顶点高度与当前基准高度，如果高于基准高度，那么就是一个轮廓点。这是最关键的地方，结合图形理解，如果当前建筑的左顶点要作比较，肯定是与它前面有重叠的建筑比较，而前面重叠的建筑高度，要取之前最高的、横线还在延续的建筑比较，因此需要用到一个大顶堆维护当前高度
     * 还有两个细节：
     * 1. 将右顶点的高度设为负值，在遍历点时用以区分左右顶点
     * 2. 碰到左顶点将高度加入大顶堆，碰到右顶点时，说明此建筑横向的延绵结束了，那么要从大顶堆中删掉此高度
     * ---------------------
     * 作者：liujunzxcv
     * 来源：CSDN
     * 原文：https://blog.csdn.net/liujunzxcv/article/details/79895270
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     * @Param [buildings]
     **/
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        Set<int[]> set = new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {//横坐标相同时，纵坐标要降序排列
                    return o2[1] - o1[1];
                }
                return o1[0] - o2[0];

            }
        });
        var heap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < buildings.length; i++) {
            int[] A = {buildings[i][0], buildings[i][2]};
            set.add(A);
            int[] B = {buildings[i][1], -buildings[i][2]};
            set.add(B);
        }
        set.forEach(o -> System.out.println(o[0] + " " + o[1]));
        System.out.println();
        heap.offer(0);
        int preHeight = 0;

        for (int[] ints : set) {
            if (ints[1] > 0) {
                if (ints[1] > preHeight)
                    res.add(ints);
                heap.offer(ints[1]);
                preHeight = heap.peek();
            } else {
                heap.remove(-ints[1]);
                preHeight = heap.peek();
                if (-ints[1] > preHeight) {
                    res.add(new int[]{ints[0], preHeight});
                }
            }

        }
        return res;
    }

    /**
     * @return int
     * @date 2019/3/13 15:53
     * @Description 287. 寻找重复数(review)
     * @Param [nums]
     **/
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        int t = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }
        }
        while (true) {
            t = nums[t];
            slow = nums[slow];
            if (slow == t) {
                break;
            }
        }
        return slow;
    }

    /**
     * @date 2019/3/21 16:57
     * @return int
     * @Description 227. 基本计算器 II(reveiw)
     * @Param [s] 
     **/
    public int calculate(String s) {
        char[] chars = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        int tmp = 0;
        char op = '+';
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                tmp = tmp * 10 + (chars[i] - '0');
            }
            if (chars[i] != ' ' && (chars[i] < '0' || chars[i] > '9') || i == chars.length - 1) {
                switch (op) {
                    case '+':
                        stack.addFirst(tmp);
                        break;
                    case '-':
                        stack.addFirst(-tmp);
                        break;
                    default:
                        int t = stack.pollFirst();
                        tmp = op == '*' ? t * tmp : t / tmp;
                        stack.addFirst(tmp);
                }
                tmp = 0;
                op = chars[i];
            }
        }

        return stack.size() ==1? stack.pollFirst() : stack.stream().reduce((a, b) -> a + b).get();
    }

    @Test
    public void test() {
        System.out.println(calculate("100000000/1/2/3/4/5/6/7/8/9/10"));
        int[][] tmp = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
//        int[][] tmp = {{0, 2, 3}, {2, 5, 3}};
//        var res = getSkyline(tmp);
//        res.forEach(o -> System.out.println(o[0] + " " + o[1]));


    }
}
