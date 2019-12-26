package leetcode;

/**
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * <p>
 * 你需要将他们合并为一个新的二叉树。
 * 合并的规则是如果两个节点重叠，
 * 那么将他们的值相加作为节点合并后的新值，
 * 否则不为 NULL 的节点将直接作为新二叉树的节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * Tree 1                     Tree 2
 * 1                         2
 * / \                       / \
 * 3   2                     1   3
 * /                           \   \
 * 5                             4   7
 * 输出:
 * 合并后的树:
 * 3
 * / \
 * 4   5
 * / \   \
 * 5   4   7
 */
public class OverlapTree {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return null;

        return mergeNode(t1, t2);
    }

    private TreeNode mergeNode(TreeNode srcL, TreeNode srcR) {
        if (srcL == null && srcR == null)
            return null;
        if (srcL == null) {
            return srcR;
        } else if (srcR == null) {
            return srcL;
        } else {
            TreeNode thisNode = new TreeNode(srcL.val + srcR.val);
            thisNode.left = mergeNode(srcL.left, srcR.left);
            thisNode.right = mergeNode(srcL.right, srcR.right);
            return thisNode;
        }
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
