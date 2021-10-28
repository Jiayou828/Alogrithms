package algorithms.entry.chap03;

import java.util.Arrays;

/**
 * 之前的排序都是基于比较的
 * 本章的桶排序、计数排序、基数排序均不是基于比较的排序算法，而是基于数据状况
 * 桶排序的公共特点：开辟一个新的容器
 *
 */
public class Code02_CountSortV1 {
    //计数排序：使用于数据范围有限的数据状况，比如年龄(岁)：[0, 200]  身高(cm)：[0,300]
    //然后根据数据值的区间开辟一个数组，对每个数出现的次数进行统计，然后根据每个数的词频再把他们倒回原数组，完成排序
    public static void main(String[] args) {
        int[] ageArr = {25, 54, 33, 20, 3, 7, 90};
        sort(ageArr);
        System.out.println(Arrays.toString(ageArr));
    }


    public static void sort(int[] ageArr) {
        int[] bucket = new int[201];
        for (int i = 0; i < ageArr.length; i++) {
            bucket[ageArr[i]]++;
        }
        for (int i = 0, j = 0; i < bucket.length && j < ageArr.length; i++) {
            while (bucket[i] != 0) {
                ageArr[j++] = i;
                bucket[i]--;
            }
        }
    }

}
