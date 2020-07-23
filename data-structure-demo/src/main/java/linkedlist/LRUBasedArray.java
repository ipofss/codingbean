package linkedlist;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 基于数组实现的LRU缓存
 * 1. 时间复杂度 O(n)
 * 2. 空间复杂度 O(n)
 * 3. 不支持null的缓存
 *
 * @author: wangbingshuai
 * @create: 2020-07-23 16:57
 **/
public class LRUBasedArray<T> {
    /**
     * 默认的缓存容量
     */
    private static final int DEFAULT_CAPACITY = (1 << 3);
    /**
     * 缓存容量
     */
    private int capacity;
    /**
     * 元素数量
     */
    private int count;
    /**
     * 元素数组
     */
    private T[] value;
    /**
     * 快速查缓存位置
     */
    private Map<T, Integer> holder;

    public LRUBasedArray() {
        this(DEFAULT_CAPACITY);
    }

    public LRUBasedArray(int capacity) {
        this.capacity = capacity;
        this.value = (T[]) new Object[capacity];
        this.count = 0;
        this.holder = new HashMap<>();
    }

    /**
     * 模拟访问某个值
     *
     * @param object
     */
    public void offer(T object) {
        if (object == null) {
            throw new IllegalArgumentException("该缓存容器不支持null");
        }
        Integer index = this.holder.get(object);
        if (index == null) {
            if (isFull()) {
                removeAndCache(object);
            } else {
                cache(object, this.count);
            }
        } else {
            update(index);
        }
        printAll();
    }

    /**
     * 缓存满，移除最后的元素，在数组头部插入新元素
     *
     * @param object
     */
    private void removeAndCache(T object) {
        T t = this.value[--this.count];
        this.holder.remove(t);
        cache(object, this.count);
    }

    /**
     * 新元素插入头部，其余元素全部右移一位
     *
     * @param object
     * @param count
     */
    private void cache(T object, int count) {
        rightShift(count);
        this.value[0] = object;
        this.holder.put(object, 0);
        this.count++;
    }

    /**
     * 缓存已满
     *
     * @return
     */
    private boolean isFull() {
        return this.count == this.capacity;
    }

    /**
     * 缓存为空
     *
     * @return
     */
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * 缓存中包含元素
     *
     * @param object
     * @return
     */
    public boolean isContain(T object) {
        return this.holder.containsKey(object);
    }

    /**
     * 若缓存中有指定的值，则更新位置
     *
     * @param index
     */
    private void update(Integer index) {
        T t = this.value[index];
        rightShift(index);
        this.value[0] = t;
        this.holder.put(t, 0);
    }

    /**
     * index左边的数据统一右移一位
     *
     * @param index
     */
    private void rightShift(Integer index) {
        for (int i = index - 1; i >= 0; i--) {
            this.value[i + 1] = this.value[i];
            this.holder.put(this.value[i], i + 1);
        }
    }

    /**
     * 打印所有元素
     */
    private void printAll() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.count; i++) {
            builder.append(this.value[i]);
            if (i != this.count - 1) {
                builder.append(" -> ");
            }
        }
        System.out.println(builder.toString());
    }

    public static void main(String[] args) {
        LRUBasedArray<Object> lruArray = new LRUBasedArray<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int num = scanner.nextInt();
            lruArray.offer(num);
        }
    }
}
