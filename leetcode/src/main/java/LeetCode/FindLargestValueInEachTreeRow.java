package LeetCode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * You need to find the largest value in each row of a binary tree.
 * <p>
 * Example:
 * Input:
 * <p>
 * 1
 * / \
 * 3   2
 * / \   \
 * 5   3   9
 * <p>
 * Output: [1, 3, 9]
 */

public class FindLargestValueInEachTreeRow {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> largestValues(TreeNode root) {
        if(root==null)
            return new LinkedList<>();
        List<Integer> r = new LinkedList<>();
        HashSet<TreeNode> layer = new HashSet<>();
        HashSet<TreeNode> tmp = new HashSet<>();
        layer.add(root);
        while (!layer.isEmpty()) {
            int min = Integer.MIN_VALUE;
            for (TreeNode node : layer) {
                if (node.val > min)
                    min = node.val;
                if (node.left != null)
                    tmp.add(node.left);
                if (node.right != null)
                    tmp.add(node.right);
            }
            layer.clear();
            layer.addAll(tmp);
            tmp.clear();
            r.add(min);
        }
        return r;
    }
}