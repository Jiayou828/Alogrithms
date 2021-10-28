package algorithms.entry.chap03;

import java.util.ArrayList;
import java.util.Arrays;

public class Code06_RadixSortV3Elegance {
    public static void main(String[] args) {
        int[] arr = {4, 12, 9, 43, 9989, 2, 34, 7};
        sort(arr, 0, 7, 4);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr, int left, int right, int digit) {
        final int RADIX = 10;
        int[] bucket = new int[right - left + 1];
        //count数组用来记录每一躺中，0-9号桶中对应0-9数字的个数
        for (int i = 0; i < digit; i++) {
            int[] count = new int[RADIX];
            for (int j = 0; j <= right; j++) {
                int dig = getDigits(arr[j], i);
                count[dig]++;
            }
            //重置count数组，每一个位置的元素，代表大小不超过该位置索引数的数据的个数
            //eg: arr[3] = 6  代表不超过3的数一共有6个
            for (int j = 1; j < count.length; j++) {
                count[j] += count[j - 1];
            }
            for (int m = right; m >= left; m--) {
                //从右往左遍历数组，第一个数是7，count[7]=6，代表小于等于7的数有6个，他们在数组中的位置是0-5
                //将这个数放到0-5范围的位置上的最后一个位置。
                int digi = getDigits(arr[m], i);
                bucket[count[digi] - 1] = arr[m];
                count[digi]--;
            }
            for (int p = 0, q = 0; p <= right; p++, q++) {
                arr[p] = bucket[q];
            }
        }
    }

    public static int getDigits(int num, int radix) {
        return (num / (int)Math.pow(10, radix) % 10);
    }
}
