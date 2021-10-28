package algorithms.entry.chap04;
//链表，树等题目：
//笔试做法(为了时间复杂度牺牲空间): 使用栈
//面试做法(考虑时间的同时还是要节约点空间)：快慢指针+翻转链表

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

//单链表的基本实现-带头节点
public class Code03_SingleList {
    public static void main(String[] args) {
        SingleList sList = new SingleList();
        int[] arrList = new int[]{2, 9, 8, 3, 87, 5, 0, 34, 99, 100, 8, 8};
        //验证add()
        for (int i = 0; i < arrList.length; i++) {
            sList.add(arrList[i]);
        }

        //SingleList.printListWithHead(sList.head);
/*        //验证remove()  删除even
        for (int i = 0; i < arrList.length; i++) {
            if (arrList[i] % 2 == 0)
                sList.remove(arrList[i]);
        }*/

/*        //验证select() 查找小于10的数并输出
        for (int i = 0; i < arrList.length; i++) {
            if (arrList[i] < 10)
                System.out.println(sList.select(arrList[i]));
        }*/

        //验证update() 把小于10的数改成它的平方
/*        for (int i = 0; i < arrList.length; i++) {
            if (arrList[i] < 10)
                sList.update(arrList[i], arrList[i] * arrList[i]);
        }*/

        //验证detectCycle()
        //验证detectCycleV1()  leetcode  142. 环形链表 II   AC
        //验证detectCycleV2()  leetcode  面试题 02.08. 环路检测  AC


/*        //验证reverseSingleList() -- leetcode 剑指 Offer 24 反转链表 AC
        sList.print();
        Node revere = reverseSingleList(sList.head.next);
        sList.printList(revere);*/

/*        //验证 getFirstIntersectionNodeV1() -- leetcode 剑指 Offer 52. 两个链表的第一个公共节点 AC
        //验证 getFirstIntersectionNodeV2() -- leetcode 面试题 02.07. 链表相交 AC
        Node head[] = buildIntersectionList();
        System.out.println(getFirstIntersectionNode(head[0], head[1]));*/

/*        //验证getPublicList()
        Node head[] = buildIntersectionList();
        getPublicList(head[0], head[1]);*/

/*        //验证palindromeList()
        //验证palindromeListV1() -- leetcode 234. 回文链表 AC
        Node head = BuildPalindromeList();
        //System.out.println(palindromeListV1(head));
        //验证palindromeListV2()  -- leetcode 面试题 02.06 回文链表 AC
        System.out.println(palindromeListV2(head));*/

/*        //验证listPartitionV1()
        SingleList.printListWithHead(listPartitionV1(sList.head, 8));*/
        //验证listPartitionV2()
        //SingleList.printListWithHead(listPartitionV2(sList.head, 8));
    }

