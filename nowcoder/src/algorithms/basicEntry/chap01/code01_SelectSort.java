//本章的三个排序都是  时间复杂度O(n^2)
package algorithms.basicEntry.chap01;

import java.util.Arrays;

/**
 * @Author:
 * @Description:  选择排序算法 --> 每次从数组的当前位置开始，与后面最小的数交换，保证每次遍历，前面的元素都是有序的
 * 如：         9 2 5 0 1 4 3
 * 第一趟：     0 2 5 9 1 4 3     交换 a[0]=9  和  a[3]=0
 * 第二趟：     0 1 5 9 2 4 3     交换 a[1]=1  和  a[4]=2
 * 第三趟：     0 1 2 9 5 4 3     交换 a[2]=2  和  a[4]=5
 * 第四趟：     0 1 2 3 5 4 9     交换 a[3]=3  和  a[6]=9
 * 第五趟：     0 1 2 3 4 5 9     交换 a[4]=4  和  a[5]=5
 * 时间复杂度O(n^2)  空间复杂度O(1)  不稳定
 * @version: v1.0
 * @Date:
 *
 **/
public class code01_SelectSort {
    public static void main(String[] args) {
        int[] arr = {5 , 4, 3, 2, 1};
        sort(arr);
        //swap(arr,0,4);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1;i++) {
            int midIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                midIndex = arr[j] < arr[midIndex] ? j : midIndex;
            }
            swap(arr, i, midIndex);
        }
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
//        arr[i] = arr[i] ^ arr[j];
//        arr[j] = arr[i] ^ arr[j];
//        arr[i] = arr[i] ^ arr[j];
//        arr[i] = arr[i] + arr[j];
//        arr[j] = arr[i] - arr[j];
//        arr[i] = arr[i] - arr[j];
    }
}
