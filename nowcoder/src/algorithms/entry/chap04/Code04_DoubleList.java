package algorithms.entry.chap04;

public class Code04_DoubleList {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 9, 10, 4, 8, 1, 5};
        DoubleLink dl = new DoubleLink();
        //测试add()
        for (int i = 0; i < arr.length; i++) {
            //if (arr[i] %2 == 0)
            dl.add(arr[i]);
        }
        dl.print();
        //测试delete()
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] <= 5)
//                dl.delete(arr[i]);
//        }
        //dl.print();
        //测试update()
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= 5) {
                System.out.println(arr[i]);
                dl.update(arr[i], arr[i] + 10);
            }
        }
        dl.print();
    }
}

class DoubleLink{
    static class Node {
        int val;
        Node next;
        Node pre;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val + " ";
        }
    }
    Node head = new Node(0);

    public void add(int val) {
        Node newNode = new Node(val);
        if (head.next == null) {
            head.next = newNode;
            newNode.pre = head;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.pre = temp;
    }

    public void delete(int val) {
        if (head.next == null) {
            System.out.println("啥都没有，删个鸡毛");
            return;
        }
        Node temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.val == val) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            if (temp.next.next == null) {
                //要删除的是最后一个
                Node t = temp.next;
                temp.next = null;
                t.pre = null;
            } else {
                //要删除的是中间的某一个temp.next
                Node tt = temp.next;
                temp.next = temp.next.next;
                tt.next.pre = temp;
                tt.next = null;
                tt.pre = null;
            }
        } else {
            System.out.println("找不捉你要删除的东西，自己找去！");
        }
    }

    public void update(int oldVal, int newVal) {
        if (head.next == null) {
            System.out.println("啥都没有，改个锤子");
            return;
        }
        Node temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.val == oldVal) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next.val = newVal;
        } else {
            System.out.println("找不着你要改的数，自己找去吧！");
        }
    }

    public void print() {
        if (head.next == null) {
            System.out.println("啥也没有，打印你妹啊");
            return;
        }
        Node tmp = head.next;
        while (tmp != null) {
            System.out.print(tmp);
            tmp = tmp.next;
        }
        System.out.println();
    }
}