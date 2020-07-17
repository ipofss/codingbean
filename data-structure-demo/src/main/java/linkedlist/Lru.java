package linkedlist;

import java.util.Scanner;

/**
 * 单链表实现lru缓存淘汰算法
 *
 * @author: wangbingshuai
 * @create: 2020-07-17 15:05
 **/
public class Lru {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkNode head = new LinkNode();
        head.setData(-1);
        head.setNext(null);
        int n = 5;
        int count = 0;
        while (true) {
            String line = scanner.nextLine();
            Integer data = Integer.valueOf(line);
            LinkNode linkList = head.getNext();
            boolean has = false;
            LinkNode pre = head;
            while (linkList != null) {
                if (linkList.getData().equals(data)) {
                    has = true;
                    // 移除当前结点，插到头结点
                    pre.setNext(linkList.getNext());
                    linkList.setNext(head.getNext());
                    head.setNext(linkList);
                    break;
                } else {
                    pre = linkList;
                    linkList = linkList.getNext();
                }
            }
            if (!has) {
                // 插入新结点，然后判断长度是否大于n，若大于，则删除最后一个结点
                LinkNode linkNode = new LinkNode();
                linkNode.setData(data);
                if (head.getNext() != null) {
                    linkNode.setNext(head.getNext());
                    head.setNext(linkNode);
                } else {
                    head.setNext(linkNode);
                    linkNode.setNext(null);
                }
                count++;
                if (count > n) {
                    linkList = head.getNext();
                    while (linkList != null) {
                        if (linkList.getNext().getNext() == null) {
                            linkList.setNext(null);
                        }
                        linkList = linkList.getNext();
                    }
                    count--;
                }
            }
            // 遍历输出所有结点
            linkList = head.getNext();
            int num = 1;
            while (linkList != null) {
                if (num < count) {
                    System.out.print(linkList.getData() + " -> ");
                } else {
                    System.out.print(linkList.getData());
                }
                num++;
                linkList = linkList.getNext();
            }
            System.out.println();
        }
    }
}