    /**
     * [题目]判断一个单链表是否有环，返回入环的第一个节点，没有就返回null
     * [要求]时间复杂度要求为O(N)，额外空间复杂度要求O(1)
     * [笔试]使用hashset，每遍历一个节点，先判断在不在hashset里面，如果在就直接return，不在就存进去
     * [面试]不开辟额外空间，使用双指针，slow一次一步，fast一次两步，有一个指向null，则无环，相遇则有环；
     * 然后，slow原地不动，fast指向head，同时一次走一步，再次相遇的节点，就是入环的第一个节点
     */
    public static Node detectCycleV1(Node head) {
        //使用hashset，每遍历一个节点，先判断在不在hashset里面，如果在就直接return，不在就存进去
        HashSet<Node> set = new HashSet<>();
        Node cur = head;
        while (cur != null) {
            if (!set.contains(cur)) {
                set.add(cur);
            } else {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }
    public static Node detectCycleV2(Node head) {
        if (head == null || head.next == null || head.next.next == null) { //至少三个节点才能形成环
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (fast == null || fast.next == null)
                return null;
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    /**
     * [题目]反转单链表
     * [要求]时间复杂度要求为O(N)，额外空间复杂度要求O(1)
     */
    public static Node reverseSingleList(Node head) {
        Node pre = null;
        Node cur = head;
        while (cur != null) {
            Node nextTemp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextTemp;
        }
        return pre;
    }

    /**
     * [题目]输入两个链表，找出他们的第一个公共节点，没有返回null（链表相交）
     * [要求]如果两个链表的长度之和为N，时间复杂度要求为O(N)，额外空间复杂度要求O(1)
     */
    public static Node[] buildIntersectionList() {
        // 1 -> 3
        //        \
        //         5 -> 6 -> 7 -> null;
        //        /
        // 2 -> 4
        Node A = new Node(1);
        Node B = new Node(3);
        Node a = new Node(2);
        Node b = new Node(4);
        Node pc = new Node(5);
        Node pd = new Node(6);
        Node pe = new Node(7);
        A.next = B; B.next = pc; pc.next = pd; pd.next = pe;
        a.next = b; b.next = pc; pe.next = null;
        return new Node[] {A, a};
    }
    public static Node getFirstIntersectionNodeV1(Node headA, Node headB) {
        //双指针法，浪漫相遇
        Node nodeA = headA;
        Node nodeB = headB;

        while (nodeA != nodeB) {
            nodeA = nodeA != null? nodeA.next : headB;
            nodeB = nodeB != null? nodeB.next : headA;
        }
        return nodeA;
    }
    public static Node getFirstIntersectionNodeV2(Node headA, Node headB) {
        //遍历两个链表，同时记录长度，之后，指针移到两个链表最后，如果是同一个节点则相交
        //得到两个链表的长度差n，让长链表重新从头走n个节点后，两个链表同时走，相遇，即为第一个公共节点
        int len = 0; //使用一个变量即可得到长度差
        Node curA = headA;
        Node curB = headB;
        while (curA != null) {
            len++;
            curA = curA.next;
        }
        while (curB != null) {
            len--;
            curB = curB.next;
        }
        if (curA != curB) //不相交
            return null;
        //len>0 说明A长
        curA = len > 0 ? headA : headB; //curA现在只指向长链表的头
        curB = curA == headA ? headB : headA; //curB现在只指向短链表的头
        len = Math.abs(len);
        while (len != 0) {
            len--;
            curA = curA.next;
        }
        while (curA != curB) {
            curA = curA.next;
            curB = curB.next;
        }
        return curA;
    }

    /**
     * [题目]给定两个有序链表的头指针head1和head2,打印两个链表的公共部分
     * [要求]如果两个链表的长度之和为N，时间复杂度要求为O(N)，额外空间复杂度要求O(1)
     */
    public static void getPublicList(Node headA, Node headB) {
        Node nodeA = headA;
        Node nodeB = headB;

        while (nodeA != nodeB) {
            nodeA = nodeA != null? nodeA.next : headB;
            nodeB = nodeB != null? nodeB.next : headB;
        }

        while (nodeA == nodeB && nodeA != null && nodeB != null) {
            System.out.println(nodeA);
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }
    }

    /**
     * [题目]判断一个链表是不是回文结构
     * 如：1—>2—>3—>3—>2—>1
     *     1—>23—>7—>23—>1  区别字符串回文"1237231"
     * 提示：笔试使用栈，开辟额外空间   面试：尽可能省空间  用快慢指针 + 反转链表法
     */
    public static Node BuildPalindromeList() {
        Node A = new Node(1);
        Node B = new Node(23);
        Node C = new Node(7);
        Node D = new Node(7);
        Node E = new Node(23);
        Node F = new Node(1);
        A.next = B; B.next = C; C.next = D; D.next = E;
        //E.next = null;
        E.next = F; F.next = null;
        return A;
    }

    public static boolean palindromeListV1(Node head) {
        //使用栈，开辟额外空间
        Stack<Node> stack = new Stack<>();
        //1.遍历链表，存入stack
        Node temp = head;
        while (true) {
            if (temp != null) {
                stack.add(temp);
            } else {
                break;
            }
            temp = temp.next;
        }
        temp = head;
        while (true) {
            if (temp != null) {
                Node stackTemp = stack.pop();
                if (temp.data != stackTemp.data) {
                    return false;
                }
                temp = temp.next;
            } else {
                break;
            }
        }
        return true;
    }
    public static boolean palindromeListV2(Node head) {
        SingleList.printListNoHead(head);
        //不开辟额外空间，使用快慢指针法
        //快指针走两步，慢指针走一步，快指针空，慢指针来到中点位置
        Node fast = head;
        Node slow = head;
        //coding技巧：快慢指针兼容链表个数(奇偶)
        while (fast != null) {
            //奇数节点： 1 -> 2 -> 3 -> 4 -> 5 -> null
            //                   ^         ^
            if (fast.next == null) {
                break;
            }
            //奇数节点： 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
            //                   ^              ^
            if (fast.next.next == null) {
                fast = fast.next;
                //slow = slow.next;
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = null;
        Node tempHead = slow.next;
        slow.next = null;
        //反转后半部分链表
        Node cur = tempHead;
        Node pre = slow;
        while (cur != null) {
            Node tempNext = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tempNext;
        }
        //头指针和尾指针往前走，依次对比数值，判断是否相等
        Node left = head;
        Node right = pre;
        Boolean flag = true;
        while (left != null) {
            if (left.data != right.data) {
                flag = false;
            }
            left = left.next;
            right = right.next;
        }
        //将链表复原
        //返回
        return flag;
    }

    /**
     * [题目] 给出一个链表list和一个数num，把链表中大于num的数放在右边，小于num的数放在左边，等于num的数放在中间
     * 笔试：把链表的数放进数组，partition之后，穿回链表
     * 面试：六个变量做划分：小于num的首和尾   等于num的首和尾   大于num的首和尾  找到之后串起来
     */
    public static Node listPartitionV1(Node head, int num) {
        //版本一：把链表的数放进数组，partition之后，穿回链表
        ArrayList<Integer> al = new ArrayList<>();
        Node temp = head;
        int n = 0;
        while (temp != null) {
            al.add(temp.data);
            temp = temp.next;
            n++;
        }
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = al.get(i);
        }
        partition(arr, 0, arr.length - 1, num);
        SingleList newSl = new SingleList();
        for (int i = 0; i < arr.length; i++) {
            newSl.add(arr[i]);
        }
        return newSl.head.next;
    }
    public static int[] partition(int[] arr, int left, int right, int num) {
        int leftIndex = left - 1;
        int rightIndex = right + 1;
        while (left < rightIndex) {
            if (arr[left] < num) {
//                left++;
//                leftIndex++;
                swap(arr, ++leftIndex, left++);
            } else if (arr[left] > num) {
                swap(arr, left, rightIndex - 1);
                rightIndex--;
            } else {
                left++;
            }
        }
        return new int[] {leftIndex + 1, rightIndex - 1};
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static Node listPartitionV2(Node head, int num) {
        //版本二：使用六个变量做划分，即小于num的首和尾   等于num的首和尾   大于num的首和尾  找到之后串起来
        Node smallLeft = null;
        Node smallRight = null;
        Node equalLeft = null;
        Node equalRight = null;
        Node bigLeft = null;
        Node bigRight = null;

        Node curNext = null;
        while (head != null) {
            curNext = head.next;
            head.next = null;
            if (head.data < num) {
                if (smallLeft == null) {
                    smallLeft = head;
                    smallRight = head;
                } else {
                    smallRight.next = head;
                    smallRight = head;
                }
            } else if (head.data == num) {
                if (equalLeft == null) {
                    equalLeft = head;
                    equalRight = head;
                } else {
                    equalRight.next = head;
                    equalRight = head;
                }
            } else {
                if (bigLeft == null) {
                    bigLeft = head;
                    bigRight = head;
                } else {
                    bigRight.next = head;
                    bigRight = head;
                }
            }
            head = curNext;
        }
        //上面得到三条链表：
        // 1. 小于num : smallLeft(head) smallRight(tail)
        // 2. 等于num : equalLeft(head) equalRight(tail)
        // 3. 大于num : bigLeft(head) bigRight(tail)
        if (smallRight != null) { // 如果有第一条
            // 连接第二条
            smallRight.next = equalLeft;
            equalRight = equalRight == null ? smallRight : equalRight;
        }
        //上面的代码无论跑了没有，都不能影响后面的
        //全连接
        if (equalRight != null) { //如果小于区域和等于区域，不是都没有
            equalRight.next = bigLeft;
        }
        return smallLeft != null ? smallLeft : (equalLeft != null ? equalLeft : bigLeft);
    }
}

class SingleList{
    //带头节点的单向链表，有效数据为head.next
    Node head = new Node(0);

    //增
    void add(int data) {
        Node newNode = new Node(data);
        if (head.next == null) {
            head.next = newNode;
            return;
        }
        Node tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = newNode;
    }

    //删
    void remove(int data) {
        if (head.next == null) {
            return;
        }
        Node tmp = head;
        while (tmp.next != null) {
            if (tmp.next.data == data) {
                tmp.next = tmp.next.next;
                return;
            }
            tmp = tmp.next;
        }
    }

    //改
    void update(int original, int newData) {
        if (head.next == null) {
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.data == original) {
                temp.next.data = newData;
                return;
            }
            temp = temp.next;
        }
    }

    //查
    int select(int data) {
        if (head.next == null) {
            return -1;
        }
        Node tmp = head;
        while (tmp.next != null) {
            if (tmp.next.data == data) {
                return tmp.next.data;
            }
            tmp = tmp.next;
        }
        return -1;
    }

    //输出链表
    static void printListWithHead(Node head) {
        if (head.next == null) {
            System.out.println("None List");
            return;
        }
        Node tmp = head;
        while (tmp.next != null) {
            System.out.print(tmp.next);
            tmp = tmp.next;
        }
        System.out.println();
    }

    //输出返回的链表 -- 不带头
    static void printListNoHead(Node head) {
        if (head == null) {
            System.out.println("None List");
            return;
        }
        Node tmp = head;
        while (tmp != null) {
            System.out.print(tmp);
            tmp = tmp.next;
        }
        System.out.println();
    }
}

class Node{
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data + " ";
    }
}

