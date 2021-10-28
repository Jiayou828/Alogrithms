package algorithms.basicEntry.chap02;


import algorithms.compare;

import java.util.Arrays;


public class code01_MergeSortCheck extends compare {
    static long recuUseTime = 0;

    static long noRecuUseTime = 0;

    public static void main(String[] args) throws Exception {
        test();
    }

    public static void test() throws Exception {
        int err = 0;
        int right = 0;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (!arrayIsEqual(arr1,arr2)) {
                throw new Exception("Array Create Error");
            }
            String low = lowMethod(arr1);
            String high = highMethod(arr2);

            if (!low.equals(high)) {
                succeed = false;
                System.out.printf("第%d次比较错误\n", i);
//                System.out.println(Arrays.toString(arr1));
//                System.out.println(Arrays.toString(arr2));
//                break;
                err++;
            } else {
//                System.out.println(Arrays.toString(arr1));
//                System.out.println(Arrays.toString(arr2));
                System.out.printf("第%d次比较正确\n", i);
//                System.out.println(Arrays.toString(arr1));
//                System.out.println(Arrays.toString(arr2));
                right++;
            }
        }
        if (succeed) {
            System.out.println("Accepted");
        } else {
            System.out.printf("Error%d%%",err/testTime);
        }
        System.out.println("recuUseTime:" + recuUseTime);
        System.out.println("noRecuUseTime:" + noRecuUseTime);
    }


    public static String lowMethod(int[] arr) {
        long recuStartTime = System.currentTimeMillis();
        String recursion = Arrays.toString(code01_MergeSort.mergeSort(arr));
        long recuEndTime = System.currentTimeMillis();
        long time = recuEndTime - recuStartTime;
        recuUseTime += time;
        return recursion;
    }

    public static String highMethod(int[] arr) {
        long noRecuStartTime = System.currentTimeMillis();
        String noRecursion = Arrays.toString(code04_MergeSortNoRecursion.mergeSort(arr));
        long noRecuEndTime = System.currentTimeMillis();
        long time = noRecuEndTime - noRecuStartTime;
        noRecuUseTime += time;
        return noRecursion;
    }

//    starTime = System.currentTimeMillis();
//30 StringBuilder str2 = new StringBuilder("*");
//31 for (int i = 0; i < N; i++) {
//        32         str2.append("*");//拼接 追加
//        33
//        34 }
//35 endTime = System.currentTimeMillis();
//36  System.out.println("StringBuilder的append用时："+(endTime - starTime)+"毫秒");
}
