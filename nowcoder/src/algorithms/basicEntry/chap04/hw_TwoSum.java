package algorithms.basicEntry.chap04;

import java.util.HashMap;
import java.util.Map;

public class hw_TwoSum {
    public static void main(String[] args) {
        int[] numbers = {3, 2, 4};
        int target = 6;
        System.out.println(twoSumV2(numbers, target)[0]);
    }

    public static int[] twoSumV1(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            map.put(target - numbers[i], i);
        }
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(numbers[i]) && map.get(numbers[i]) != i) {
                return new int[] {i, map.get(numbers[i])};
            }
        }
        return null;
    }

    public static int[] twoSumV2(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(numbers[i])) {
                return new int[] {i, map.get(numbers[i])};
            }
            map.put(target - numbers[i], i);
        }
        return null;
    }
}
