package filter;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * 过滤消息样例生产者
 * 消费者将接收包含TAGA或TAGB或TAGC的消息。但是限制是一个消息只能有一个标签，这对于复杂的场景可能不起作用。在这种情况下，可以使用SQL表达式筛选消息。SQL特性可以通过发送消息时的属性来进行计算。在RocketMQ定义的语法下，可以实现一些简单的逻辑。下面是一个例子：
 * <p>
 * ------------
 * | message  |
 * |----------|  a > 5 AND b = 'abc'
 * | a = 10   |  --------------------> Gotten
 * | b = 'abc'|
 * | c = true |
 * ------------
 * ------------
 * | message  |
 * |----------|   a > 5 AND b = 'abc'
 * | a = 1    |  --------------------> Missed
 * | b = 'abc'|
 * | c = true |
 * ------------
 * <p>
 * RocketMQ只定义了一些基本语法来支持这个特性。你也可以很容易地扩展它。
 * <p>
 * 数值比较，比如：>，>=，<，<=，BETWEEN，=；
 * 字符比较，比如：=，<>，IN；
 * IS NULL 或者 IS NOT NULL；
 * 逻辑符号 AND，OR，NOT；
 * 常量支持类型为：
 * <p>
 * 数值，比如：123，3.1415；
 * 字符，比如：'abc'，必须用单引号包裹起来；
 * NULL，特殊的常量
 * 布尔值，TRUE 或 FALSE
 * 只有使用push模式的消费者才能用使用SQL92标准的sql语句，接口如下：
 * <p>
 * public void subscribe(finalString topic, final MessageSelector messageSelector)
 */
public class FilterProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("wbs_filter_mq_demo");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        Message message = new Message("TopicTest", "TagA", "Hello Haha 0 abc".getBytes(StandardCharsets.UTF_8));
        // 设置一些属性
        message.putUserProperty("a", "0");
        message.putUserProperty("b", "abc");
        SendResult send = producer.send(message);
        producer.shutdown();
    }
}
