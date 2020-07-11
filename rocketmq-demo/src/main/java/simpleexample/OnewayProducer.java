package simpleexample;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * 单向传递消息
 *
 * @author: wangbingshuai
 * @create: 2020-07-11 19:21
 **/
public class OnewayProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException {
        // Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("wbs_mq_demo");
        // Specify name server addresses.
        producer.setNamesrvAddr("localhost:9876");
        // Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            Message msg = new Message("TopicTest",
                    "TagA",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // Call send message to deliver message to one of brokers.
            producer.sendOneway(msg);
        }
        Thread.sleep(5000);
        producer.shutdown();
    }
}
