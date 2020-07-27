package queue;

import java.util.Scanner;

/**
 * 用数组实现队列，队满进行数据搬移
 *
 * @author: wangbingshuai
 * @create: 2020-07-27 11:59
 **/
public class DynamicArrayQueue {
    /**
     * 数组
     */
    private String[] array;
    /**
     * 数组容量
     */
    private int n;
    /**
     * 对头下标
     */
    private int head = 0;
    /**
     * 队尾下标
     */
    private int tail = 0;

    /**
     * 申请一个大小为 capacity 的队列
     *
     * @param capacity
     */
    public DynamicArrayQueue(int capacity) {
        this.array = new String[capacity];
        this.n = capacity;
    }

    /**
     * 入队
     *
     * @param item
     * @return
     */
    public boolean enqueue(String item) {
        // 队列尾部没有空间
        if (this.tail == this.n) {
            // 整个队列已满
            if (this.head == 0) {
                return false;
            }
            // 进行数据搬移
            for (int i = this.head; i < this.tail; i++) {
                this.array[i - this.head] = this.array[i];
            }
            // 更新对头、队尾下标，要先更新队尾下标
            this.tail -= this.head;
            this.head = 0;
        }
        this.array[this.tail++] = item;
        return true;
    }

    /**
     * 出队
     *
     * @return
     */
    public String dequeue() {
        // 队列已空
        if (this.head == this.tail) {
            return null;
        }
        return this.array[this.head++];
    }

    public void printAll() {
        for (int i = this.head; i < this.tail; i++) {
            if (i == this.tail - 1) {
                System.out.print(this.array[i]);
            } else {
                System.out.print(this.array[i] + " -> ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DynamicArrayQueue dynamicArrayQueue = new DynamicArrayQueue(5);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("入队：en，出队：de");
            String line = scanner.next();
            if ("en".equals(line)) {
                String next = scanner.next();
                dynamicArrayQueue.enqueue(next);
                dynamicArrayQueue.printAll();
            } else if ("de".equals(line)) {
                String str = dynamicArrayQueue.dequeue();
                System.out.println("出队元素：" + str);
                dynamicArrayQueue.printAll();
            }
        }
    }
}
