package algorithms.entry.chap04;

import java.util.HashMap;

public class Code05_SingleListAdvanced {
    public static void main(String[] args) {
        //测试copyRandomList()   --  leetcode  剑指 Offer 35. 复杂链表的复制 AC
        RandomNode head = buildRandomList();
//        RandomNode copyHead = copyRandomListV1(head);
//        RandomNode.print(copyHead);
        RandomNode.print(copyRandomListV2(head));
    }

    /**
     * [题目] 除了next指针外，还有一个额外的random指针，可以指向链表中的任意一个节点(包括null)，请复制该链表
     * 笔试：使用hashmap key存原节点，value存新节点，然后根据原节点的random和next指向，设置新节点
     * 面试：不开辟额外空间，重铸链表，每个链表的下一个节点是copy的该节点，再遍历时每两个一起遍历，
     * 后面的copyNode指向根据前面的Node的random指向设置，最后重置next指向，分离两个链表
     */
    public static RandomNode buildRandomList() {
        //使用hashmap key存原节点，value存新节点，然后根据原节点的random和next指向，设置新节点
        //head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
        RandomNode a = new RandomNode(7);
        RandomNode b = new RandomNode(13);
        RandomNode c = new RandomNode(11);
        RandomNode d = new RandomNode(10);
        RandomNode e = new RandomNode(1);
        a.next = b; a.random = null;
        b.next = c; b.random = a; //0
        c.next = d; c.random = e; //4
        d.next = e; d.random = c; //2
        e.next = null; e.random = a; //0
        return a;
    }
    public static RandomNode copyRandomListV1(RandomNode head) {
        HashMap<RandomNode, RandomNode> map = new HashMap<>();
        RandomNode cur = head;
        while (cur != null) {
            map.put(cur, new RandomNode(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
    public static RandomNode copyRandomListV2(RandomNode head) {
        //不开辟额外空间，重铸链表，每个链表的下一个节点是copy的该节点，再遍历时每两个一起遍历，
        //后面的copyNode指向根据前面的Node的random指向设置，最后重置next指向，分离两个链表
        if (head == null)
            return null;
        RandomNode cur = head;
        while (cur != null) {
            RandomNode temp = cur.next;
            cur.next = new RandomNode(cur.val);
            cur.next.next = temp;
            cur = temp;
        }
        cur = head;
        RandomNode Next = null;
        while (cur != null) {
            Next = cur.next.next;
            RandomNode copyNode = cur.next;
            copyNode.random = cur.random != null ? cur.random.next : null;
            cur = Next;
        }
        //split
        RandomNode res = head.next;
        cur = head;
        while (cur != null) {
            Next = cur.next.next;
            RandomNode copyNode = cur.next;
            cur.next = Next;
            copyNode.next = Next != null ? Next.next : null;
            cur = Next;
        }
        return res;
    }

    /**
     * [题目] 给定两个可能有环也可能无环的单链表，头节点head1和head2。
     * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点，如果不相交，返回null
     * (复杂单链表相交问题，综合性很强，单链表中最难的题目)
     * [要求] 如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)
     */
    public static Node complexSingleListIntersection(Node headA, Node headB) {
        Node loopA = isLoop(headA);
        Node loopB = isLoop(headB);

        if (loopA == null && loopB == null) {
            //两条无环单链表相交
            return acyclicListIntersect(headA, headB);
        }
        if (loopA != null && loopB != null) {
            //两条有环单链表相交
            return bothLoopIntersect(headA,loopA, headB, loopB);
        }
        return null;
    }
    public static Node isLoop(Node head) {
        //判断链表是不是环形链表，如果是，返回第一个入环节点，如果不是，返回null
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        //使用双指针法
        Node fast = head;
        Node slow = head;
        while (slow != fast) {
            if (fast == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        //此时slow == fast，重置fast到head，一次走一步，相遇即为第一个入环节点
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
    public static Node acyclicListIntersect(Node headA, Node headB) {
        //无环单链表相交
        int len = 0;
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
        if (curA != curB) {
            return null;
        }
        //让curA总是指向长链表
        curA = len < 0 ? headB : headA;
        curB = curA == headA ? headB : headA;
        len = Math.abs(len);
        while (len != 0) {
            curA = curA.next;
            len--;
        }
        while (curA != curB) {
            curA = curA.next;
            curB = curB.next;
        }
        return curA;
    }
    public static Node bothLoopIntersect(Node heaA, Node loopA, Node heaB, Node loopB) {
        //两条有环链表相交问题
        Node curA = null;
        Node curB = null;
        if (loopA == loopB) {
            //情况一: 两条有环链表的第一个入环节点是相同的
            //处理方法类似无环单链表相交问题, 区别是将loop节点替换acyclicListIntersect()函数中的null
            curA = heaA;
            curB = heaB;
            int len = 0;
            while (curA != loopA) {   //替换null
                len++;
                curA = curA.next;
            }
            while (curB != loopB) {   //替换null
                len++;
                curB = curB.next;
            }
            curA = len > 0 ? heaA : heaB;
            curB = curA == heaA ? heaB : heaA;
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
        } else {
            //情况二: 两条链表有不同的入环节点
            //把环走一圈停止，如果中间遇到另外一个链表的入环节点，返回哪一个都可以
            curA = loopA.next;
            while (curA != loopA) {
                if (curA == loopB) {
                    return loopA;
                }
                curA = curA.next;
            }
            //情况三: 上面两种情况走完没有return，说明不相交
            return null;
        }
    }
}


class RandomNode {
    int val;
    RandomNode next;
    RandomNode random;

    public RandomNode(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    public static void print(RandomNode head) {
        if (head == null) {
            return;
        }
        RandomNode cur = head;
        while (cur != null) {
            System.out.println(cur);
            cur = cur.next;
        }
    }

    @Override
    public String toString() {
        return "[" + this.val + "->" + this.random + "]";
    }
}