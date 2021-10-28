package algorithms.basicEntry.chap02;

import java.util.Arrays;

/**
 *
 * 最普遍的快排方法：
 * 1.取随机数和数组的第一个数arr[l]交换
 * 2.变量x保存arr[l]的值
 * 3.以x为划分值，数组中arr[l]的位置被当作临时交换空间，将大于等于x的值放在数组右边，小于x的值放在数组左边
 * 4.左右指针相遇时(leftPoint==rightPoint)，一趟遍历结束，恢复arr[l]值即x值的位置，arr[point] = x
 *
 */
public class code12_QuickSortV4 {
    public static void main(String[] args) {
        //int[] arr = {3, 6, 4, 7, 2, 9, 0, 1, 4, 3, 5, 3};
        int[] arr = {3, 4, 9 ,10, 90, -9, 900,273, 847};
        quickSort(arr, 0, arr.length - 1);
        //int a = getIndex(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (arr == null || arr.length < 2)
            return;
        if (l < r) {
            int index = getIndex(arr, l, r);
            quickSort(arr, l, index - 1);
            quickSort(arr, index + 1, r);
        }
    }

    public static int getIndex(int[] arr, int l, int r) {
//        int leftPoint = l;
//        int rightPoint = r;
//        swap(arr, l + (int) (Math.random() * (r - l + 1)), l);
        //swap(arr, 4, l);
//        int x = arr[l];
//        while (leftPoint < rightPoint) {
//            //当队尾的元素大于等于基准数据时,向前挪动high指针
//            while (leftPoint < rightPoint && arr[rightPoint] >= x) {
//                rightPoint--;
//            }
//            //如果队尾元素小于x了,需要将其赋值给low
//            arr[leftPoint] = arr[rightPoint];
//            //当队首元素小于等于tmp时,向前挪动low指针
//            while (leftPoint < rightPoint && arr[leftPoint] <= x) {
//                leftPoint++;
//            }
//            //当队首元素大于tmp时,需要将其赋值给high
//            arr[rightPoint] = arr[leftPoint];
//        }
//        // 跳出循环时low和high相等,此时的low或high就是tmp的正确索引位置
//        // 由原理部分可以很清楚的知道low位置的值并不是tmp,所以需要将tmp赋值给arr[low]
//        arr[leftPoint] = x;
//        //System.out.println("随机数是：" + x + "索引是：" + leftPoint);
//        return leftPoint;

        int left = l;
        int right = r;
        swap(arr, l + (int) (Math.random() * (r - l + 1)), l);
        int num = arr[l];
        //System.out.println("num=" + num);
        while (left < right) {
            while (left < right && arr[right] >= num) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= num) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = num;
        return left;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
