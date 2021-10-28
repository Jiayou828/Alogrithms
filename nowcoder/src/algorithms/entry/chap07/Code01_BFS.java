package algorithms.entry.chap07;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Code01_BFS {
    public static void main(String[] args) {
        Node root = buildGraph();
        bfs(root);
    }

    public static Node buildGraph() {
        Integer[][] matrix = new Integer[][] {
                {0, 97, 98},
                {1, 97, 99},
                {2, 97, 100},
                {3, 98, 99},
                {4, 99, 100},
                {5, 98, 102},
                {6, 99, 101}
        };
        Graph graph = GraphGenerator.createGraph(matrix);
        return graph.nodes.get(97);
    }
    public static void bfs(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue= new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(root);
        set.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.print((char) cur.value + " ");
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
