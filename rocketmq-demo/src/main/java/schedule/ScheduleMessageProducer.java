package schedule;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 发送延时消息
 */
public class ScheduleMessageProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        // 实例化一个生产者来产生延时消息
        DefaultMQProducer producer = new DefaultMQProducer("wbs_schedule_mq_demo");
        producer.setNamesrvAddr("localhost:9876");
        // 启动生产者
        producer.start();
        int totalMessagesToSend = 100;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        for (int i = 0; i < totalMessagesToSend; i++) {
            Message message = new Message("TopicTest", ("Hello scheduled message " + i + ", currentTimeLong=" + System.currentTimeMillis() + ", currentTime=" + sdf.format(new Date())).getBytes());
            // 设置延时等级3，这个消息将在10s之后发送（现在只支持固定的几个时间，详情看delayTimeLevel）
            message.setDelayTimeLevel(3);
            // 发送消息
            producer.send(message);
        }
        // 关闭生产者
        producer.shutdown();
    }
}
