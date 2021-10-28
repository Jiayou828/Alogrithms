package algorithms.entry.chap05;

import java.util.Scanner;

public class Hw_BinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right; //默认null
        public TreeNode(int val) {
            this.val = val;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            TreeNode p = new TreeNode(m);
            TreeNode q = new TreeNode(n);
        }
    }
}
