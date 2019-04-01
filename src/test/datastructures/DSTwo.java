package test.datastructures;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

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
     * @return test.datastructures.DSTwo.ListNode
     * @date 2019/3/27 8:16
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

    /**
     * @return int
     * @date 2019/3/29 9:39
     * @Description 213. 打家劫舍 II(reveiw)
     * 由于House [1]和House [n]相邻，它们不能被抢劫在一起。 因此，问题变成抢劫House [1] -House [n-1]或House [2] -House [n]，取决于哪个选择提供更多钱。 现在问题已经退化
     * @Param [nums]
     **/
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
//        if (nums.length == 2) return Math.max(nums[0], nums[1]);
//        if (nums.length == 3) return Math.max(Math.max(nums[0], nums[1]), nums[2]);
//        int[] dp1 = new int[nums.length];
//        int[] dp2 = new int[nums.length];
//        dp1[0] = nums[0];
//        dp1[1] = Math.max(nums[0], nums[1]);
//        dp2[1] = nums[1];
//        dp2[2] = Math.max(nums[1], nums[2]);
//        for (int i = 2; i < dp1.length - 1; i++) {
//            dp1[i] = Math.max(dp1[i - 2] + nums[i], dp1[i - 1]);
//        }
//        for (int i = 3; i < dp2.length; i++) {
//            dp2[i] = Math.max(dp2[i - 2] + nums[i], dp2[i - 1]);
//        }
//        return Math.max(dp1[nums.length - 2], dp2[nums.length - 1]);
        return Math.max(robHelp(nums, 1, nums.length), robHelp(nums, 0, nums.length - 1));

    }

    /**
     * @date 2019/4/1 10:53
     * @return int
     * @Description 863. 二叉树中所有距离为 K 的结点
     * @Param [nums, left, right] 
     **/
    public int robHelp(int[] nums, int left, int right) {
        if (right - left == 1) return nums[left];
        int[] dp = new int[nums.length];
        dp[left] = nums[left];
        dp[left + 1] = Math.max(nums[left + 1], nums[left]);
        for (int i = left + 2; i < right; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[right - 1];
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        HashMap<TreeNode, TreeNode> parent = new HashMap<>();
        dfs(parent, root, null);
        Deque<TreeNode> deque = new LinkedList<>();
        Set<TreeNode> seen = new TreeSet<>((a, b) -> a.val - b.val);
        int dist = 0;
        deque.addFirst(target);
        seen.add(target);

        while (!deque.isEmpty()) {
            if (dist == K) {
                return deque.stream().map(node -> node.val).collect(Collectors.toList());
            }

            int len = deque.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = deque.pollLast();
                if (node.left != null && !seen.contains(node.left)) {
                    seen.add(node.left);
                    deque.addFirst(node.left);
                }
                if (node.right != null && !seen.contains(node.right)) {
                    seen.add(node.right);
                    deque.addFirst(node.right);
                }
                TreeNode pre = parent.get(node);
                if (pre != null && !seen.contains(pre)) {
                    seen.add(pre);
                    deque.addFirst(pre);
                }
            }
            dist++;
            
        }
        
        return new LinkedList<Integer>();
    }

    public void dfs(HashMap<TreeNode, TreeNode> parent, TreeNode node, TreeNode pre) {
        Optional.ofNullable(node).ifPresent(n -> {
            parent.put(node, pre);
            dfs(parent, node.left, node);
            dfs(parent, node.right, node);
        });


    }

    @Test
    public void test() {
        System.out.println(rob(new int[]{2, 3, 2}));

    }
}
