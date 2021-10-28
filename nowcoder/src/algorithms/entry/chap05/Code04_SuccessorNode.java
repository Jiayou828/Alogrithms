package algorithms.entry.chap05;

public class Code04_SuccessorNode {
    public static void main(String[] args) {

    }
    public static TreeNode getSuccessorNode(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return getLeftMOst(node.left);
        } else { //无右子树
            TreeNode parent = node.parent;
            while (parent != null && parent.left != node) { //当前节点是其父亲节点的右孩子
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }
    public static TreeNode getLeftMOst(TreeNode node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    //特殊的树节点，多了一个指向父节点的指针
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        public TreeNode(int data) {
            this.data = data;
        }
    }
}
