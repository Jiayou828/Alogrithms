package algorithms.basicEntry.chap02;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * 归并排序的扩展 -- 逆序对问题
 * 在一个数组中，左边的数如果比右边的数大，则这两个数构成一个逆序对，请打印所以的逆序对。
 * 例子：[1,3,4,2,5]  1右边比1小的数：没有
 *                   3右边比3小的数： 2     -->    (3,2)
 *                   4右边比4小的数： 2     -->    (4,2)
 *                   2右边比2小的数：没有
 *                   5右边比5小的数：没有
 * 问题转换：对于每一个数找出它右边有几个比它本身小的数，累加即可
 *
 *
 */
public class code03_ReversePair {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        int ans = getNumReversePair(arr);
        System.out.println(ans);
    }

    public static int getNumReversePair(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R)
            return 0;
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid) + process(arr, mid+1, R)+ merge(arr, L, mid, R);
    }

    public static int merge(int[] arr, int L, int mid, int R) {
        int p1 = L;
        int p2 = mid + 1;
        int i = 0;
        int ans = 0;
        int[] help = new int[R - L + 1];
        while (p1 <= mid && p2 <= R) {
            ans += arr[p1] > arr[p2] ? (R - p2 + 1) : 0;
//            print the two numbers
//            if (arr[p1] > arr[p2]) {
//                if (R - p2 == 0)
//                    System.out.println(arr[p1] + " " + arr[p2]);
//                else {
//                    for (int m = 0; m < R - p2 + 1; m++) {
//                        System.out.println(arr[p1] + " " + arr[p2 + m]);
//                    }
//                }
//            }
            help[i++] = arr[p1] > arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L+j] = help[j];
        }
        return ans;
    }
}
