package algorithms.entry.chap02;

import java.util.Arrays;

/**
 * 堆排序扩展题目：
 * Give you an almost ordered array which means if you want to put the array in order completely,
 * the distance of every element of the array can move no more than a given parameter k, and the
 * parameter k is small relative to the array. Please select an appropriate sorting algorithm for this array.
 *
 * Thinking Method : Build a Min Heap with capacity k. And then do popMin operation meaning while put the arr[k+1]
 * into the Min Heap structure.
 */
public class Code06_HeapExtension {
    public static void main(String[] args) {
        int[] arr = {2,1,4,3,6,5,8,7,10,9,};
        sort(arr, 5);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr, int k) {
        int[] MinHeap = new int[k];
        for (int i = 0; i < k; i++) {
            MinHeap[i] = arr[i];
        }
        for (int i = k - 1; i >= 0; i--) {
            heapify(MinHeap, i, k);
        }
        for (int i = k; i < arr.length; i++) {
            arr[i - k] = MinHeap[0];
            swap(MinHeap, 0, k - 1);
            MinHeap[k - 1] = arr[i];
            heapify(MinHeap, 0, k);
        }
        //使用普通堆排序的方式调整MinHeap中剩余的元素，并加入原数组
        for (int i = arr.length - k; i < arr.length; i++) {
            arr[i] = MinHeap[0];
            swap(MinHeap, 0, --k);
            heapify(MinHeap, 0, k);
        }
    }



    public static void heapify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            int minimum = left + 1 < heapSize && arr[left + 1] < arr[left] ? left + 1 : left;
            minimum = arr[minimum] < arr[index] ? minimum : index;
            if (minimum == index) {
                break;
            }
            swap(arr, minimum, index);
            index = minimum;
            left = 2 * index + 1;
        }

    }

    public static void popMin(int[] arr, int[] MinHeap, int index, int heapSize) {
        int i = 0;
        int len = arr.length;
        while (len > 0) {
            arr[i] = MinHeap[0];

            len--;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
