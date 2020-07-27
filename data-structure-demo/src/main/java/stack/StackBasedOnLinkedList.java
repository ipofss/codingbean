package stack;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Scanner;

/**
 * 基于链表实现的栈
 *
 * @author: wangbingshuai
 * @create: 2020-07-27 15:55
 **/
public class StackBasedOnLinkedList {
    /**
     * 栈顶结点
     */
    private Node top;

    /**
     * 入栈
     *
     * @param item
     * @return
     */
    public boolean push(String item) {
        Node node = new Node(item, null);
        if (this.top == null) {
            this.top = node;
        } else {
            node.setNext(this.top);
            this.top = node;
        }
        return true;
    }

    /**
     * 出栈
     *
     * @return
     */
    public String pop() {
        if (this.top == null) {
            return null;
        }
        String data = this.top.getData();
        this.top = this.top.getNext();
        return data;
    }

    /**
     * 查看栈顶元素
     *
     * @return
     */
    public String peek() {
        if (this.top == null) {
            return null;
        }
        return this.top.getData();
    }

    public void printAll() {
        Node node = this.top;
        while (node != null) {
            if (node.getNext() != null) {
                System.out.print(node.getData() + " -> ");
            } else {
                System.out.println(node.getData());
            }
            node = node.getNext();
        }
    }

    public static void main(String[] args) {
        StackBasedOnLinkedList stack = new StackBasedOnLinkedList();
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

    @Data
    @AllArgsConstructor
    private static class Node {
        private String data;
        private Node next;
    }
}
