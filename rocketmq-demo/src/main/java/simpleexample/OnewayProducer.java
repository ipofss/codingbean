package simpleexample;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * 单向发送消息
 * 这种方式主要用在不特别关系发送结果的场景，例如日志发送。
 *
 * @author: wangbingshuai
 * @create: 2020-07-11 19:21
 **/
public class OnewayProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("wbs_mq_demo");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        producer.start();
        for (int i = 0; i < 100; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("TopicTest",
                    "TagA",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 发送单向消息，没有任何返回结果
            producer.sendOneway(msg);
        }
        Thread.sleep(5000);
        // 如果不再发送消息，关闭Producer实例
        producer.shutdown();
    }
}
