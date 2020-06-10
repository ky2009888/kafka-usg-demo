package com.usg.kafka.consumer.apps;

import com.usg.kafka.common.apps.ConstantUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * 功能描述:
 * 项目名称:kafka-usg-demo
 * 创建日期:2020/6/9
 *
 * @author Lenovo
 */
public class KafkaConsumerMain {
    /**
     * 定义kafka消费者
     */
    private static KafkaConsumer<String, String> consumer;


    /**
     * 初始化kafka消费者
     */
    public KafkaConsumerMain() {
        Properties properties = new Properties();
        //指定服务器的地址
        properties.put(ConstantUtils.BOOTSTRAP_SERVERS, ConstantUtils.SERVER_IP);
        //开启自动提交
        properties.put(ConstantUtils.ENABLE_AUTO_COMMIT, "true");
        //自动提交的延时
        properties.put(ConstantUtils.AUTO_COMMIT_INTERVAL_MS, 1000);
        //指定组的ID
        properties.put(ConstantUtils.GROUP_ID, "test-consumer-group");
        //指定session的时效时间
        properties.put(ConstantUtils.SESSION_TIMEOUT_MS, 30000);
        //指定key的反序列化的类
        properties.put(ConstantUtils.KEY_DESERIALIZER, "org.apache.kafka.common.serialization.StringDeserializer");
        //指定value的反序列化的类
        properties.put(ConstantUtils.VALUE_DESERIALIZER, "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(properties);
    }

    /**
     * 消费消息
     */
    void getConsumers() {
        //订阅主题
        consumer.subscribe(Arrays.asList(ConstantUtils.TOPIC));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> consumerRecords : records) {
                System.out.println("key:" + consumerRecords.key() + ", value: " + consumerRecords.value() + ", topic: " + consumerRecords.topic());
            }
        }
    }

    /**
     * 入口执行方法
     *
     * @param args 入口参数
     */
    public static void main(String[] args) {
        KafkaConsumerMain main = new KafkaConsumerMain();
        main.getConsumers();
    }

}
