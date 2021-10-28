package algorithms.entry.chap01;

/**
 * 1.异或运算就是 --> "无进位" 相加
 * 2.0和任何一个数异或 都是它本身 -->  0 ^ N = N
 * 3.任何一个数和自己异或得到都是0 --> N ^ N = 0
 * 4.异或运算满足交换律和结合律,解释如下：多个数异或结果只与单列上0和1的个数有关和顺序无关
 *       a :    1   0   1   1   0   1   0
 *       b :    1   1   1   0   0   1   1
 *       c :    0   0   1   1   1   0   0
 *       ----------------------------------
 *              0   1   1   0   1   0   1
 * 5.不用额外变量交换两个数
 * 6.一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到这一个数 printOddTimeNum1
 * 7.一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到这两个数
 *
 * 牛逼哄哄的一行代码：取出一个数二进制位最右边的一个1   rightOne = eor & (~eor + 1)
 *
 *          err :   0   1   1   0   1   1   0   1   0   0
 *         ~err :   1   0   0   1   0   0   1   0   1   1
 *       ~err+1 :   1   0   0   1   0   0   1   1   0   0
 * err&(~err+1) :   0   0   0   0   0   0   0   1   0   0
 *
 */

public class Code05_XorOper {

    public static void main(String[] args) {
        int[] arr1 = {0, 0, 0, 1, 1, 2, 2, 3, 3, 3, 3};
        //printOddTimeNum1(arr1);

        int[] arr2 = {0, 0, 0, 1, 1, 2, 2, 3, 3, 3, 3, 5, 5, 5};
        printOddTimeNum2(arr2);
    }

    //数组中只有一个数出现了奇数次，其余数出现了偶数次，打印数组中出现了奇数次的一个数
    public static void printOddTimeNum1(int[] arr) {
        int eor = 0;
        for (int cur : arr) {
            eor ^= cur;
        }
        System.out.println(eor);
    }

    //数组中有两个不同的数(a,b)出现了奇数次，其余数出现偶数次，打印这两个数
    public static void printOddTimeNum2(int[] arr) {
        int eor = 0;
        for (int cur : arr) {
            eor ^= cur;
        }
        //eor = a ^ b != 0
        //说明eor的二进制数中必定有不为0(1)的某一位
        //获取eor二进制位最右侧为1的二进制位
        int rightOne = eor & (~eor + 1);
        int exr = 0;
        for (int cur : arr) {
            if ((cur & rightOne) != 0) {
                exr ^= cur;
            }
        }
        System.out.println(exr + " " + (exr ^ eor));
    }
}
