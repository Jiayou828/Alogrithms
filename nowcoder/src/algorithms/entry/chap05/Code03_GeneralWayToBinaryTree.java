package algorithms.entry.chap05;

import java.util.LinkedList;
import java.util.Queue;

public class Code03_GeneralWayToBinaryTree {
    //二叉树的通用解法 即 树形dp -- 每一个节点返回一些需要的信息给下一个节点，依次递归
    public static void main(String[] args) {
        boolean treeIsBst = isBinarySearchTree(buildBinarySearchTree());
        System.out.println("判断一棵树是不是搜索二叉树：" + treeIsBst);
        boolean treeIsFull = isFullBinaryTree(buildFullBinaryTree());
        System.out.println("判断一棵树是不是满二叉树：" + treeIsFull);
        TreeNode[] tn = buildLcaTree();
        System.out.println("找公共祖先" + getLowestCommonAncestorV1(tn[0], tn[1], tn[2]));
        System.out.println("判断是不是完全二叉树：" + isCompleteBinaryTree(buildCompleteBinaryTree()));
    }

    /**
     * [题目] 判断一棵树是不是搜索二叉树 -- leetcode 98. 验证二叉搜索树 AC
     * [概念] 二叉搜索树的必要条件：
     *         一、左子树整体是二叉搜索树
     *         二、右子树整体是二叉搜索树
     *         三、左子树的最大值小于当前root节点的值小于右子树的值，即 left.max < x < right.min
     * [分析] 二叉搜索树：对于每一个节点，它的左孩子的数值比自己小，右孩子的数值比自己大
     *       设计Info时，需要的信息：1.以该节点为root的子树是不是搜索二叉树  2.以该节点为root的子树中的最小值和最大值
     */
    public static TreeNode buildBinarySearchTree () {
        TreeNode head = new TreeNode(5);
        head.left = new TreeNode(1);
        head.right = new TreeNode(4);
        head.right.left = new TreeNode(3);
        head.right.right = new TreeNode(6);
        //code02_PrintBinaryTree.printTree(head);
        return head;
    }
    public static boolean isBinarySearchTree (TreeNode root){
        return processBst(root).isBst;
    }
    public static InfoBst processBst(TreeNode x) {
        if (x == null) {
            return null;
        }
        InfoBst left = processBst(x.left);
        InfoBst right = processBst(x.right);
        int min = x.val;
        int max = x.val;
        if (left != null) {
            min = Math.min(min, left.min);
            max = Math.max(max, left.max);
        }
        if (right != null) {
            min = Math.min(min, right.min);
            max = Math.max(max, right.max);
        }
        boolean isBst = false;
        if ( (left == null ||  (left.isBst && left.max < x.val)) &&
             (right == null || (right.isBst && right.min > x.val))) {
            isBst = true;
        }
        return new InfoBst(isBst, min, max);
    }
    public static class InfoBst {
        boolean isBst;
        int min;
        int max;
        public InfoBst(boolean isBst, int min, int max) {
            this.isBst = isBst;
            this.min = min;
            this.max = max;
        }
    }

