package algorithms.basicEntry.chap01;

import java.util.Arrays;

/**
 * @Author:
 * @Description: 冒泡排序 --> 从头开始遍历数组，如果前面的数比后面的数字大，则交换两个数，每次遍历都把最大的数字沉底
 * 如：        9 2 5 0 1 4 3
 * 第一趟：    2 9 5 0 1 4 3    2 5 9 0 1 4 3   2 5 0 9 1 4 3   2 5 0 1 9 4 3   2 5 0 1 4 9 3   2 5 0 1 4 3 9
 * 第二趟：    2 0 5 1 4 3 9    2 0 1 5 4 3 9   2 0 1 4 5 3 9   2 0 1 4 3 5 9
 * 第三趟：    0 2 1 4 3 5 9    0 1 2 4 3 5 9   0 1 2 3 4 5 9
 * 时间复杂度O(n^2)  空间复杂度O(1)  稳定
 * @version: v1.0
 * @Date:
 *
 **/
public class code02_BubbleSort {
    public static void main(String[] args) {
        int[] arr = {9, 2, 5, 0, 1, 4, 3};
        sort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort1(int[] arr) {
        for (int i= 0; i < arr.length;i++) {
            for (int j = 0; j < arr.length - i - 1;j++) {
                if (arr[j] > arr[j+1]) {
                    arr[j] = arr[j] ^ arr[j+1];
                    arr[j+1] = arr[j] ^ arr[j+1];
                    arr[j] = arr[j] ^ arr[j+1];
                }
            }
            //System.out.println(Arrays.toString(arr));
        }
    }

    public static void sort2(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }


}

