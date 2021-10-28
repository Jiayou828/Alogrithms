package algorithms.basicEntry.chap01;

public class code06_GetMaxRecursion {
    public static void main(String[] args) {
        int maxSize = 10;
        int maxValue = 100;
        int[] arr = logarithm.generateRandomArray(maxSize,maxValue);
        int ans = getMax(arr);
        System.out.println(ans);
    }


    public static int getMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("err:no arr!");
        }
        return process(arr,0,arr.length-1);
    }

    //递归arr求最大值
    public static int process(int[] arr, int L, int R){
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int leftMax = process(arr,L,mid);
        int rightMax = process(arr,mid+1,R);
        return Math.max(leftMax,rightMax);
    }
}
