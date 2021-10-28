package algorithms.entry.chap02;


import algorithms.Compare;



public class Code02_SmallSumCheck extends Compare {

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
            int low = lowMethod(arr1);
            int high = Code02_SmallSum.getSmallSum(arr2);
            if (low != high) {
                succeed = false;
//                System.out.printf("第%d次比较错误,low=%d,high=%d\n", i, low, high);
//                System.out.println(Arrays.toString(arr1));
//                System.out.println(Arrays.toString(arr2));
//                break;
                err++;
            } else {
//                System.out.println(Arrays.toString(arr1));
//                System.out.println(Arrays.toString(arr2));
//                System.out.printf("第%d次比较正确,low=%d,high=%d\n", i, low, high);
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
    }


    public static int lowMethod(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j =  0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    sum += arr[j];
                }
            }
        }
        return sum;
    }
}
