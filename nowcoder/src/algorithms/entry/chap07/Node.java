package algorithms.entry.chap07;

import java.util.ArrayList;

public class Node {
    public int value; //节点值
    public int in;   //有多少个节点指向该节点
    public int out;  //从该节点出发的节点有多少个
    public ArrayList<Node> nexts; //一个节点的相邻节点的集合，即所有从该节点出发指向的下一个节点
    public ArrayList<Edge> edges; //和该节点有关的所有边的集合，即从该节点出发的边和指向该节点的边的集合

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
