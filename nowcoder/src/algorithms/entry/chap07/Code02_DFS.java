package algorithms.entry.chap07;

import java.util.HashSet;
import java.util.Stack;

public class Code02_DFS {
    public static void main(String[] args) {
        Node root = buildGraph();
        dfs(root);
    }
    public static Node buildGraph() {
        Integer[][] matrix = new Integer[][] {
                {0, 97, 98},
                {1, 97, 99},
                {2, 98, 102},
                {3, 99, 101},
                {4, 98, 99},
                {5, 99, 100},
        };
        Graph graph = GraphGenerator.createGraph(matrix);
        return graph.nodes.get(97);
    }
    public static void dfs(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(root);
        set.add(root);
        System.out.print((char)root.value + " ");
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.print((char) next.value + " ");
                    break;
                }
            }
        }
    }
}
