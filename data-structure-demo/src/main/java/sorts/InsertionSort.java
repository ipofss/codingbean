package sorts;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author: wangbingshuai
 * @create: 2020-07-29 19:15
 **/
public class InsertionSort {
    public void insertionSort(int[] array) {
        int n = array.length;
        if (n <= 1) {
            return;
        }
        for (int i = 1; i < n; i++) {
            int value = array[i];
            int j = i - 1;
            // 全部已排序元素大于待插入元素时，j=-1，则在第一个位置插入value；若能找到一个小于等于value的元素，则在该元素后面插入value
            for (; j >= 0; j--) {
                if (array[j] > value) {
                    // 把大于value的元素右移一位
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = value;
        }
    }

    public static void main(String[] args) {
        int[] array = {4, 5, 6, 1, 3, 2};
        Arrays.stream(array).forEach(o -> System.out.print(o + " "));
        System.out.println();
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.insertionSort(array);
        Arrays.stream(array).forEach(o -> System.out.print(o + " "));
        System.out.println();
    }
}
