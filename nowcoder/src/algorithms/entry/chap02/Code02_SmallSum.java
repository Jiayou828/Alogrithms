package algorithms.entry.chap02;

import java.util.Arrays;

/**
 * 归并排序的扩展 -- 小和问题
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和。
 * 例子：[1,3,4,2,5]  1左边比1小的数：没有
 *                   3左边比3小的数： 1
 *                   4左边比4小的数： 1，3
 *                   2左边比2小的数： 1
 *                   5左边比5小的数： 1，3，4，2
 *                   所以小和为：1+1+3+1+1+3+4+2=16
 * 将该问题转换为：对于每一个数来说，它的右边有n个数比它大，那么 sum = n 个该数相加
 */


public class Code02_SmallSum {
    public static void main(String[] args) {
        int[] arr = {9, 2, 5, 0, 1, 4, 3};
        int res = getSmallSum(arr);
        System.out.println(res);
        System.out.println(Arrays.toString(arr));
    }

    public static int getSmallSum(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R)
            return 0;
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid) + process(arr, mid+1, R) + merge(arr, L, mid, R);
    }

    private static int merge(int[] arr, int L, int mid, int R) {
        int p1 = L;
        int p2 = mid + 1;
        int i = 0;
        int[] help = new int[R - L + 1];
        int ans = 0;
        while (p1 <= mid && p2 <= R) {
            ans += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2]? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
        return ans;
    }
}

