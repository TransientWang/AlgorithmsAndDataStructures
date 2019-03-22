package test.datastructures;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author wangfy
 * @Description 225. 用队列实现栈(review)
 * @date 2019/3/22
 **/
public class MyStack {

    private Deque<Integer> lq;
    private Deque<Integer> rq;


    /** Initialize your data structure here. */
    public MyStack() {
        this.lq = new ArrayDeque<>();
        this.rq = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        if(lq.size() == 0 ){
            rq.addFirst(x);
        }else{
            lq.addFirst(x);
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(lq.size() == 0){
            int rqSize = rq.size() - 1 ;
            for(int i = 0; i < rqSize; i++){
                lq.addFirst(rq.pollLast());
            }
            return rq.pollLast();
        }else{
            int lqSize = lq.size() - 1 ;
            for(int i = 0; i < lqSize; i++){
                rq.addFirst(lq.pollLast());
            }
            return lq.pollLast();
        }

    }

    /** Get the top element. */
    public int top() {
        if(lq.size() == 0){
            return rq.peekFirst();
        }
        return lq.peekFirst();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return lq.size() + rq.size() == 0;
    }

    @Test
    public void test() {
        MyStack myStack = new MyStack();
        myStack.push(1);
        System.out.println(myStack.pop());

    }
}
