package algorithms.basicEntry.chap02;

import java.util.Scanner;

public class hw_StringSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String string = sort(str);
            System.out.println(string);
        }
    }

    public static String sort(String str) {
        char[] arr = str.toCharArray();
        for (int i = arr.length - 1; i >= 0; i--) {
            int p = 0;
            for (int j = 0; j < i; j++) {
                if (isLetter(arr[j])) {
                    p = j + 1;
                    while (p < i && !isLetter(arr[p]))
                        p++;
                    if (isLetter(arr[p]) && normalization(arr[p]) < normalization(arr[j])) {
                        char tmp = arr[p];
                        arr[p] = arr[j];
                        arr[j] = tmp;
                    }
                    j = p - 1;
                }
            }
        }
        return new String(arr);
    }

    public static boolean isLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    public static int normalization(char c) {
        return c >= 'A' && c <= 'Z' ? c - 'A' : c - 'a';
    }

}

//偶然发现，字符串数组转换成字符串有点分歧。
//
//        方法一：直接用数组转字符串方法效果如下
//
//        1 char[] c1 = new char[]{'a','d','s'};
//        2 return = Arrays.toString(c1);
//        输出效果：[a, d, s]
//
//
//
//        方法二：使用StringBuffer转换
//
//        1 char[] c4 = new char[]{'a','d','s','a','d','s'};
//        2 StringBuffer sb = new StringBuffer();
//        3 for(int i = 1;i<c4.length;i++){
//        4     sb.append(c4);
//        5 }
//        6 System.out.println(c4);
//        输出效果：adsads
//
//        方法三：推荐使用
//
//        1 char[] c4 = new char[]{'a','d','s','a','d','s'};
//        2 return new String(c4);
//        输出效果：adsads
//
//        原因：
//
//        String构造方法中，
//
//        String(char[] value)
//        分配一个新的 String，使其表示字符数组参数中当前包含的字符序列。
//
//
//
//        在此，需要使用到数组转换字符串，推荐第三种方法。