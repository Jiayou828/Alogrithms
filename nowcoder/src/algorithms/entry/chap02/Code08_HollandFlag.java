package algorithms.entry.chap02;

import java.util.Arrays;

import static algorithms.entry.chap02.Code07_ArrayPartition.swap;

/**
 * 给定一个数组和一个数，要求调整为：大于该数的在左边，等于该数的在中间，大于该数的在右边
 * 时间复杂度O(n)  空间O(1)
 *
 *
 */
public class Code08_HollandFlag {
    public static void main(String[] args) {
        int[] arr = {9, 2, 5, 0, 1, 4, 3, 11, 12, 8, 7, 6, 6, 6};
        partition(arr,0, arr.length - 1, 6);
        System.out.println(Arrays.toString(arr));
    }

    public static void partition(int[] arr, int L, int R, int num) {
        int leftPoint = L - 1;
        int rightPoint = R + 1;
        while (L < rightPoint) {
            if (arr[L] < num) {
                swap(arr, ++leftPoint, L++);
                //L++; buxing
            } else if (arr[L] > num) {
                swap(arr, --rightPoint, L);
            } else {
                L++;
            }
        }
    }
}