    /**
     * [题目] 判断一棵树是不是满二叉树 -- leetcode 未找到 手动验证
     * [概念] 满二叉树的必要条件：
     *       如果一颗二叉树的深度(高度)为h，节点个数为n，并且 n = 2 ^ h - 1, 则该树是二叉树
     * [分析] 可以使用宽度优先搜索求两个值，带入公式，这里使用套路解题
     *       信息体：以当前节点为root的子树的节点个数，以及树的高度
     *
     */
    public static TreeNode buildFullBinaryTree () {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);
        //code02_PrintBinaryTree.printTree(head);
        return head;
    }
    public static boolean isFullBinaryTree(TreeNode root) {
        InfoFull info = processFul(root);
        int nodes = info.nodes;
        int heights = info.heights;

        return (1 << heights) - 1 == nodes;
    }
    public static InfoFull processFul(TreeNode x) {
        if (x == null) {
            return new InfoFull(0, 0);
        }
        InfoFull left = processFul(x.left);
        InfoFull right = processFul(x.right);

        int nodes = left.nodes + right.nodes + 1;
        int height = Math.max(left.heights, right.heights) + 1;
        return new InfoFull(nodes, height);
    }
    public static class InfoFull {
        int nodes;
        int heights;

        public InfoFull(int nodes, int heights) {
            this.nodes = nodes;
            this.heights = heights;
        }
    }

    /**
     * [题目] 判断一棵树是不是平衡二叉树 -- leetcode 剑指 Offer 55 - II. 平衡二叉树 / 110. 平衡二叉树 AC
     * [概念] 平衡二叉树的必要条件：
     *         每一颗子树，左右高度差不超过1
     * [分析] 二叉搜索树：对于每一个节点，它的左孩子的数值比自己小，右孩子的数值比自己大
     *       设计Info时，需要的信息：1.以该节点为root的子树是不是搜索二叉树  2.以该节点为root的子树中的最小值和最大值
     */
    public static boolean isBalancedBinaryTree (TreeNode root){
        return processBbt(root).isBbt;
    }
    public static InfoBbt processBbt(TreeNode x) {
        if (x == null) {
            return new InfoBbt(true, 0);
        }
        InfoBbt left = processBbt(x.left);
        InfoBbt right = processBbt(x.right);
        int height = Math.max(left.heights, right.heights) + 1;
        boolean isBalanced = left.isBbt && right.isBbt && Math.abs(left.heights - right.heights) < 2 ;
        return new InfoBbt(isBalanced, height);
    }
    public static class InfoBbt {
        boolean isBbt;
        int heights;

        public InfoBbt(boolean isBbt, int heights) {
            this.isBbt = isBbt;
            this.heights = heights;
        }
    }

    /**
     * [题目] 返回两个棵树(p,q)的最近公共祖先(lowest common ancestor)，没有返回null
     * [分析] 情况一：p = null, 02 = null, 没有lca,返回null
     *       情况二：p和q只存在一个，不存在lca
     *       情况三：p和q都存在在root树上，那么lca必存在，可能在左树也可能在右树，否则就是root
     *      Info设计：1.是否发现了p  2.是否发现了q  3.是否已经找到公共祖先
     * [说明] v1版本使用套路 leetcode 剑指 Offer 68 - II. 二叉树的最近公共祖先 AC
     *       v2版本没有使用套路 leetcode 236. 二叉树的最近公共祖先 AC
     */
    public static TreeNode[] buildLcaTree() {
        TreeNode head = new TreeNode(3);
        head.left = new TreeNode(5);
        head.right = new TreeNode(1);
        head.left.left = new TreeNode(6);
        head.left.right = new TreeNode(2);
        head.right.left = new TreeNode(0);
        head.right.right = new TreeNode(8);
        head.left.right.left = new TreeNode(7);
        head.left.right.right = new TreeNode(4);
        Code02_PrintBinaryTree.printTree(head);
        return new TreeNode[] {head, head.left, head.left.right.right};
    }
    public static TreeNode getLowestCommonAncestorV1(TreeNode root, TreeNode p, TreeNode q){
        return processLca(root, p, q).findLca;
    }
    public static InfoLca processLca(TreeNode x, TreeNode p, TreeNode q) {
        if (x == null) {
            return new InfoLca(false, false, null);
        }
        InfoLca left = processLca(x.left, p, q);
        InfoLca right = processLca(x.right, p, q);
        if (left.findLca != null) {
            return new InfoLca(true, true, left.findLca);
        }
        if (right.findLca != null) {
            return new InfoLca(true,true, right.findLca);
        }
        //此时说明：左右两颗子树都没有发现lca
        if (left.isFindO1 && right.isFindO2) {
            //如果p在左树，q在右树，那么x就是lca
            return new InfoLca(true, true, x);
        }
        if (left.isFindO2 && right.isFindO1) {
            //如果p在右树，q在左树，那么x就是lca
            return new InfoLca(true, true, x);
        }
        //左右两颗树都没有发现lca,而且两棵树不交汇，即lca不在x处交汇
        //左右两树只包含p或者只包含q,或者什么都没发现
        boolean findO1 = x == p;
        boolean findO2 = x == q;
        if (left.isFindO1 || right.isFindO1) {
            //p在左子树或右子树上，q就是根节点
            if (findO2) {
                return new InfoLca(true, true, x);
            } else {
                return new InfoLca(true, false, null);
            }
        }
        if (left.isFindO2 || right.isFindO2) {
            //q在左子树或右子树，p就是根节点
            if (findO1) {
                return new InfoLca(true, true, x);
            } else { //此时说明：还没有发现o1(p),所以要返回false
                return new InfoLca(false, true, null);
            }
        }
        //左右两颗树都没有发现p和q
        return new InfoLca(findO1, findO2, null);
    }
    public static class InfoLca {
        boolean isFindO1;
        boolean isFindO2;
        TreeNode findLca;

        public InfoLca(boolean isFindO1, boolean isFindO2, TreeNode findLca) {
            this.isFindO1 = isFindO1;
            this.isFindO2 = isFindO2;
            this.findLca = findLca;
        }
    }

    public static TreeNode getLowestCommonAncestorV2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        TreeNode left = getLowestCommonAncestorV2(root.left, p, q);
        TreeNode right = getLowestCommonAncestorV2(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    /**
     * [题目] 判断一棵树是不是完全二叉树 -- 手动验证 过
     * [分析] 一棵树如果是满二叉树，或者正在走向满二叉树的路上，就是完全二叉树
     *       所以可以改写宽度优先遍历的代码，即在遍历过程中满足条件
     *       1) 遍历的任何节点不能有右无左
     *       2) 一旦遇到孩子不双全的节点，后续遇到的所有节点，都必须是叶子节点
     */
    public static TreeNode buildCompleteBinaryTree() {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);
//        head.left.right.right = new TreeNode(8);
//        head.right.left.right = new TreeNode(9);
//        head.right.right.right = new TreeNode(10);
        Code02_PrintBinaryTree.printTree(head);
        return head;
    }
    public static boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        boolean isMeet = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode left = null;
        TreeNode right = null;
        while (!queue.isEmpty()) {
            root = queue.poll();
            left = root.left;
            right = root.right;
            if (isMeet && !(left == null && right == null) || (left == null && right != null)) {
                //如果遇到了不双全的节点之后，又发现当前节点不是叶子节点，或者它是有右无左
                //程序结束，返回不是完全二叉树的结论
                return false;
            }
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
            if (left == null || right == null) {
                //表示当前节点不双全的节点
                isMeet = true;
            }
        }
        //循环结束，没有任何节点违反判断条件，说明是完全二叉树
        return true;
    }
}



















