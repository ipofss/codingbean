package sorts;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author: wangbingshuai
 * @create: 2020-07-30 17:30
 **/
public class QuickSort {
    public void quickSort(int[] array) {
        this.quickSortInternally(array, 0, array.length - 1);
    }

    private void quickSortInternally(int[] array, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = this.partition(array, p, r);
        this.quickSortInternally(array, p, q - 1);
        this.quickSortInternally(array, q + 1, r);
    }

    private int partition(int[] array, int p, int r) {
        int value = array[r];
        while (p < r) {
            while (p < r && array[p] <= value) {
                p++;
            }
            if (p < r) {
                int tmp = array[p];
                array[p] = array[r];
                array[r] = tmp;
                r--;
            }
            while (p < r && array[r] >= value) {
                r--;
            }
            if (p < r) {
                int tmp = array[p];
                array[p] = array[r];
                array[r] = tmp;
                p++;
            }
        }
        return p;
    }

    public static void main(String[] args) {
        int[] array = {49, 38, 65, 97, 76, 13, 27, 49};
        Arrays.stream(array).forEach(o -> System.out.print(o + " "));
        System.out.println();
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(array);
        Arrays.stream(array).forEach(o -> System.out.print(o + " "));
        System.out.println();
    }
}
