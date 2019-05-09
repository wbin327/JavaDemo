package com.rocketmq.demo;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * rocketmq,生产者同步发送消息
 * 1.创建DefaultMQProducer
 * 2.设置Namesrv地址
 * 3.开启DefaultMQProducer
 * 4.创建消息Message
 * 5.发送消息
 * 6.关闭DefaultMQProducer
 */
public class SyncProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        // 创建defaultMQProducer
        DefaultMQProducer producer = new DefaultMQProducer("producer_group");
        producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");
        // 设置nameServ地址
        producer.setNamesrvAddr("192.168.195.129:9876");
        // 开启DefaultMQProducer
        producer.start();
        /** 创建消息Message
         * Topic: 主题
         * Tags:  标签，用于消息过滤，例如获取特定标签的消息
         * keys:  消息的唯一值
         * body:  消息内容
         */
        Message message = new Message("TopicDemo", "Tags", "key_1", "hello".getBytes(RemotingHelper.DEFAULT_CHARSET));
        // 发送消息
        SendResult result = producer.send(message);
        System.out.println(result);
        // 关闭Producer
        producer.shutdown();
    }
}
