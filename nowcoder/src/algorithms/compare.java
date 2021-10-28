package algorithms;


public class compare {
    public static int maxSize = 10000;
    public static int maxValue = 20000;
    public static int testTime = 50000;


    public static boolean succeed = true;


    //产生随机数组
    public static int[] generateRandomArray(int maxSize, int maxValue) {
//        int[] arr = new int[(int)((maxSize + 1) * Math.random())];
        int[] arr = new int[30];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
        }
        return arr;
    }

    //复制数组
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    //比较两个数组public abstract int lowDoGetInt();
    public static boolean arrayIsEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length)
            return false;
        for (int i = 0; i < arr1.length;i++) {
            if (arr1[i] != arr2[i])
                return false;
        }
        return true;
    }


    //打印数组
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " ");
        }
    }


}

