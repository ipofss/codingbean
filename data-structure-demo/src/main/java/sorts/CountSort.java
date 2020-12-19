package sorts;

import java.util.Random;

/**
 * 计数排序
 *
 * @author wangbingshuai
 */
public class CountSort {
    public int[] countSort(int[] arr) {
        int min = arr[0], max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        int k = max - min + 1;
        int[] C = new int[k];
        for (int i = 0; i < arr.length; i++) {
            C[arr[i] - min]++;
        }
        for (int i = 1; i < k; i++) {
            C[i] = C[i] + C[i - 1];
        }
        int[] r = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int index = C[arr[i] - min] - 1;
            r[index] = arr[i];
            C[arr[i] - min]--;
        }
        return r;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] array = random.ints(0, 10).limit(10).toArray();
        int[] r = new CountSort().countSort(array);
        System.out.println();
    }
}
