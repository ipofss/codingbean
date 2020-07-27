package queue;

import lombok.Data;

import java.util.Scanner;

/**
 * 基于链表实现队列
 *
 * @author: wangbingshuai
 * @create: 2020-07-27 14:43
 **/
public class QueueBasedOnLinkedList {
    /**
     * 队头结点
     */
    private Node head;
    /**
     * 队尾结点
     */
    private Node tail;

    /**
     * 入队
     *
     * @param value
     * @return
     */
    public boolean enqueue(String value) {
        if (tail == null) {
            Node node = new Node(value, null);
            this.head = node;
            this.tail = node;
        } else {
            this.tail.setNext(new Node(value, null));
            this.tail = this.tail.getNext();
        }
        return true;
    }

    /**
     * 出队
     *
     * @return
     */
    public String dequeue() {
        if (this.head == null) {
            return null;
        }
        String data = this.head.getData();
        this.head = this.head.getNext();
        if (this.head == null) {
            this.tail = null;
        }
        return data;
    }

    public void printAll() {
        Node p = this.head;
        while (p != null) {
            if (p.getNext() != null) {
                System.out.print(p.getData() + " -> ");
            } else {
                System.out.println(p.getData());
            }
            p = p.getNext();
        }
    }

    @Data
    private static class Node {
        private String data;

        private Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        QueueBasedOnLinkedList queue = new QueueBasedOnLinkedList();
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
