package algorithms.basicEntry.chap01;

import java.util.Arrays;

public class logarithm {
    public static void main(String[] args) {
        int maxSize = 1000;
        int maxValue = 1000;
        int testTime = 500000000;

        boolean succeed = true;

        for (int i = 0; i < testTime;i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            comparator(arr1);
            code01_SelectSort.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            } else {
//                System.out.println(Arrays.toString(arr1));
//                System.out.println(Arrays.toString(arr2));
                System.out.printf("第%d次比较,arr1和arr2相等",i);
                System.out.println();
            }
        }
        System.out.println(succeed? "Nice!" : "Fucking fucked!");
    }

    //for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }
    //for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int)((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
        }
        return arr;
    }
    //for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }
    //for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length)
            return false;
        for (int i = 0; i < arr1.length;i++) {
            if (arr1[i] != arr2[i])
                return false;
        }
        return true;
    }
    //for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " ");
        }
    }
}
