package algorithms.entry.chap03;

import java.util.Arrays;

/**
 * 笔试不省空间：一切为了时间复杂度
 * 面试可以权衡
 */

public class Code03_CountSortV2 {
    //计数排序：使用于数据范围有限的数据状况，比如年龄(岁)：[0, 200]  身高(cm)：[0,300]
    //先求出数组的max和min，然后开辟数组[min,max]，可以节省空间，对每个数出现的次数进行统计，然后根据每个数的词频再把他们倒回原数组，完成排序
    public static void main(String[] args) {
        int[] ageArr = {25, 54, 33, 20, 3, 7, 90};
        sort(ageArr);
        System.out.println(Arrays.toString(ageArr));
    }


    public static void sort(int[] ageArr) {
        int max = ageArr[0];
        int min = ageArr[0];
        for (int i = 0, j = 0; i < ageArr.length && j < ageArr.length; i++, j++) {
            if (ageArr[i] > max)
                max = ageArr[i];
            if (ageArr[j] < min)
                min = ageArr[j];
        }
        int[] bucket = new int[max - min + 1];
        for (int i = 0; i < ageArr.length; i++) {
            bucket[ageArr[i] - min]++;
        }
        for (int i = 0, j = 0; i < bucket.length && j < ageArr.length; i++) {
            while (bucket[i] != 0) {
                ageArr[j++] = i + min;
                bucket[i]--;
            }
        }
    }

}
