package test.datastructures;

import org.junit.Test;

import java.util.Stack;

public class dataTest {

    @Test
    public void queueTest() {
        MyQuque.push(1);
        MyQuque.push(2);
        MyQuque.push(3);
        System.out.println(MyQuque.pop());
        System.out.println(MyQuque.pop());
        System.out.println(MyQuque.pop());

    }

    //用两个栈实现队列
    //栈1用于存放入队列的数据
    //当出队列时候，先判断栈2是否为空
    //只有为空时才能把栈1的数据放入栈2中
    //最后弹出栈2中的数据
    static class MyQuque {
        private static Stack<Integer> stack1 = new Stack<Integer>();
        private static Stack<Integer> stack2 = new Stack<Integer>();

        public static void push(Integer i) {
            stack1.push(i);
        }

        public static Integer pop() {
            if (stack2.empty()) {
                while (!stack1.empty())
                    stack2.push(stack1.pop());
            }
            return stack2.pop();
        }

    }


    /**
     * @return void
     * @Description 二叉树层序遍历，用一个队列实现
     * 先把根节点入队
     * 然后遍历队列
     * 在遍历队列的每一个节点时，在将此节点的两个子节点入队
     * @Param [root]
     * @Line 59
     **/
    public void levelRead(treeNode<Integer> root) {
        Queue<treeNode> queue = new ArrayBlockingQueue<>(10);
        queue.add(root);
        while (queue.size() != 0) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                treeNode<Integer> temp = queue.poll();
                System.out.println(temp.val);
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }

            }
        }
    }

    @Test
    public void tests() {
        treeNode<Integer> roots = new treeNode<>(0);
        roots.val = 1;
        roots.left = new treeNode<>(1);
        roots.right = new treeNode<>(2);
        roots.left.val = 2;
        roots.left.left = new treeNode<>(1);
        roots.left.left.val = 3;
        roots.right.val = 4;
        roots.right.left = new treeNode<>(1);
        roots.right.left.val = 5;

        wideRead(roots);

    }

    /**
     * @return void
     * @Description 二叉树深度遍历
     *用栈实现，先把根节点入栈，然后循环（出栈一次，右节点先入栈，左边节点后入栈
     * 用栈实现
     * @Param [root]
     * @Line 100
     **/
    public void wideRead(treeNode<Integer> root) {
        Stack<treeNode<Integer>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            treeNode<Integer> temp = stack.pop();
            System.out.println(temp.val);
            if (temp.right != null)
                stack.push(temp.right);
            if (temp.left != null)
                stack.push(temp.left);
        }
    }

    class treeNode<T extends Number> {
        int type;

        public treeNode(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public treeNode getLeft() {
            return left;
        }

        public void setLeft(treeNode left) {
            this.left = left;
        }

        public treeNode getRight() {
            return right;
        }

        public void setRight(treeNode right) {
            this.right = right;
        }

        public T getVal() {
            return val;
        }

        public void setVal(T val) {
            this.val = val;
        }

        treeNode left;
        treeNode right;
        T val;
    }
}
