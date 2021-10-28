package algorithms.basicEntry.chap05;

import javax.xml.soap.Node;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class code05_BinaryTreeSerializable {
    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);
        head.left.right.right = new TreeNode(8);
        head.right.left.right = new TreeNode(9);
        head.right.right.right = new TreeNode(10);
        //System.out.println(serialByPre(head));
        //code02_PrintBinaryTree.printTree(head);
        String s1 = "phenobarbital";
        System.out.println(s1.substring(3,5));
    }

    /**
     * [题目] 实现两个函数，分别对一颗二叉树序列化和反序列化  --  这里使用先序的方式
     * [分析] 先将一颗二叉树序列化成一个字符串。其中节点与节点之间的分隔符使用 "_"
     *
     */
    public static String serialByPre(TreeNode root) {
        if (root == null) {
            return "#_";
        }
        String res = root.val + "_";
        res += serialByPre(root.left);
        res += serialByPre(root.right);
        return res;
    }
    public static TreeNode deSerialByPre(String data) {
        String[] values = data.split("_");
        Queue<String> queue = new LinkedList<>(Arrays.asList(values));
        return processReSerial(queue);
    }
    public static TreeNode processReSerial(Queue<String> queue) {
        String value = queue.poll();
        assert value != null;
        if (value.equals("#")) {
            //说明是空树
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(value));
        root.left = processReSerial(queue);
        root.right = processReSerial(queue);
        return root;
    }
}
