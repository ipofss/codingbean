package queue;

import java.util.Scanner;

/**
 * 用数组实现循环队列
 *
 * @author: wangbingshuai
 * @create: 2020-07-27 15:37
 **/
public class CircularQueue {
    /**
     * 数组
     */
    private String[] array;
    /**
     * 数组大小
     */
    private int n;
    /**
     * 队头下标
     */
    private int head = 0;
    /**
     * 队尾下标
     */
    private int tail = 0;

    /**
     * 申请一个大小为 capacity 的循环队列
     *
     * @param capacity
     */
    public CircularQueue(int capacity) {
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
        if ((this.tail + 1) % n == this.head) {
            return false;
        }
        this.array[tail] = item;
        this.tail = (this.tail + 1) % n;
        return true;
    }

    /**
     * 出队
     *
     * @return
     */
    public String dequeue() {
        if (this.head == this.tail) {
            return null;
        }
        String data = this.array[this.head];
        this.head = (this.head + 1) % n;
        return data;
    }

    public void printAll() {
        for (int i = this.head; i % this.n != this.tail; i = (i + 1) % this.n) {
            if ((i + 1) % this.n == tail) {
                System.out.println(this.array[i]);
            } else {
                System.out.print(this.array[i] + " -> ");
            }
        }
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(5);
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
