package test.datastructures;

import org.junit.Test;

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
     * @return test.datastructures.Main.ListNode
     * @Description 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，
     * 它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     * @Param [l1, l2]
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

            if (l1 != null || l2 != null) {
                node.next = new ListNode(0);
                node = node.next;
            } else if (c == 1) {
                node.next = new ListNode(1);
                node = node.next;
            }


        }

        return head;
    }

    /**
     * @date 2019/3/12 16:33
     * @return java.lang.String
     * @Description 299. 猜数字游戏（review）
     * @Param [secret, guess] 
     **/
    public String getHint(String secret, String guess) {
        int[] sFreq = new int[10];
        int[] gFreq = new int[10];
        int bulls = 0, cows = 0;
        for (int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);
            if (s == g) {
                bulls++;
            } else {
                sFreq[s - '0']++;
                gFreq[g - '0']++;
            }
        }
        for (int i = 0; i < 10; i++) {
            cows += Math.min(sFreq[i], gFreq[i]);
        }
        return bulls + "A" + cows + "B";
    }


    @Test
    public void tests() {
        String secret = "1123";
        String guess = "0111";
        System.out.println(getHint(secret, guess));
    }
}
