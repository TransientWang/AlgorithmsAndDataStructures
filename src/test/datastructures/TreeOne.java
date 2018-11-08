package test.datastructures;

import org.junit.Test;

import java.util.*;


/**
 * @author wangfy
 * @Description 二叉树相关
 * @date 2018/11/8
 */

public class TreeOne {
    @Test
    public void testOne() {

    }

    /**
     * @return int
     * @Description 求二叉树深度
     * @Param [root]
     * @Line 25
     **/
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * @return int
     * @Description 二叉树深度优先遍历
     * @Param [root]
     * @Line 29
     **/
    public int DFS(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        int deepth = 1;

        if (root == null) return 0;

        queue.offer(root);
        while (!queue.isEmpty()) {
            deepth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmpNode = queue.poll();
                if (tmpNode.left != null)
                    queue.offer(tmpNode.left);
                if (tmpNode.right != null)
                    queue.offer(tmpNode.right);
            }
        }
        return deepth;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> lists = new ArrayList<>();

        if (root == null) return lists;

        queue.offer(root);
        List<Integer> tmp = new ArrayList<>(1);
        tmp.add(root.val);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> t = new ArrayList<>();
            for (int i = 0; i < size; i++) {

                TreeNode tmpNode = queue.poll();
                t.add(tmpNode.val);
                if (tmpNode.left != null)
                    queue.offer(tmpNode.left);
                if (tmpNode.right != null)
                    queue.offer(tmpNode.right);
            }
            lists.add(t);
        }
        return lists;
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Queue<Integer> queue = new ArrayDeque<>();
        find(root, queue);
        int tmp = queue.poll();
        while (!queue.isEmpty()) {
            int t = queue.poll();
            if (t <= tmp) return false;
            tmp = t;
        }
        return true;
    }

    public void find(TreeNode root, Queue<Integer> queue) {
        if (root == null) return;
        if (root.left != null) find(root.left, queue);
        queue.offer(root.val);
        if (root.right != null) find(root.right, queue);
    }

    public boolean isSymmetric(TreeNode root) {

        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val) && isMirror(t1.right, t2.left) && isMirror(t1.left, t2.right);

    }

    public boolean isSymmetricOne(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if (t1 == null && t2 == null) return true;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);
        }
        return true;
    }

    @Test
    public void testTwo() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(10);
//        root.right = new TreeNode(15);
//        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(20);
        System.out.println(isValidBST(root));
    }
}
