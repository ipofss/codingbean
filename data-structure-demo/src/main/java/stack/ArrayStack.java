package stack;

import java.util.Scanner;

/**
 * 基于数组实现栈
 *
 * @author: wangbingshuai
 * @create: 2020-07-27 16:13
 **/
public class ArrayStack {
    /**
     * 数组
     */
    private String[] array;
    /**
     * 数组大小
     */
    private int n;
    /**
     * 数组中元素个数
     */
    private int count = 0;

    /**
     * 申请一个大小为 capacity 的栈
     *
     * @param capacity
     */
    public ArrayStack(int capacity) {
        this.array = new String[capacity];
        this.n = capacity;
    }

    /**
     * 入栈
     *
     * @param item
     * @return
     */
    public boolean push(String item) {
        if (this.count == this.n) {
            return false;
        }
        this.array[count++] = item;
        return true;
    }

    /**
     * 出栈
     *
     * @return
     */
    public String pop() {
        if (this.count == 0) {
            return null;
        }
        return this.array[--this.count];
    }

    /**
     * 查看栈顶元素
     *
     * @return
     */
    public String peek() {
        if (this.count == 0) {
            return null;
        }
        return this.array[this.count - 1];
    }

    public void printAll() {
        for (int i = 0; i < this.count; i++) {
            if (i == this.count - 1) {
                System.out.println(this.array[i]);
            } else {
                System.out.print(this.array[i] + " -> ");
            }
        }
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("入栈：push，出栈：pop，查看栈顶元素：peek，退出：exit");
            String line = scanner.next();
            if ("push".equals(line)) {
                String next = scanner.next();
                stack.push(next);
                stack.printAll();
            } else if ("pop".equals(line)) {
                String data = stack.pop();
                stack.printAll();
                System.out.println("出栈元素：" + data);
            } else if ("peek".equals(line)) {
                String data = stack.peek();
                stack.printAll();
                System.out.println("查看栈顶元素：" + data);
            } else if ("exit".equals(line)) {
                break;
            }
        }
    }
}
