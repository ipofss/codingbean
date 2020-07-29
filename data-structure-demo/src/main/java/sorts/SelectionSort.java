package sorts;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author: wangbingshuai
 * @create: 2020-07-29 20:10
 **/
public class SelectionSort {
    public void selectionSort(int[] array) {
        int n = array.length;
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int tmp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] array = {2, 5, 3, 1, 4, 6};
        Arrays.stream(array).forEach(o -> System.out.print(o + " "));
        System.out.println();
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.selectionSort(array);
        Arrays.stream(array).forEach(o -> System.out.print(o + " "));
        System.out.println();
    }
}
