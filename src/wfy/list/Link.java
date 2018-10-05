package haha.link;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author 王扶摇
 * @Title: Link
 * @ProjectName test
 * @date 2018/9/5 16:35
 */

public class Link<E extends Number> {

    Node headNode = new Node<>(100);

    private int length;

    private Node<E> getHeadNode() {
        return headNode;
    }


    protected void insertHeasd(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = getHeadNode().next;
        getHeadNode().next = newNode;
        length++;
    }

    protected void insertTail(E e) {
        Node<E> newNode = new Node<>(e);
        Node<E> cursor = getHeadNode();
        while (cursor.next != null) {
            cursor = cursor.next;
        }
        cursor.next = newNode;
        length++;
    }

    protected void forEach(Consumer<E> consumer) {
        Node<E> cursor = getHeadNode();

        while (cursor.next != null) {
            cursor = cursor.next;
            consumer.accept(cursor.e);
        }
    }


    protected int getLength() {
        return length;
    }

    protected void delTail() {
        delTail(getLength());

    }

    protected void delTail(int index) {
        int count = 0;
        Node<E> cursor = getHeadNode();
        while (cursor.next != null && count != index) {
            cursor = cursor.next;
            count++;
        }
        Node<E> delNode = cursor.next;
        cursor.next = delNode.next;

    }

    protected void reverse() {
        Node pre = new Node(11);   //先前节点
        Node cursor = new Node(12); //当前翻转节点
        Node after = new Node(13);  //下一节点
        pre = getHeadNode().next; //头结点排除,获取到第一个节点作为先前节点
        cursor = pre.next;         //获取翻转节点
        pre.next = null;  //将第一个先前节点的下一节点指向空防止遍历是形成环
        while (cursor != null) {
            after = cursor.next; //获取下一节点在
            cursor.next = pre;  //将翻转节点的下一节点指向先前节点，翻转完成所有节点向后移动
            pre = cursor;  //先前节点后移一位
            cursor = after; //翻转节点后移一位
        }
        headNode.next = pre; //将头结点安装在翻转后的第一个节点前面
    }

    public static void main(String[] args) {
        Link<Integer> link = new Link();
        link.insertTail(1);
        link.insertTail(2);
        link.insertTail(3);
        link.insertTail(4);

        link.insertHeasd(0);
//        link.delTail(2);
        link.reverse();
        link.forEach(o -> System.out.println("输出" + o));

    }

    private class Node<E> {
        private E e;
        Node next;

        public E getE() {
            return e;
        }

        public void setE(E e) {
            this.e = e;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node(E e) {
            this.e = e;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    ", next=" + next +
                    '}';
        }
    }

}
