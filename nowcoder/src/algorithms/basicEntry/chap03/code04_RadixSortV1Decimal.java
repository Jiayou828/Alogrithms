package algorithms.basicEntry.chap03;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class code04_RadixSortV1Decimal {
    public static void main(String[] args) {
        int[] arr = {4, 12, 9, 43, 9989, 2, 34, 0};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        //定义基数10，即按10进制进行排序
        final int RADIX = 10;
        //创建10个桶，每个桶的最大容量默认为数组长度
        int[][] bucket = new int[RADIX][arr.length];
        //创建记录每个桶中元素个数的数组
        int[] bucketCount = new int[RADIX];
        //获取数组中最大的数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //获取最大数的位数
        int maxLen = (max+"").length();
        //装桶倒桶 一共需要maxLen次
        for (int i = 0; i < maxLen; i++) {
            //装桶：遍历数组
            for (int j = 0; j < arr.length; j++) {
                //得到位数  0-->个位   1-->十位   2-->百位
                int dig = getDigits(arr[j], i, RADIX);
                //根据位数进桶
                bucket[dig][bucketCount[dig]++] = arr[j];
            }
            int index = 0;
            //出桶：遍历每一个桶
            for (int k = 0; k < RADIX; k++) {
                //单独桶
                for (int num = 0; num < bucketCount[k]; num++) {
                    arr[index++] = bucket[k][num];
                }
                //取完后，将该桶的计数器归0
                bucketCount[k] = 0;
            }
        }
    }

    public static int getDigits(int num, int digit, int RADIX) {
        //digit:代表取的位数  -->   (0,1,2) --> (个，十，百)
//        int dig = (int) Math.pow(10, digit);
//        num /= dig;
//        int res = num % 10;
//        return res;
        return ( (num / (int)Math.pow(RADIX, digit)) % RADIX);
    }



}
