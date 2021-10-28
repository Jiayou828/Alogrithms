package algorithms.basicEntry.chap06;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * [前缀树]
 * 一个字符串类型的数组arr1，另一个字符串类型的数组arr2。
 * arr2中有哪些字符，是arr1中出现的?请打印。
 * arr2中有哪些字符，是作为arr1中某个字符串前缀出现的?请打印。
 * arr2 中有哪些字符，是作为arr1中某个字符串前缀出现的?请打印
 * arr2中出现次数最大的前缀。
 */
public class code01_PrefixTree {

}

class PrefixTree {  // leetcode 208. 实现 Trie (前缀树)
    /**
     * [前缀树节点]
     * pass表示该节点通过的次数，end表示以该节点作为结尾的字符串的个数
     * 使用数组或哈希表代表每个节点下的路径，路径代表字符。
     * 比如纯英文的字符串，无非就是26个英文字母的排列组合，那么就使用一个26个元素的数组
     */
    static class Node{
        public int pass;
        public int end;
        public Node[] next;
        public Node() {
            pass = 0;
            end = 0;
            // HashMap<Character, Integer> map = new HashMap<>();
            // TreeMap<Character, Integer> map = new TreeMap<>();
            next = new Node[26];
        }
    }

    private Node root;

    public PrefixTree() {
        root = new Node();
    }

    //把一个字符串添加进前缀树
    public void insert(String word) {
        if (word == null) {
            return;
        }
        char[] arrStr = word.toCharArray();
        int index = 0;
        Node cur = root;
        cur.pass++;
        for (int i = 0; i < arrStr.length; i++) {
            index = arrStr[i] - 'a';
            if (cur.next[index] == null) {
                cur.next[index] = new Node();
            }
            cur = cur.next[index];
            cur.pass++;
        }
        cur.end++;
    }

    //查询word这个单词一共加入过几次
    public int search(String word) {
        if (word == null) {
            return 0;
        }
        char[] arr = word.toCharArray();
        Node cur = root;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            index = arr[i] - 'a';
            if (cur.next[index] == null) {
                return 0;
            }
            cur = cur.next[index];
        }
        return cur.end;
    }

    // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
    public int prefixNumber(String prefix) {
        char[] arr = prefix.toCharArray();
        int index = 0;
        Node cur = root;
        for (int i = 0; i < arr.length; i++) {
            index = arr[i] - 'a';
            if (cur.next[index] == null) {
                return 0;
            }
            cur = cur.next[index];
        }
        return cur.pass;
    }

    public void delete(String word) {
        if (search(word) != 0) {
            char[] arr = word.toCharArray();
            int index = 0;
            Node cur = root;
            for (int i = 0; i < arr.length; i++) {
                index = arr[i] - 'a';
                if (--cur.next[index].pass == 0) {
                    cur.next[index] = null;
                    return;
                }
                cur = cur.next[index];
            }
            cur.end--;
        }
    }

    // leetcode -- 14. 最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        for (int i = 0; i < strs.length; i++) {
            insert(strs[i]);
        }
        Node cur = null;
        Node[] curArr = null;
        while (true) {
            cur = root;
            curArr = cur.next;

        }
//
//        Node[] curArr = cur.next;
//        Node[] cur = root.next;
//        while (cur.length == 1) {
//
//        }

    }

}