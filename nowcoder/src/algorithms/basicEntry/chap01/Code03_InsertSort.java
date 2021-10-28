package algorithms.basicEntry.chap01;


import java.util.Arrays;
//常见的优化一个问题的解法：第一从特殊的数据状况入手    第二从特殊的问题性质入手
//最优解都是基于特殊的数据状况和特殊的问题性质再做进一步优化之后，所呈现的具体解法。


/**
 * @Author:
 * @Description: 插入排序  遍历数组，把当前元素放入在它之前的数组中，该放的位置,(依次交换，保证该数比它前面的数大)保证该元素之前的所有元素有序
 * 如：        9 2 5 0 1 4 3
 * 第一趟：    2 9 5 0 1 4 3  交换 9 2
 * 第二趟：    2 5 9 0 1 4 3  交换 9 5
 * 第三趟：    0 2 5 9 1 4 3  交换 9 0  0 5  0 2
 * 第四趟：    0 1 2 5 9 4 3  交换 9 1  1 5  1 2
 * 第五趟：    0 1 2 4 5 9 3  交换 9 4  4 5
 * 第六趟：    0 1 2 3 4 5 9  交换 9 3  3 5  3 4
 * 时间复杂度：O(N^2)  额外空间复杂度O(1)
 * @version: v1.0
 * @Date:
 *
 **/
public class Code03_InsertSort {
    public static void main(String[] args) {
        int[] arr = {9, 2, 5, 0, 1, 4, 3};
//        sort(arr);
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
            //System.out.println(Arrays.toString(arr));
        }
    }



    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length;i++) {
            for (int j = i - 1; j >= 0 && arr[j+1] < arr[j]; j--) {
                int tmp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = tmp;
            }
        }
    }
}
