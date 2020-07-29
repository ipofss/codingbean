package sorts;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author: wangbingshuai
 * @create: 2020-07-29 17:43
 **/
public class BubbleSort {
    public void bubbleSort(int[] array) {
        int n = array.length;
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {3, 5, 4, 1, 2, 6};
        Arrays.stream(array).forEach(o -> System.out.print(o + " "));
        System.out.println();
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.bubbleSort(array);
        Arrays.stream(array).forEach(o -> System.out.print(o + " "));
        System.out.println();
    }
}
