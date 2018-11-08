package test.datastructures;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;


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

}
