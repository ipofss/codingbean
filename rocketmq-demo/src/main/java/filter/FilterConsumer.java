package filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 过滤消息样例消费者
 * 需要设置Broker支持sql https://blog.csdn.net/u010690828/article/details/84337688
 */
public class FilterConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("wbs_filter_mq_demo");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.subscribe("TopicTest", MessageSelector.bySql("a between 0 and 3 and b = 'abc'"));
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (int i = 0; i < msgs.size(); i++) {
                    MessageExt msg = msgs.get(i);
                    System.out.println("consumeThread=" + Thread.currentThread().getName() + ", queueId=" + msg.getQueueId() + ", content:" + new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }
}
