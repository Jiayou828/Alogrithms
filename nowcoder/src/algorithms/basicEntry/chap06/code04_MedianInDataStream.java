package algorithms.basicEntry.chap06;


import java.util.Comparator;
import java.util.PriorityQueue;

public class code04_MedianInDataStream {
    public static void main(String[] args) {
        MedianHolder ml = new MedianHolder();
        int[] arr = new int[] {3, 4, 0, 23, 5};
        for (int i = 0; i < arr.length; i++) {
            ml.addNum(arr[i]);
            System.out.println(ml.findMedian());
        }
    }
    /**
     * [题目] 数据流中的中位数 leetcode 剑指 Offer 41. 数据流中的中位数 / 主站 296
     * [分析] 准备一个大根堆和一个小根堆，第一个数先进大根堆，后续进来的数都和大根堆的堆顶比较，
     * 如果大于堆顶的数，进小根堆，如果小于等于大根堆的堆顶还进大根堆。但是，在每次执行上述操作
     * 之后，都要检查一下两个堆的size()差，如果相差超过2，那么数量多的堆顶弹出，进另外一个堆。
     * 如此以来，较小的N/2个数都在小根堆里，较大的N/2个数都在大根堆里，如果两个堆的size相等
     * 中位数就是堆顶相加除以2，如果不想等，中位数就是size较大的堆的堆顶
     */
    public static class MedianHolder{
        private PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int count = 0;

        public void addNum(int num) {
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
            if (this.minHeap.size() == this.maxHeap.size() + 2) {
                this.maxHeap.add(this.minHeap.poll());
            }
            if (this.maxHeap.size() == this.minHeap.size() + 2) {
                this.minHeap.add(this.maxHeap.poll());
            }
            count++;
        }

        public double findMedian() {
            int minHeapSize = this.minHeap.size();
            int maxHeapSize = this.maxHeap.size();
            if (minHeapSize + maxHeapSize == 0) {
                return 0;
            }
            Integer maxHead = this.maxHeap.peek();
            Integer minHead = this.minHeap.peek();
            assert maxHead != null;
            assert minHead != null;
            if (minHeapSize == maxHeapSize) {
                return  (double) (maxHead + minHead) / 2;
            }
            return (double) maxHeapSize > minHeapSize ? maxHead : minHead;
        }

/*        public double findMedian2() {
            if ((count & 1) == 0) {
                // 如果两个堆合起来的元素个数是偶数，数据流的中位数就是各自堆顶元素的平均值
                return (double) (maxHeap.peek() + minHeap.peek()) / 2;
            } else {
                // 如果两个堆合起来的元素个数是奇数，数据流的中位数大顶堆的堆顶元素
                return (double) maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
            }
        }*/
    }
}
