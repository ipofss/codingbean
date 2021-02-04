package batchexample;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 自定义分隔消息的批量消息生产者
 */
public class SplitBatchProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("wbs_batch_mq_demo");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        List<Message> messages = new ArrayList<>();
        String topic = "TopicTest";
        Random random = new Random();
        final int SIZE_LIMIT = 1024 * 1024;
        String str = "我";
        for (int i = 0; i < 100; i++) {
            StringBuilder sb = new StringBuilder();
            int size = random.nextInt(SIZE_LIMIT);
            for (int j = 0; j < size; j++) {
                sb.append(str);
            }
            messages.add(new Message(topic, "TagA", "Hello Wbs " + i, sb.toString().getBytes()));
        }
        ListSplitter splitter = new ListSplitter(messages);
        int times = 1;
        while (splitter.hasNext()) {
            List<Message> messageList = splitter.next();
            producer.send(messageList);
            System.out.println("发送了 " + times + " 次");
            times++;
        }
        producer.shutdown();
    }
}
