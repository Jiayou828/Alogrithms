package algorithms.entry.chap02;


import java.util.Arrays;

/**
 * 给定一个数组arr和一个数num，要求把大于num的数放右边，小于等于num的数放左边。
 * 空间复杂度O(1)   时间复杂度O(n)
 *
 */
public class Code07_ArrayPartition {
    public static void main(String[] args) {
        int[] arr = {7, 9, 2, 7, 5, 0, 1, 4, 3, 11, 12, 8, 7};
        partition1(arr, 0, arr.length - 1, 7);
        //partition2(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    // 给定一个数组arr和一个数num，要求把大于num的数放右边，小于等于num的数放左边。
    public static void partition1(int[] arr, int L, int R, int num) {
        int leftPoint = L - 1;
        while (L <= R) {
            if (arr[L] <= num) {
                swap(arr, ++leftPoint, L++);
            } else {
                L++;
            }
        }
    }

    //给定一个数组arr，要求把奇数放在偶数的前面
    public static void partition2(int[] arr, int L, int R) {
        int oddZone = L - 1;
        while (L <= R) {
            if (arr[L] %2 == 1) {
                swap(arr, ++oddZone, L++);
            } else {
                L++;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
