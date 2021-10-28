package algorithms.basicEntry.chap04;

import java.util.HashMap;

public class code01_UnorderMap {
    public static void main(String[] args) {
        HashMap<Integer, String> sm = new HashMap<>();
        sm.put(1, "Lily");
        sm.put(2, "Lucy");

        System.out.println(sm.containsKey(2));
        System.out.println(sm.containsKey(4));
        System.out.println(sm.containsValue("Lily"));
        System.out.println(sm.containsValue("johnny"));

        sm.remove(2);
        System.out.println(sm.containsKey(2));

        sm.put(1, "johnny");
        System.out.println(sm.get(1));
        System.out.println(sm);
    }
}
