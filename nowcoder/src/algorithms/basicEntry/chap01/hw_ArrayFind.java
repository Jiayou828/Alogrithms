package algorithms.basicEntry.chap01;

//在二维数组中查找某一个数
import java.util.Arrays;
import java.util.Scanner;

public class hw_ArrayFind {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] arr = {
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        int target = 7;
        System.out.println(Find(target, arr));
    }


    public static boolean Find(int target, int[][] array) {
        int up = array.length - 1;
        int right = 0;
        while (up >= 0 && right < array[0].length){
            int index = array[up][right];
            if (index == target) {
                return true;
            } else if (index < target) {
                right++;
            } else if (index > target) {
                up--;
            }
        }
        return false;
    }
}
