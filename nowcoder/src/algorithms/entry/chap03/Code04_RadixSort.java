package algorithms.entry.chap03;

/**
 * 基数排序要求：数据必须是二进制，最好只有非正数
 * step:
 *  1. prepare to 10 buckets(queue), from 0 to 9, which represents binary 0 - 9
 *  2. converts(转变) all numbers in the array to three bits binary numbers, with 0 in front of those less than max bits(以最大数的位数为基准，把小数补0)
 *  3. then put those binary numbers in the buckets according to the unit(个位) number
 *  4. after 3, pour(倒出) the number in the bucket from left to right, numbers in the same bucket follow
 *     first-in first-out(fifo), into the original array.
 *  5. then like step 2 -- put those numbers in the buckets again, but need to according to the tens digit
 *  6. after 5, pour the number in the bucket from left to right again, the rule follow step 4
 *  7. finally, do the same thing a third time, according to the hundreds digit, and pour them
 *  8. you will find that they are already in order amazingly at this time.
 */
public class Code04_RadixSort {
    public static void main(String[] args) {
        int[] arr = {4, 12, 9, 43, 89, 2, 34};
//        sort(arr);
//        System.out.println(arr);
        System.out.println(DecimalismToBinary(8));
    }

    public static void sort(int[] arr) {
        //1.将数组转为二进制
        String[] binArr = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            binArr[i] = DecimalismToBinary(arr[i]);
        }
    }

    public static String DecimalismToBinary(int n) {
        int time = 0;
        int bin = 0;
        while (n != 0) {
            bin += n % 2 * Math.pow(10, time);
            n /= 2;
            time++;
        }
//        return "0b" + bin;
        return bin + "";
    }
}