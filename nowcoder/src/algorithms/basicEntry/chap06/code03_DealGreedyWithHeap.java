package algorithms.basicEntry.chap06;

import algorithms.basicEntry.chap05.TreeNode;

import java.util.*;

import static algorithms.basicEntry.chap05.code02_PrintBinaryTree.printTree;

public class code03_DealGreedyWithHeap {

    public static void main(String[] args) {
        System.out.println(splitGold(new int[]{10, 20, 30}));
        System.out.println("***************************************");
        huffmanTree();
        System.out.println("***************************************");
        System.out.println(maxProfits(new int[]{3, 7, 1, 6, 100}, new int[]{5, 1, 7, 9, 400}, 3, 2));
    }

    /**
     * [题目]切金条
     *  一块金条切成两半，是需要花费和长度数值一样的铜板的。
     *  比如长度为20的金 条，不管切成长度多大的两半，都要花费20个铜板。
     *
     * 一群人想整分整块金条，怎么分最省铜板? 例如,给定数组{10,20,30}，代表一共三个人，
     * 整块金条长度为10+20+30=60。 金条要分成10,20,30三个部分。
     * 如果先把长度60的金条分成10和50，花费60; 再把长度50的金条分成20和30，花费50;
     * 一共花费110铜板。但是如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20，花费30;
     * 一共花费90铜板。输入一个数组，返回分割的最小代价.
     * [分析] 对数组排序，然后把最小的两个想加的值放回小根堆，反复重复，得到数组就是一颗哈夫曼树的结构
     *       它的每层节点值，就是每次切后分割的金条值
     */
    public static int splitGold(int[] arr) {
        //小根堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        System.out.println(queue);
        int cur = 0;
        int ans = 0;
        while (queue.size() > 1) {
            cur = queue.poll() + queue.poll();
            //沿途所有合出来的节点就是代价
            ans += cur;
            queue.add(cur);
        }
        return ans;
    }

    /**
     * [哈夫曼树构建]
     * 1.从小到大进行排序，将每一个数据，每个数据都是一个节点，每个节点可以看成是一颗最简单的二叉树
     * 2.取出根节点权值最小的两颗二叉树
     * 3.组成一颗新的二叉树，该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
     * 4.再将这颗新的二叉树，以根节点的权值大小再次排序，不断重复1-2-3-4的步骤，直到数列中，所有的数据都被处理，就得到一颗哈夫曼树
     */
    public static void huffmanTree() {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        //1.遍历arr数组
        //2.将arr的每个元素构成TreeNode
        //3.将TreeNode放入ArrayList中
        List<TreeNode> nodes = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            nodes.add(new TreeNode(arr[i]));
        }
        TreeNode root = null;
        while (nodes.size() > 1) {
            //排序 从小达大
            Collections.sort(nodes);
            //取出根节点权值最小的两颗二叉树
            TreeNode left = nodes.get(0);
            TreeNode right = nodes.get(1);
            //构建一颗新的二叉树
            root = new TreeNode(left.val + right.val);
            root.left = left;
            root.right = right;
            //删除用过的节点
            nodes.remove(left);
            nodes.remove(right);
            //将新的二叉树的root加入到nodes中
            nodes.add(root);
        }
        printTree(root);
        System.out.println(nodes);
    }

    /**
     * [最大利润]
     * 输入:
     * 正数数组costs
     * 正数数组profits
     * 正数count
     * 正数fund
     * 含义:
     * costs[i]表示i号项目的花费 profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润) k表示你只能串行的最多做k个项目
     * m表示你初始的资金
     * 说明: 你每做完一个项目，马上获得的收益，可以支持你去做下一个项目。 输出:
     * 你最后获得的最大钱数。
     * [分析] RPG游戏解锁模式，先将所有的(花费，利润)对象按照花费排序，扔进小根堆，然后从小根堆顶开始挑选对象，放入大根堆，
     * 挑选条件是花费要小于目前所拥有的资金，初始资金是m。在k次循环下，返回最大钱数
     */
     public static int maxProfits(int[] costs, int[] profits, int count, int fund) {
         //按照cost组成的小根堆
         PriorityQueue<Node> minHeap = new PriorityQueue<>(((o1, o2) -> o1.cost - o2.cost));
         //按照profit组成的大根堆
         PriorityQueue<Node> maxHeap = new PriorityQueue<>(((o1, o2) -> o2.profit - o1.profit));
         for (int i = 0; i < costs.length; i++) {
             minHeap.add(new Node(profits[i], costs[i]));
         }
         for (int i = 0; i < count; i++) {
             while (!minHeap.isEmpty() && minHeap.peek().cost <= fund) {
                 maxHeap.add(minHeap.poll());
             }
             if (maxHeap.isEmpty()) {
                 return fund;
             }
             fund += maxHeap.poll().profit;
         }
         return fund;
     }
     public static class Node {
         public int profit;
         public int cost;

         public Node(int p, int c) {
             this.profit = p;
             this.cost = c;
         }
     }

}
