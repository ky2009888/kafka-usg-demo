package com.usg.kafka.consumer.apps;

import com.usg.kafka.common.apps.ConstantUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * ��������:
 * ��Ŀ����:kafka-usg-demo
 * ��������:2020/6/9
 *
 * @author Lenovo
 */
public class KafkaConsumerMain {
    /**
     * ����kafka������
     */
    private static KafkaConsumer<String, String> consumer;


    /**
     * ��ʼ��kafka������
     */
    public KafkaConsumerMain() {
        Properties properties = new Properties();
        //ָ���������ĵ�ַ
        properties.put(ConstantUtils.BOOTSTRAP_SERVERS, ConstantUtils.SERVER_IP);
        //�����Զ��ύ
        properties.put(ConstantUtils.ENABLE_AUTO_COMMIT, "true");
        //�Զ��ύ����ʱ
        properties.put(ConstantUtils.AUTO_COMMIT_INTERVAL_MS, 1000);
        //ָ�����ID
        properties.put(ConstantUtils.GROUP_ID, "test-consumer-group");
        //ָ��session��ʱЧʱ��
        properties.put(ConstantUtils.SESSION_TIMEOUT_MS, 30000);
        //ָ��key�ķ����л�����
        properties.put(ConstantUtils.KEY_DESERIALIZER, "org.apache.kafka.common.serialization.StringDeserializer");
        //ָ��value�ķ����л�����
        properties.put(ConstantUtils.VALUE_DESERIALIZER, "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(properties);
    }

    /**
     * ������Ϣ
     */
    void getConsumers() {
        //��������
        consumer.subscribe(Arrays.asList(ConstantUtils.TOPIC));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> consumerRecords : records) {
                System.out.println("key:" + consumerRecords.key() + ", value: " + consumerRecords.value() + ", topic: " + consumerRecords.topic());
            }
        }
    }

    /**
     * ���ִ�з���
     *
     * @param args ��ڲ���
     */
    public static void main(String[] args) {
        KafkaConsumerMain main = new KafkaConsumerMain();
        main.getConsumers();
    }

}
