package linkedlist;

import lombok.Data;

/**
 * 单链表节点
 *
 * @author: wangbingshuai
 * @create: 2020-07-17 15:02
 **/
@Data
public class LinkNode {
    /**
     * 存放数据
     */
    private Integer data;
    /**
     * 下一节点地址
     */
    private LinkNode next;
}
