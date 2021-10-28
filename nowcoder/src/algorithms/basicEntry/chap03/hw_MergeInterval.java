package algorithms.basicEntry.chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class hw_MergeInterval {
    public static void main(String[] args) {
        int[][] intervals1 = new int[][] {{1, 3}, {4, 6}, {8, 10}, {15, 18}};
        ArrayList<Interval> intervals2 = new ArrayList<>();
        intervals2.add(new Interval(1, 3));
        intervals2.add(new Interval(4, 6));
        intervals2.add(new Interval(8, 10));
        intervals2.add(new Interval(15, 18));
       /* //验证直接合并区间函数merge()   --  //leetcode  56. 合并区间  AC
        for (int[] arr : merge(intervals1)) {
            System.out.print(Arrays.toString(arr) + "   ");
        }*/

        //验证插入新区间后，合并
        for (Interval it: insertV2(intervals2, new Interval(2, 5))) {
            System.out.println(it);
        }
    }

    public static int[][] merge(int[][] intervals) {    //leetcode  56. 合并区间
        //输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
        //输出: [[1,6],[8,10],[15,18]]
        if (intervals == null) {
            return null;
        }
        //1.将区间按照第一个数字 从小到大排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int[][] ans = new int[intervals.length][2];
        ans[0] = intervals[0];
        int index = 0;
        for (int i = 0; i < intervals.length; i++) {
            //2.拿ans里的最后一个区间ans[index]的right和当前遍历到的区间intervals[i]的left做对比
            if (intervals[i][0] > ans[index][1]) {     //不用合并，直接添加进ans  如： [1,4]   [6,8]
                ans[++index] = intervals[i];
            } else {    //需要合并，不添加新区间，更改最后一个区间的right  如: [1,4]  [3,5]   -->   [1,5]
                ans[index][1] = Math.max(ans[index][1], intervals[i][1]);
            }
        }
        return Arrays.copyOf(ans, index + 1);
    }

    public static int[][] insertV1(int[][] intervals, int[] newInterval) {    //leetcode  57. 插入区间
        //输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]]  newInterval = [4,8]
        //输出: [[1,2],[3,10],[12,16]]
        if (intervals == null || newInterval == null) {
            return null;
        }
        int[][] tempIntervals = new int[intervals.length + 1][];
        for (int i = 0; i < intervals.length; i++) {
            tempIntervals[i] = intervals[i];
        }
        tempIntervals[intervals.length] = newInterval;
        //1.将区间按照第一个数字 从小到大排序
        Arrays.sort(tempIntervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int[][] ans = new int[tempIntervals.length][2];
        ans[0] = tempIntervals[0];
        int index = 0;
        for (int i = 0; i < tempIntervals.length; i++) {
            //2.拿ans里的最后一个区间ans[index]的right和当前遍历到的区间intervals[i]的left做对比
            if (tempIntervals[i][0] > ans[index][1]) {     //不用合并，直接添加进ans  如： [1,4]   [6,8]
                ans[++index] = tempIntervals[i];
            } else {    //需要合并，不添加新区间，更改最后一个区间的right  如: [1,4]  [3,5]   -->   [1,5]
                ans[index][1] = Math.max(ans[index][1], tempIntervals[i][1]);
            }
        }
        return Arrays.copyOf(ans, index + 1);
    }

    public static ArrayList<Interval> insertV2(ArrayList<Interval> intervals, Interval newInterval) {
        int ns = newInterval.start;
        int ne = newInterval.end;
        ArrayList<Interval> ans = new ArrayList<>();
        int i;
        for (i = 0; i < intervals.size(); i++) {
            if (ne < intervals.get(i).start) {
                //如果ne比老区间的起始点都小那么可以直接退出   如： [2,4] [5,7]  插入[1,1]
                break;
            } else if (intervals.get(i).end < ns) {
                //如果ns比老区间的终点都要大，那么把当前的区间加入ans   如： [2,4] [5,7]  插入[8,9]
                ans.add(intervals.get(i));
            } else {
                //不满足上面情况就更新区间  如：[2,4] [5,7]  插入[3,6]
                ns = Math.min(intervals.get(i).start, ns);
                ne = Math.max(intervals.get(i).end, ne);
            }
        }
        //System.out.println("i:" + i);
        newInterval.start = ns;
        newInterval.end = ne;
        ans.add(newInterval);
        for (; i < intervals.size(); i++) {
            ans.add(intervals.get(i));
        }
        return ans;
    }

    static class Interval {
        int start;
        int end;
        Interval() {
            start = 0; end = 0;
        }
        Interval(int s, int e) {
            start = s; end = e;
        }

        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }
}
