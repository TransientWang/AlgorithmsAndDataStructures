package test.datastructures;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author wangfy
 * @Description TODO
 * @date 2019/3/27
 **/

public class DSTwo {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * @date 2019/3/27 8:16
     * @return test.datastructures.DSTwo.ListNode
     * @Description 23. 合并K个排序链表（review）
     * @Param [lists]
     **/
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val - o2.val > 0) {
                    return 1;
                } else if (o1.val - o2.val < 0) {
                    return -1;
                }
                return 0;
            }
        });
        ListNode head = new ListNode(-1), cur = head;
        for (int i = 0; i < lists.length; i++) {
            heap.offer(lists[i]);
        }
        while (!heap.isEmpty()) {
            ListNode minNode = heap.poll();
            cur.next = minNode;
            cur = cur.next;
            if (minNode.next != null) {
                heap.offer(minNode.next);
            }
        }
        return head.next;
    }
}
