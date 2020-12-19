package sorts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * 桶排序
 *
 * @author wangbingshuai
 */
public class BucketSort {
    public void bucketSort(int[] arr) {
        int min = arr[0], max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        // 设置桶的数量
        int bucketNum = max / 10 - min / 10 + 1;
        List<List<Integer>> bucketList = new ArrayList<>();
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new ArrayList<>());
        }
        for (int i = 0; i < arr.length; i++) {
            int index = (arr[i] - min) / 10;
            bucketList.get(index).add(arr[i]);
        }
        int k = 0;
        for (int i = 0; i < bucketNum; i++) {
            Collections.sort(bucketList.get(i));
            for (int j = 0; j < bucketList.get(i).size(); j++) {
                arr[k++] = bucketList.get(i).get(j);
            }
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        IntStream intStream = random.ints(0, 1001);
        int[] arr = intStream.limit(1000).toArray();
        new BucketSort().bucketSort(arr);
        System.out.println();
    }
}
