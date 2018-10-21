package test.greedychoice;

import org.junit.Test;

import java.util.*;

/**
 * @author wangfy
 * @Description 贪心选择
 * @date 2018/10/15
 */

public class ActivitySelection {

    @Test
    public void test() {
        int[] s = new int[]{1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
        int[] f = new int[]{4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16};
//        recursiceSelector(s, f, 0, 11);
        GreedyActivitySelector(s, f);
    }


    /**
     * @return void
     * @Description 活动选择问题
     * 迭代
     * @Param [s, f, k, n]
     * @Line 23
     **/
    public void recursiceSelector(int[] s, int[] f, int k, int n) {
        System.out.println(k + 1);
        int m = k + 1;
        while (m < n && s[m] <= f[k]) {
            m += 1;
        }
        if (m < n) {
            recursiceSelector(s, f, m, n);
        }

    }

    /**
     * @return void
     * @Description 递归方法
     * @Param [s, f]
     * @Line 45
     **/
    public void GreedyActivitySelector(int[] s, int[] f) {
        int n = s.length;
        int k = 0;
        for (int i = 1; i < n; i++) {
            if (s[i] >= f[k]) {
                System.out.println(k + 1);
                k = i;
            }
        }
        System.out.println(k + 1);
    }


    @Test
    public void Tests() {
        int[][] map = {{1000, 2, 5, 1000, 1000},
                {1000, 1000, 2, 6, 1000},
                {1000, 1000, 1000, 7, 1},
                {1000, 1000, 2, 1000, 4},
                {1000, 1000, 1000, 1000, 1000}};
        dijkstraSolution(map);

    }

    /**
     * @return void
     * @Description 单元最短路径，算法时间复杂度为O（n²）,空间复杂度为常数阶O（n）
     * map代表有向图 从i到j的权重
     * @Param [map]
     * @Line 64
     **/
    public void dijkstraSolution(int[][] map) {
        //原点到各个点的距离
        int[] dist = new int[map[0].length];
        //各个点的前驱节点
        int[] p = new int[dist.length];
        //为true是代表该节点已经走过
        boolean[] flag = new boolean[p.length];

        //初始化
        for (int i = 0; i < p.length; i++) {
            dist[i] = map[0][i];
            flag[i] = false;
            if (dist[i] == 1000) {
                p[i] = -1;
            } else {
                p[i] = 0;
            }
        }
        flag[0] = true;
        p[0] = 0;

        //做出总结点-1次选择（原点不算）
        for (int i = 1; i < p.length; i++) {
            //筛选出最短路径的辅助变量
            int temp = 1000;
            //最短路径所在的节点
            int t = 0;
            //从未走过路径中选择dist[]最小的的顶点t
            for (int j = 1; j < p.length; j++) {
                //如果此节点还未走过而且到此节点的最短路径小于temp
                if (!flag[j] && dist[j] < temp) {
                    temp = dist[j];
                    t = j;
                }
            }
            //更新已走节点
            flag[t] = true;
            for (int j = 1; j < p.length; j++) {
                //如果此节点没走过而且从t到j可以达到
                if (!flag[j] && map[t][j] < 1000) {
                    //如果当前到j节点的最短路径比从t开始到j的最短路径大
                    if (dist[j] > (dist[t] + map[t][j])) {
                        //将j节点的最短距离更新
                        dist[j] = dist[t] + map[t][j];
                        //将达到j节点的前驱节点设为t
                        p[j] = t;
                    }
                }
            }

        }
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println();
        //根据p找出应走路径
        int index = 4;
        while (index > 0) {
            stringBuilder.append(String.valueOf(index + 1));
            stringBuilder.append("-");
            index = p[index];
        }
        stringBuilder.append(String.valueOf(index + 1));
        System.out.println(stringBuilder);

    }




    //哈弗曼树测试
    @Test
    public void hhtest() {
        Integer[] integers = new Integer[]{5, 32, 18, 7, 25, 13};
        List<Integer> list = Arrays.asList(integers);
        haHaffCodeSolution(list);
    }

    /**
     * @return void
     * @Description 哈夫曼编码
     * @Param [list]
     * @Line 168
     **/
    public void haHaffCodeSolution(List<Integer> list) {

        List<Tree> treeList = new ArrayList<>();

        Tree parentTree = null;
        for (int i = 0; i < list.size(); i++) {
            treeList.add(new Tree(list.get(i)));
        }
        for (int i = 0; i < list.size() - 1; i++) {
            Tree leftTree = treeList.stream().min((var1, var2) -> var1.getValue() - var2.getValue()).get();
            treeList.remove(leftTree);
            Tree rightTree = treeList.stream().min((var1, var2) -> var1.getValue() - var2.getValue()).get();
            treeList.remove(rightTree);
            parentTree = new Tree(leftTree, rightTree, leftTree.getValue() + rightTree.getValue());
            treeList.add(parentTree);
        }
        findChild(parentTree, true);
    }

    /**
     * @Description 展示哈夫曼树
     * @return void
     * @Param [tree, flag]
     * @Line 194
     **/
    public void findChild(Tree tree, boolean flag) {
        if (flag == true) {
            System.out.println("  " + tree.getValue());
            System.out.println("/     \\");

        }
        if (tree.getLeftCgild() != null) {
            System.out.print(tree.getLeftCgild().getValue() + "     ");
            if (tree.getRightCgild() != null) {
                System.out.println(tree.getRightCgild().getValue());
            }
            System.out.print(" /       \\\r\n");

        }
        System.out.println();
        if (tree.getLeftCgild() != null) {
            findChild(tree.getLeftCgild(), false);
        }
        if (tree.getRightCgild() != null) {
            findChild(tree.getRightCgild(), false);
        }


    }

    class Tree {
        private Tree parent;
        private Tree leftCgild;
        private Tree rightCgild;
        private Integer value;

        public Tree() {

        }


        public Tree(Tree leftCgild, Tree rightCgild, Integer value) {
            this(value);
            this.leftCgild = leftCgild;
            this.rightCgild = rightCgild;

        }

        public Tree(Integer value) {
            this.value = value;
        }

        public Tree getParent() {
            return parent;
        }

        public void setParent(Tree parent) {
            this.parent = parent;
        }

        public Tree getLeftCgild() {
            return leftCgild;
        }

        public void setLeftCgild(Tree leftCgild) {
            this.leftCgild = leftCgild;
        }

        public Tree getRightCgild() {
            return rightCgild;
        }

        public void setRightCgild(Tree rightCgild) {
            this.rightCgild = rightCgild;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }
}
