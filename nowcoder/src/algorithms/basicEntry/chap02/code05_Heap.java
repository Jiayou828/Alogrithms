package algorithms.basicEntry.chap02;

import java.util.Arrays;

/**
 *  heap :
 *                                       对于每一个节点，它都大于自己的左右孩子  -->   大顶堆
 *      堆：首先要保证是一颗完全二叉树 -->
 *                                       对于每一个节点，它都小于自己的左右孩子  -->   小顶堆
 *      堆(包括完全二叉树) ： 1.对于在数组中存储下标为i的节点来说，它的左孩子下标为2*i + 1，右孩子下标2*i + 2
 *                          2.对于N个数组成的完全二叉树，它的层数为(int)log2 N，反之对于一颗层数为n的二叉树，
 *                          它的节点个数不超过2^n，只有是满二叉树时节点个数确定是2^n - 1
 *      二叉树叶子节点个数n0计算公式 ： 对于普通二叉树 -->  n0 = n2 + 1  -->  其中n2代表该二叉树中，度为2的子节点的个数
 *                                   对于满二叉树 -->  n / 2 向上取整  -->  其中n代表二叉树中，节点的个数
 *                                               --> 2 ^ (n - 1)    -->   其中n代表二叉树的层数
 *  heapInsert() ：将要进入堆的元素下标arr[i]，与其父节点比大小，arr[(i-1)/2]，以大顶堆为例，如果arr[i]>arr[(i-1)/2]，
 *                 交换他们的位置，直到大于条件不成立，或者该元素到顶位置，这个过程是heapInsert，
 *                 每次insert，调整的时间复杂度logN，因为它只会在某一高度上依次向上遍历。
 *  popMAX() ：对于一个排好序的大顶堆，get到顶上最大的元素后，从堆中移除，还能保证该堆依然满足大顶堆的性质。
 *             方法：直接get到arr[0]，即为max值，然后将数组中堆的最后一个有效数arr[heapSize]，copy to arr[0]，
 *             然后do heapify()操作，之后 heapSize - 1
 *  heapify() ：将一个不满足堆性质的完全二叉树，从上往下调整成大/小顶堆
 *              对于起始节点arr[index]，找到它的左右两个孩子，分别是arr[2*index + 1]和arr[2*index + 2]，
 *              do max(arr[index], max(arr[2*index + 1],arr[2*index + 2]))，即arr[index]和左右两个孩子中
 *              较大数交换，依次遍历，直到arrive heapSize结束后即为大顶堆。该方法调整的时间复杂度也是logN
 *  heapSort() : 将一个已调整成大顶堆的数组，做popMax即可，heapSize之外的区域就是排好序的数组
 *
 *  Attension : 在调整成大顶堆的的过程中，方法一：用户一个数一个数给你，然后你做heapInsert
 *                                     方法二：用户一次性把所有的数给你，然后你从下往上以每一个数为父节点依次做heapify
 *
 */
public class code05_Heap {
    public static void main(String[] args) {
        int[] arr = {4, 2, 3, 1, 6, 5};

        heapSort(arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }

    private static void heapSort(int[] arr, int index, int heapSize) {
        if (arr == null || arr.length < 2)
            return;

        //make arr a Max Heap --> Method one : use heapInsert() one by one
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }

        //make arr a Max Heap --> Method two : use heapipy() to arr from low to high
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, heapSize);
        }
        System.out.println(Arrays.toString(arr));

        popMax(arr, index, heapSize);

    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, (index - 1) / 2, index);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            int lagest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            lagest = arr[lagest] > arr[index] ? lagest : index;
            if (lagest == index)
                break;
            swap(arr, index, lagest);
            index = lagest;
            left = 2 * index + 1;
        }
    }

    public static void popMax(int[] arr, int index, int heapSize) {
        swap(arr, index, --heapSize);
        while (heapSize > 0) {
            heapify(arr, index, heapSize);
            swap(arr, index, --heapSize);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
