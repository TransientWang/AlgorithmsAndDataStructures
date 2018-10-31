package test.datastructures;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(9);
//        l1.next = new ListNode(8);
//        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(9);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);
        var head = addTwoNumbers(l1, l2);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }


    }
    /**
     * @Description 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，
     * 它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     * @Param [l1, l2]
     * @return test.datastructures.Main.ListNode
     * @Line 40
     **/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int c = 0;
        ListNode head = new ListNode(0);
        ListNode node = head;
        while (l1 != null || l2 != null) {
            int t1 = 0;
            int t2 = 0;
            int sum = 0;
            if (l1 != null) {
                t1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                t2 = l2.val;
                l2 = l2.next;
            }
            sum = t1 + t2 + c;

            if (sum < 10) {
                node.val = sum;
                c = 0;
            } else {
                c = 1;
                node.val = Math.abs(10 - sum);
            }

            if (l1!=null || l2 !=null) {
                node.next = new ListNode(0);
                node = node.next;
            }else if (c ==1){
                node.next = new ListNode(1);
                node = node.next;
            }


        }

        return head;
    }


}
