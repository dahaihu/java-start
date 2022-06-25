package leetcode.right_side_view;


import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class RightSideView {
    public List<Integer> solve(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        List<Integer> result = new ArrayList<>();
        while (queue.size() != 0) {
            result.add(queue.get(queue.size() - 1).val);
            List<TreeNode> nextQueue = new ArrayList<>();
            for (TreeNode node : queue) {
                if (node.left != null) {
                    nextQueue.add(node.left);
                }
                if (node.right != null) {
                    nextQueue.add(node.right);
                }
            }
            queue = nextQueue;
        }
        return result;
    }
}
