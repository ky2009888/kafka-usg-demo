package com.usg.kafka.apps;

import com.usg.kafka.common.apps.ConstantUtils;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * 功能描述:
 * 项目名称:kafka-usg-demo
 * 创建日期:2020/6/9
 *
 * @author Lenovo
 */
public class KafkaProducerMain {
    /**
     * 注意:java 客户端无法直接连接服务器上面的kafka
     * 解决方法:
     * 将kafka/config/server.properties文件中
     * 默认值
     * #listeners=PLAINTEXT://:9092
     * 修改为:
     * listeners=PLAINTEXT://192.168.64.7:9092
     * 说明:
     * 192.168.64.7 kafka服务器的地址
     */
    /**
     * 定义消息生产者
     */
    private final Producer<String, String> kafkaProdcer;

    /**
     * 定义构造方法
     */
    private KafkaProducerMain() {
        kafkaProdcer = createKafkaProducer();
    }

    /**
     * 创建生产者
     *
     * @return
     */
    private Producer<String, String> createKafkaProducer() {
        Properties parmas = new Properties();
        parmas.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ConstantUtils.SERVER_IP);
        parmas.put(ProducerConfig.ACKS_CONFIG, "all");
        parmas.put(ProducerConfig.RETRIES_CONFIG, 1);
        parmas.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        parmas.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        parmas.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        parmas.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        parmas.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<String, String>(parmas);
        return producer;
    }

    /**
     * Produce.
     */
    void produce() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final String key = "key" + i;
            String msg = "we send message to kafka server:" + key;
            kafkaProdcer.send(new ProducerRecord<String, String>(ConstantUtils.TOPIC, key, msg), new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println("发送消息" + key + "成功");
                }
            });
        }
        kafkaProdcer.close();
    }

    /**
     * 入口方法执行
     *
     * @param args 入口参数
     */
    public static void main(String[] args) {
        KafkaProducerMain main = new KafkaProducerMain();
        main.produce();
    }
}
