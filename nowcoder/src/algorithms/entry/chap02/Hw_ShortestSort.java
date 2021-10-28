package algorithms.entry.chap02;


public class Hw_ShortestSort {
    public static void main(String[] args) {
        int[] arr = {1,5,3,4,2,6,7};
        int n = 7;
        System.out.println(findShortest(arr, 7));
    }

    public static int findShortest(int[] A, int n) {
        // write code here
        int[] copyA = new int[n];
        System.arraycopy(A, 0, copyA, 0, n);
        sort(copyA);
        int p1 = 0, p2 = n - 1;
        while (p1 <= p2 && copyA[p1] == A[p1])
            p1++;
        if (p1 == n - 1)
            return 0;
        while (p1 <= p2 && copyA[p2] == A[p2])
            p2--;
        return p2 - p1 + 1;
    }

    public static void sort(int[] arr) {
        //Insert Sort
        for (int i = 1; i < arr.length; i++) {
            int p = i;
            while (arr[p - 1] > arr[p]) {
                int tmp = arr[p-1];
                arr[p-1] = arr[p];
                arr[p] = tmp;
                p = p - 1;
            }
        }
    }
}
