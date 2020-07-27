package queue;

import java.util.Scanner;

/**
 * 用数组实现队列
 *
 * @author: wangbingshuai
 * @create: 2020-07-27 11:22
 **/
public class ArrayQueue {
    /**
     * 数组
     */
    private String[] array;
    /**
     * 数组大小
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
    public ArrayQueue(int capacity) {
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
        // 队列已满
        if (this.tail == this.n) {
            return false;
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
        ArrayQueue queue = new ArrayQueue(5);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("入队：en，出队：de");
            String line = scanner.next();
            if ("en".equals(line)) {
                String next = scanner.next();
                queue.enqueue(next);
                queue.printAll();
            } else if ("de".equals(line)) {
                String str = queue.dequeue();
                System.out.println("出队元素：" + str);
                queue.printAll();
            } else if ("exit".equals(line)) {
                break;
            }
        }
    }
}
