package batchexample;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单批量发送
 */
public class SimpleBatchProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("wbs_batch_mq_demo");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        String topic = "TopicTest";
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            messages.add(new Message(topic, "TagA", "OrderID00" + i, ("Hello Java " + i).getBytes()));
        }
        producer.send(messages);
        producer.shutdown();
    }
}
