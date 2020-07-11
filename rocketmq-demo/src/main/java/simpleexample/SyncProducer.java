package simpleexample;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * 同步发送消息
 *
 * @author: wangbingshuai
 * @create: 2020-07-10 20:38
 **/
public class SyncProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        // Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("wbs_mq_demo");
        // Specify name server addresses.
        producer.setNamesrvAddr("localhost:9876");
        // Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            Message message = new Message("TopicTest",// topic
                    "TagA",// tag
                    ("Hello RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET)// Message body
            );
            // Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(message);
            System.out.println(sendResult);
        }
        // Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
