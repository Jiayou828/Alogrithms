package algorithms.entry.chap03;


import java.util.*;

public class Code05_RadixSortV2Binary {
    public static void main(String[] args) {
        int[] arr = {4, 12, 9, 43, 89, 2, 34, 7};
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = DecimalizeToBinary(arr[i]);
//        }
        //arr 转为[0b100, 1100, 1001, 101011, 1011001, 10, 100010, 111]
        //对二进制数组排序
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        //定义基数2，即按2进制进行排序
        final int RADIX = 2;
        //获取数组中最大的数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //获取最大数的位数
        int maxLen = (DecimalizeToBinary(max) + "").length();
        //创建RADIX个桶
        System.out.println(maxLen);
        //装桶倒桶 一共需要maxLen次
        for (int i = 0; i < maxLen; i++) {
            List<Integer> l0= new ArrayList<>();
            List<Integer> l1= new ArrayList<>();
            //装桶：遍历数组
            for (int j = 0; j < arr.length; j++) {
                //得到位数  0-->个位   1-->十位   2-->百位
                //根据位数进桶
                if ((arr[j]>>i&1) == 0) {
                    l0.add(arr[j]);
                } else{
                    l1.add(arr[j]);
                }
            }
            int index = 0;
            //出桶：遍历每一个桶
            for (int k = 0; k < l0.size(); k++) {
                arr[k] = l0.get(k);
            }
            for (int k = 0; k < l1.size(); k++) {
                arr[k + l0.size()] = l1.get(k);
            }
        }
    }

    public static int getDigits(int num, int digit) {
        //num >> digit & 1   右移(0,1,2...)位 & 1  -->  取num的(个，十，百)位。
        int res = ((num >> digit) & 1);
        return res;
    }


    public static int BinToBin(int num) {
        //num是一个二进制的样式，但是进入函数以后它参与的计算均为十进制
        //此函数将二进制的样式的int数据转化为十进制
        String temp = num + "";
        char[] arr = temp.toCharArray();
        int res = 0;
        int index = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            //先把字符转为int
            int a = Integer.parseInt(arr[i] + "");
            res += a * (int)Math.pow(2, index++);
        }
        return res;
    }

    public static int DecimalizeToBinary(int n) {
        int time = 0;
        int bin = 0;
        while (n != 0) {
            bin += n % 2 * Math.pow(10, time);
            n /= 2;
            time++;
        }
//        return "0b" + bin;
        return bin;
    }
}


        /* 心得：1.刚开始时，考虑把所有数用补零的方式统一位数，再排序。实际没必要，对于位数不足的数返回0即可
        2.取位数还要先把int转为字符串？没必要
        3.希望创建10个优先队列当桶，泛型数组有吗？ 还是用二位数组吧
        //将数组转为二进制, 而且需要每一个数的位数是一样的
        String[] binArr = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            binArr[i] = DecimalizeToBinary(arr[i]);
        }

        int max = binArr[0].length();
        for (int i = 0; i < binArr.length; i++) {
            if (binArr[i].length() > max)
                max = binArr[i].length();
        }
        for (int i = 0; i < binArr.length; i++) {
            int diffLen = max - binArr[i].length();
            while (diffLen != 0) {
                binArr[i] = "0" + binArr[i];
                diffLen--;
            }
        }
        return binArr;
    }

    public static String DecimalizeToBinary(int n) {
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
    */