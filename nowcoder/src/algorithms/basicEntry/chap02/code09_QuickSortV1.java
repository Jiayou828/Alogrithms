package algorithms.basicEntry.chap02;

import java.util.Arrays;

/**
 * 快速排序1.0：选最后一个数
 * 选择数组中最后一个数p做划分值，调整数组：小于等于p的全部在左侧，大于p的在右侧
 * 然后把p和大于p部分的第一个数做交换
 *
 * ------憨批方法，趁早淘汰！！！
 */
public class code09_QuickSortV1 {
    public static void main(String[] args) {
        int[] arr = {3, 6, 4, 7, 2, 9, 0, 1, 4, 3, 5, 3};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        process(arr, 0, arr.length - 1);
    }

    public static void process (int[] arr, int L, int R) {
        if (L < R) {
            int index = partition(arr, L, R);
            process(arr, L, index - 1);
            process(arr, index + 1, R);
        }
    }

    public static int partition(int[] arr, int L, int R) {
        int leftPoint = L - 1;
        while (L < R) {
            if (arr[L] <= arr[R]) {
                swap(arr, ++leftPoint, L++);
            } else {
                L++;
            }
        }
        swap(arr, leftPoint + 1, R);
        return leftPoint + 1;
    }

    private static void swap(int[] arr, int i, int l) {
        int temp = arr[i];
        arr[i] = arr[l];
        arr[l] = temp;
    }


}
