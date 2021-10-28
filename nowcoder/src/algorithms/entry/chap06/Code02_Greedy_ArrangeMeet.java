package algorithms.entry.chap06;

import java.util.Arrays;

/**
 * 贪心算法：
 * [概念]在某一个标准下，优先考虑最满足标准的样本，最后考虑最不满足标准的样本，最终得到一个答案的算法，叫做贪心算法
 * 也就是说，不从整体最优上加以考虑，所做出的是在某种意义上的局部最优解
 * 我们要做的，是从局部最优中找到整体最优
 * [套路]
 * 1.实现一个不依靠贪心策略的解法x，可以用暴力的尝试
 * 2.脑补出贪心策略A,贪心策略B,贪心策略C
 * 3.用解法X和对数器，去验证每一个贪心策略，用实验的方式得知哪个贪心策略正确
 * 4.不要纠结贪心策略的证明
 * [实现]
 *  1，根据某标准建立一个比较器来排序
 *　2，根据某标准建立一个比较器来组成堆
 */
public class Code02_Greedy_ArrangeMeet {
    public static void main(String[] args) {
        test1();
    }

    /**
     * [题目] 安排会议
     * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
     * 给你每一个项目开始的时间和结束的时间(给你一个数组，里面是一个个具体的项目)，你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
     * 返回这个最多的宣讲场次。
     * [举例] 给三个会议时间点：(6,12),(7,8),(8,9)  安排(7,8)和(8,9)
     * [分析] 贪心策略A: 会议开始早的先安排
     *       贪心策略B: 会议持续短的先安排
     *       贪心策略C: 会议结束早的先安排  -->  整体最优解(证明略)
     *
     */
    public static void test1() {
        //测试ArrangeMeet()
        int[][] arr = {{6,12},{7,8},{8,9}};
        System.out.println(arrangeMeet(arr));
    }
    public static int arrangeMeet(int[][] events) {
        //按会议结束时间排序
        Arrays.sort(events, ((o1, o2) -> o1[1] - o2[1]));
        int startTime = 0;
        int ans = 0;
        for (int i = 0; i < events.length; i++) {
            if (events[i][0] >= startTime) {
                ans++;
                startTime = events[i][1];
            }
        }
        return ans;
    }

    /**
     * [题目] 安排会议 -- 拓展
     * 给你一个数组events，其中events[i] = [startDayi, endDayi]，表示会议i开始于startDayi，结束于endDayi。
     * 你可以在满足startDayi<= d <= endDayi中的任意一天d参加会议i。注意，一天只能参加一个会议。
     * 请你返回你可以参加的最大会议数目。
     * 来源：力扣（LeetCode）1353. 最多可以参加的会议数目
     * 链接：https://leetcode-cn.com/problems/maximum-number-of-events-that-can-be-attended
     * []
     * 输入：events = [[1,2],[2,3],[3,4]]
     * 输出：3
     * 解释：你可以参加所有的三个会议。
     * 安排会议的一种方案如上图。
     * 第 1 天参加第一个会议。
     * 第 2 天参加第二个会议。
     * 第 3 天参加第三个会议。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-number-of-events-that-can-be-attended
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * [分析] 相比上题，数据由连续变为离散型数据
     */
    public static int maxEvents(int[][] events) {
        //waiting...
        return 0;
    }
}
