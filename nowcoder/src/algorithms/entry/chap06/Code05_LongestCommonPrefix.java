package algorithms.entry.chap06;

//leetcode - 14. 最长公共前缀
public class Code05_LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        String ans = "";
        Tire root = new Tire();
        for(int i=0;i<strs.length;i++)
        {
            Tire p = root;
            if (strs[i] == null)
                return ans;
            int index = 0;
            char[] arr = strs[i].toCharArray();
            for(int j = 0; j < strs[i].length(); j++)
            {
                index = arr[j] - 'a';
                if(p.children[index] == null)
                {
                    p.children[index]=new Tire();
                    p.size++;
                }
                p = p.children[index];
            }
            p.isEnd = true;
        }
        Tire q = root;
        int k = 0;
        while(q.size == 1 && q.isEnd == false)
        {
            for(k = 0; k < 26; k++)
            {
                if(q.children[k] != null)
                {
                    char temp = (char) (k + 'a');
                    ans += temp;
                    break;
                }
            }
            q = q.children[k];
        }
        return ans;
    }

    static class Tire{
        Tire[] children = new Tire[26];
        int size;
        boolean isEnd=false;
        Tire()
        {
            size=0;
            for(int i = 0; i < 26; i++)
            {
                children[i] = null;
            }
        }
    }
}