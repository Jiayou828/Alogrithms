package algorithms.basicEntry.chap02;

import java.util.Scanner;

public class hw_AscendDescend {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr;
        while (sc.hasNext()) {
            int n = sc.nextInt();
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            int upDown = sc.nextInt();
            if (upDown == 0) {
                ascend(arr, 0, arr.length - 1);
            } else if (upDown == 1) {
                descend(arr);
            }
            printArr(arr);
        }
    }

    public static void ascend(int[] arr, int left, int right) {
        //use quickSort
        if (arr == null || arr.length < 2)
            return;
        if (left < right) {
            int[] index = quickSort(arr, left, right);
            ascend(arr, left, index[0] - 1);
            ascend(arr, index[1] + 1, right);
        }
    }

    public static int[] quickSort(int[] arr, int left, int right) {
        int rand = left + (int)(Math.random() * (right - left + 1));
        swap(arr, right, rand);
        int leftPoint = left - 1;
        int rightPoint = right;
        while (left < rightPoint) {
            if (arr[left] < arr[right]) {
                swap(arr, ++leftPoint, left++);
            } else if (arr[left] > arr[right]) {
                swap(arr, left, --rightPoint);
            } else {
                left++;
            }
        }
        swap(arr, right, rightPoint);
        return new int[] {leftPoint + 1, rightPoint};
    }

    public static void descend(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        int heapSize = arr.length;
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, heapSize);
        }
        while (heapSize > 0) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
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

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
