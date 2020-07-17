package linkedlist;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

/**
 * 基于单链表的LRU算法
 *
 * @author: wangbingshuai
 * @create: 2020-07-17 19:09
 **/
public class LRUBaseLinkedList<T> {

    /**
     * 默认链表容量
     */
    private final static Integer DEFAULT_CAPACITY = 10;

    /**
     * 头结点
     */
    private SNode<T> headNode;

    /**
     * 链表长度
     */
    private Integer length;

    /**
     * 链表容量
     */
    private Integer capacity;

    public LRUBaseLinkedList() {
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    public LRUBaseLinkedList(Integer capacity) {
        this.headNode = new SNode<>();
        this.capacity = capacity;
        this.length = 0;
    }

    public void add(T data) {
        SNode preNode = findPreNode(data);
        // 链表中存在，删除原数据，再插入到链表的头部
        if (preNode != null) {
            deleteNextNode(preNode);
            insertNodeAtBegin(data);
        } else {
            if (length >= this.capacity) {
                // 删除尾结点
                deleteNodeAtEnd();
            }
            insertNodeAtBegin(data);
        }
    }

    /**
     * 删除preNode结点的下一个结点
     *
     * @param preNode
     */
    private void deleteNextNode(SNode preNode) {
        SNode temp = preNode.getNext();
        preNode.setNext(temp.getNext());
        temp = null;
        length--;
    }

    /**
     * 链表头部插入结点
     *
     * @param data
     */
    private void insertNodeAtBegin(T data) {
        SNode<T> node = new SNode<>(data);
        node.setNext(headNode.getNext());
        headNode.setNext(node);
        length++;
    }

    /**
     * 删除尾结点
     */
    private void deleteNodeAtEnd() {
        SNode node = headNode;
        // 空链表直接返回
        if (node.getNext() == null) {
            return;
        }
        // 获取倒数第二个结点
        while (node.getNext().getNext() != null) {
            node = node.getNext();
        }
        SNode temp = node.getNext();
        node.setNext(null);
        temp = null;
        length--;
    }

    /**
     * 获取查找到元素的前一个结点
     *
     * @param data
     * @return
     */
    private SNode findPreNode(T data) {
        SNode node = headNode;
        while (node.getNext() != null) {
            if (data.equals(node.getNext().getData())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    public void printAll() {
        SNode node = headNode.getNext();
        while (node != null) {
            if (node.getNext() != null) {
                System.out.print(node.getData() + " -> ");
            } else {
                System.out.print(node.getData());
            }
            node = node.getNext();
        }
        System.out.println();
    }

    @Getter
    @Setter
    public class SNode<T> {

        private T data;

        private SNode next;

        public SNode() {
            this.next = null;
        }

        public SNode(T data) {
            this.data = data;
        }

        public SNode(T data, SNode next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUBaseLinkedList<Integer> list = new LRUBaseLinkedList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            list.add(sc.nextInt());
            list.printAll();
        }
    }
}
