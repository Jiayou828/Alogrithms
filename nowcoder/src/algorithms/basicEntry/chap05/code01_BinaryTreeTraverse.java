package algorithms.basicEntry.chap05;

import java.util.*;

public class code01_BinaryTreeTraverse {
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
        code02_PrintBinaryTree.printTree(head);

/*        System.out.println("pre-order:");
        preOrderRecur(head);
        System.out.println();
        preOrderUnRecur(head);*/

/*        System.out.println("infix-order:");
        infixOrderRecur(head);
        System.out.println();
        infixOrderUnRecur(head);*/

/*        System.out.println("post-order:");
        postOrderRecur(head);
        System.out.println();
        postOrderUnRecur(head);*/

/*        System.out.println("Breadth-First-Traverse:");
        breadthFirstTraverse(head);*/

        System.out.println("get the max width of a binary tree");
        System.out.println(getMaxWidthV1(head));
        System.out.println();
        System.out.println(getMaxWidthV2(head));
    }

    public static void generalTraverse(TreeNode head) {
        //上面二叉树的递归序列：1,2,4,4,4,2,5,5,5,2,1,3,6,6,6,3,7,7,7,3,1
        //递归时，每一个数字都会看3次，输出第一次出现的数，就是先序遍历，第二次就是中序，第三次就是后序
        if (head == null) {
            return;
        }
        //1.先(前)序遍历
        //System.out.print(head);
        generalTraverse(head.left);
        //2.中序遍历
        //System.out.print(head);
        generalTraverse(head.right);
        //3.后序遍历
        System.out.print(head);
    }

    public static void preOrderRecur(TreeNode head) {
        //System.out.println("pre-order:");
        if (head == null) {
            return;
        }
        System.out.print(head);
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }
    public static void preOrderUnRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        //System.out.println("pre-order:");
        //使用辅助栈，实现非递归先序遍历
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            System.out.print(temp);
            //注意这里，虽然先序是：中左右  但是因为我们使用了栈，所有要倒着来，先push右
            //如果先push左，输出就是：中右左(反过来就是后序遍历)
            if (temp.right != null) {
                stack.push(temp.right);
            }
            if (temp.left != null) {
                stack.push(temp.left);
            }
        }
    }

    public static void infixOrderRecur(TreeNode head) {
        //System.out.println("in-order:");
        if (head == null) {
            return;
        }
        infixOrderRecur(head.left);
        System.out.print(head);
        infixOrderRecur(head.right);
    }
    public static void infixOrderUnRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        //阶段一：cur从head开始，将左子节点全部入栈，如果没有左子节点，进入阶段二
        //阶段二：弹出cur，并打印，如果cur有右子节点，将右子节点入栈视为head，重复阶段一
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = head;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.print(cur);
                cur = cur.right;
            }
        }

    }

    public static void postOrderRecur(TreeNode head) {
        //System.out.println("post-order:");
        if (head == null) {
            return;
        }
        postOrderRecur(head.left);
        postOrderRecur(head.right);
        System.out.print(head);
    }
    public static void postOrderUnRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        //后序遍历：左右中  把 中右左 的顺序存入另一个栈然后依次弹出打印即可
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(head);
        while (!s1.isEmpty()) {
            TreeNode temp = s1.pop();
            s2.push(temp);
            if (temp.left != null) {
                s1.push(temp.left);
            }
            if (temp.right != null) {
                s1.push(temp.right);
            }
        }
        while (!s2.isEmpty()) {
            System.out.print(s2.pop());
        }
    }

    public static void breadthFirstTraverse(TreeNode head) {
        if (head == null) {
            return;
        }
        //广度(宽度)优先遍历 -- 使用队列即可
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            System.out.print(cur);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    public static int getMaxWidthV1(TreeNode head) {
        //更改宽度优先遍历代码求一棵二叉树的最大宽度
        //v1: 不计算空节点，只计算有效节点
        if (head == null) {
            return 0;
        }
        //广度(宽度)优先遍历 -- 使用队列即可
        Map<TreeNode, Integer> map = new HashMap<>();
        map.put(head, 1);
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int level = map.get(cur);

            if (level == curLevel) {
                curLevelNodes++;
            } else {
                System.out.println("the nodes number of level-" + curLevel + " is " + curLevelNodes);
                if (curLevelNodes > max) {
                    max = curLevelNodes;
                }
                curLevel++;
                curLevelNodes = 1;
            }

            System.out.println("Node:" + cur + " " + "level:" + level);
            //map.put(cur, map.get(cur) + 1);
            if (cur.left != null) {
                queue.add(cur.left);
                map.put(cur.left, map.get(cur) + 1);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                map.put(cur.right, map.get(cur) + 1);
            }
        }
        System.out.println("the nodes number of level-" + curLevel + " is " + curLevelNodes);
        return Math.max(max, curLevelNodes);
    }
    public static int getMaxWidthV2(TreeNode root) {
        //更改宽度优先遍历代码求一棵二叉树的最大宽度
        //v2: 计算空节点  -- leetcode 662. 二叉树最大宽度
         class Info{
             TreeNode node;
             int level;
             int pos;

             public Info(TreeNode node, int level, int pos) {
                 this.node = node;
                 this.level = level;
                 this.pos = pos;
             }
         }
         Queue<Info> queue = new LinkedList<>();
         queue.add(new Info(root, 0, 0));
         int curLevel = 0;
         int maxAns = 0;
         int tempPos = 0;
         while (!queue.isEmpty()) {
             Info cur = queue.poll();
             if (cur.node != null) {
                 queue.add(new Info(cur.node.left, cur.level + 1, cur.pos * 2));
                 queue.add(new Info(cur.node.right, cur.level + 1, cur.pos * 2 + 1));
                 if (curLevel != cur.level) {
                     curLevel = cur.level;
                     tempPos = cur.pos;
                 }
                 maxAns = Math.max(maxAns, cur.pos - tempPos + 1);
             }
         }
         return maxAns;
    }
}
