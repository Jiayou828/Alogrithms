package algorithms.basicEntry.chap04;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class hw_DiscardOdd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        while (sc.hasNextInt()) {
            n = sc.nextInt();
            System.out.println(discardOddV1(n));
            System.out.println(discardOddV2(n));
        }

    }

    public static int discardOddV1(int n) {  // 使用数组
        int[] arr = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            arr[i] = i;
        }
        int len = n + 1; //数组的真实长度
        int j = 0; //代表去奇数位后的数组有效值
        while (len != 1) {  //最后只剩下一个
            for (int i = 1; i <= len; i++) {
                if (i % 2 == 0) {
                    arr[j++] = arr[i - 1];
                }
            };
            len = j;
            j = 0;
        }
        return arr[0];
    }

    public static int discardOddV2(int n) { //使用链表
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            list.add(i);
        }
        while (list.size() != 1) {
            //应该从后向前删除。因为如果从前开始删除，数据的index就会发生改变，从而删错。
            //0   1   2   3   4   5   6
            //1   2   3   4   5   6   7
            //定位，找到最后一个要删除的节点
            int end = list.size() % 2 == 0 ? list.size() - 2 : list.size() - 1;
            //从后往前删除
            for (int i = end; i >= 0; i -= 2) {
                list.remove(i);
            }
        }
        return list.get(0);
    }
}



