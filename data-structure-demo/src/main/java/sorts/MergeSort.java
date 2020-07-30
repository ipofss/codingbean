package sorts;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author: wangbingshuai
 * @create: 2020-07-30 16:47
 **/
public class MergeSort {
    public void mergeSort(int[] array) {
        this.mergeSortInternally(array, 0, array.length - 1);
    }

    private void mergeSortInternally(int[] array, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = p + (r - p) / 2;
        this.mergeSortInternally(array, p, q);
        this.mergeSortInternally(array, q + 1, r);
        this.merge(array, p, q, r);
    }

    private void merge(int[] array, int p, int q, int r) {
        int i = p;
        int j = q + 1;
        int k = 0;
        int[] tmp = new int[r - p + 1];
        while (i <= q && j <= r) {
            if (array[i] <= array[j]) {
                tmp[k++] = array[i++];
            } else {
                tmp[k++] = array[j++];
            }
        }
        while (i <= q) {
            tmp[k++] = array[i++];
        }
        while (j <= r) {
            tmp[k++] = array[j++];
        }
        for (int l = 0; l <= r - p; l++) {
            array[p + l] = tmp[l];
        }
    }

    public static void main(String[] args) {
        int[] array = {2, 3, 5, 8, 6, 1, 5, 6, 7, 9};
        Arrays.stream(array).forEach(o -> System.out.print(o + " "));
        System.out.println();
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(array);
        Arrays.stream(array).forEach(o -> System.out.print(o + " "));
        System.out.println();
    }
}
