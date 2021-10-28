package algorithms.entry.chap02;

import java.util.Arrays;
import java.util.Stack;

/**
 * 使用非递归的方式进行归并排序
 */
public class Code04_MergeSortNoRecursion {
    public static void main(String[] args) {
        int[] arr = {9, 2, 5, 4, 10, 11, 7, 8};
        arr = mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] mergeSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return new int[] {};
        int p = 1;
        while (p < arr.length) {
            int i = 0;
            for (i = 0; i + 2 * p - 1 < arr.length; i = i + 2 * p) {
                int rightPoint = i + 2 * p - 1;
                int mid = i + p - 1;
                merge(arr, i, mid, rightPoint);
            }
            int ans = i + p - 1;
            //剩余无法构成完整的两组也要进行处理
            if (i + p - 1 < arr.length)
                merge(arr, i, i + p - 1, arr.length - 1);
            p *= 2;
        }
        return arr;
    }

    public static int merge(int[] arr, int L, int mid, int R) {
        int left = L;
        int right = mid + 1;
        int[] help = new int[R - L + 1];
        int i = 0;
        while (left <= mid && right <= R) {
            help[i++] = arr[left] > arr[right] ? arr[right++] : arr[left++];
        }
        while (left <= mid) {
            help[i++] = arr[left++];
        }
        while (right <= R) {
            help[i++] = arr[right++];
        }
        if (help.length >= 0)
            System.arraycopy(help, 0, arr, L + 0, help.length);
        return mid;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
