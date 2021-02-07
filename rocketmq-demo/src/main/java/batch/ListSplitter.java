package batch;

import org.apache.rocketmq.common.message.Message;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 自定义消息分隔
 */
public class ListSplitter implements Iterator<List<Message>> {
    // 限制4MB
    private final int SIZE_LIMIT = 1024 * 1024 * 4;
    private final List<Message> messages;
    private int currentIndex;

    public ListSplitter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < messages.size();
    }

    @Override
    public List<Message> next() {
        int startIndex = getStartIndex();
        int nextIndex = startIndex;
        int totalSize = 0;
        for (; nextIndex < messages.size(); nextIndex++) {
            Message message = messages.get(nextIndex);
            int tmpSize = calcMessageSize(message);
            if (tmpSize + totalSize > SIZE_LIMIT) {
                break;
            } else {
                totalSize += tmpSize;
            }
        }
        List<Message> subList = this.messages.subList(startIndex, nextIndex);
        currentIndex = nextIndex;
        return subList;
    }

    /**
     * 获取起始下标索引
     *
     * @return
     */
    private int getStartIndex() {
        Message currentMessage = messages.get(currentIndex);
        int tmpSize = calcMessageSize(currentMessage);
        // fixme：这里有一个bug，就是某个消息体的大小大于4MB的时候，会跳过
        while (tmpSize > SIZE_LIMIT) {
            currentIndex++;
            if (currentIndex >= messages.size()) {
                break;
            } else {
                Message message = messages.get(currentIndex);
                tmpSize += calcMessageSize(message);
            }
        }
        return currentIndex;
    }

    /**
     * 计算消息体的大小
     *
     * @param message 消息体
     * @return
     */
    private int calcMessageSize(Message message) {
        int tmpSize = message.getTopic().length() + message.getBody().length;
        Map<String, String> properties = message.getProperties();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            tmpSize += entry.getKey().length() + entry.getValue().length();
        }
        tmpSize += 20;// 增加日志的开销20字节
        return tmpSize;
    }
}
