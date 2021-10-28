package algorithms.entry.chap01;

/**
 * 二分查找详解：https://www.cnblogs.com/kyoner/p/11080078.html
 * 二分查找的注意点：1.数组不一定有序才能使用，也可以用来查找局部最值问题
 *               2.时间复杂度：O(log2 N)
 *               3.空间复杂度：O(log2 N)
 *
 * 常见的优化一个问题的解法：第一从特殊的数据状况入手    第二从特殊的问题性质入手
 * 最优解都是基于特殊的数据状况和特殊的问题性质再做进一步优化之后，所呈现的具体解法。
 *
 */

public class Code04_MidSearch {
    public static void main(String[] args) {
        int[] arr = {0, 2, 3, 5, 6, 7, 9, 10};
        int ans = binarySearch(arr,10);
        System.out.println(ans);
    }
    public static int binarySearch(int[] arr, int index) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1); //有效防止当数据量太大时，溢出的问题
            if (arr[mid] == index) {
                return mid;
            } else if (arr[mid] > index){
                right = mid - 1;
            } else if (arr[mid] < index) {
                left = mid + 1;
            }
        }
        return -1;
    }
}
