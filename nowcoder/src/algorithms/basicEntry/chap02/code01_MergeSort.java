//本章的三个排序都是  时间复杂度O(n * logn)   最难的三个排序算法  很重要
package algorithms.basicEntry.chap02;

import java.util.Arrays;

//归并排序 稳定
public class code01_MergeSort {
    public static void main(String[] args) {
        int[] arr = {9, 2, 5, 0, 1, 4, 3};
        arr = mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] mergeSort(int[] arr) {
        if (arr == null || arr.length == 0)
            return new int[] {};
        process(arr,0,arr.length-1);
        return arr;
    }

    private static void process(int[] arr, int L, int R) {
        if (L == R)
            return ;
        int mid = L + ((R - L) >> 1);
        process(arr,0,mid);
        process(arr,mid+1,R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int p1 = l;
        int p2= mid + 1;
        int[] help = new int[r - l + 1];   
        int i = 0;
        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (int j = 0;j < help.length;j++) {
            arr[l+j] = help[j];
        }
    }
}
