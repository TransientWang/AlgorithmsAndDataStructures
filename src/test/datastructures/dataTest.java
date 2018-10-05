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
}
