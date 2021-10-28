package algorithms.basicEntry.chap03;


public class hw_InsertionSortList {
    final static int MAX = (int) Math.pow(2, 32) - 1;
    final static int MIN = MAX * (-1);

    public static void main(String[] args) {
        int[] arr = new int[]{20, 10, 0, 70, 50, 90, 30};
        NodeList nl = new NodeList();
        for (int i = 0; i < arr.length; i++) {
            nl.add(arr[i]);
        }
        NodeList.print(nl.head);
        System.out.println();
        ListNode tnode = insertSort(nl.head);
        NodeList.print(tnode);
    }

    public static ListNode insertSort(ListNode head) {
        ListNode res = new ListNode(MIN);
        ListNode pre = res;
        ListNode cur = head;
        while (cur != null) {
            pre = res;
            while (pre.next != null && cur.val >= pre.next.val) {
                pre = pre.next;
            }
            ListNode temp = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = temp;
        }
        return res.next;
    }
}

class NodeList {
    ListNode head = null;

    void add(int val) {
        ListNode newNode = new ListNode(val);
        if (head == null) {
            head = newNode;
            return;
        }
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public static void print(ListNode head) {
        if (head == null) {
            System.out.println("None List");
            return;
        }
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp);
            temp = temp.next;
        }
    }
}
class ListNode{
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString(){
        return val + " ";
    }
}