package leetcode.codec;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import sun.reflect.generics.tree.Tree;

import java.util.regex.*;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public String string() {
        String left = this.left != null ? this.left.string() : "";
        String right = this.right != null ? this.right.string() : "";
        return String.format("(%d)%d(%d)", left.length(), this.val, right.length()) + "|" + left + "|" + right;
    }

    public static TreeNode reverse(String s) throws Exception {
        if (s.length() == 0) {
            return null;
        }
        String[] elements = s.split("\\|", 2);
        Pattern p = Pattern.compile("\\((\\d+)\\)(\\d+)\\((\\d+)\\)");
        Matcher matcher = p.matcher(elements[0]);
        boolean matched = matcher.matches();
        if (!matched) {
            throw new Exception("");
        }
        int leftLength = Integer.parseInt(matcher.group(1));
        int val = Integer.parseInt(matcher.group(2));
        TreeNode node = new TreeNode(val);
        TreeNode left = TreeNode.reverse(elements[1].substring(0, leftLength));
        node.setLeft(left);
        TreeNode right = TreeNode.reverse(elements[1].substring(leftLength + 1));
        node.setRight(right);
        return node;
    }
}