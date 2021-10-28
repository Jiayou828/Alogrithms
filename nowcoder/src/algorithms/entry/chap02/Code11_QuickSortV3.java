package algorithms.entry.chap02;

import java.lang.Math;
import java.util.Arrays;

/**
 * 快速排序3.0：选一个随机数，把它和最后一个数交换
 * 选择数组中最后一个数p做划分值，调整数组：小于p的全部在左侧，大于p的在右侧，等于p的在中间
 * 然后把p和大于p部分的第一个数做交换，得到划分值的索引并返回。 如：[... 3 4 0 5 5 5 7 9 6...]  返回[i,j]
 *                                                                      i   j
 */
public class Code11_QuickSortV3 {
    public static void main(String[] args) {
        //int[] arr = {3, 6, 4, 7, 2, 9, 0, 1, 4, 3, 5, 3};
        int[] arr = {3, 89, 9, 2, 0, 34, 21, 109, -34, 54, 123, -3};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L < R) {
            swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
            int[] index = partition(arr, L, R);
            process(arr, L, index[0]);
            process(arr, index[1], R);
        }
    }

    private static int[] partition(int[] arr, int L, int R) {
        int leftPoint = L - 1;
        int rightPoint = R + 1;
        int num = arr[R];
        while (L < rightPoint) {
            if (arr[L] < num) {
                swap(arr, ++leftPoint, L++);
            } else if (arr[L] > num) {
                swap(arr, --rightPoint, L);
            } else {
                L++;
            }
        }
        return new int[] {leftPoint, rightPoint};
    }

    public static void swap (int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
