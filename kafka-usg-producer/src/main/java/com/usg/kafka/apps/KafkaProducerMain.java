package com.usg.kafka.apps;

import com.usg.kafka.common.apps.ConstantUtils;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * ��������:
 * ��Ŀ����:kafka-usg-demo
 * ��������:2020/6/9
 *
 * @author Lenovo
 */
public class KafkaProducerMain {
    /**
     * ע��:java �ͻ����޷�ֱ�����ӷ����������kafka
     * �������:
     * ��kafka/config/server.properties�ļ���
     * Ĭ��ֵ
     * #listeners=PLAINTEXT://:9092
     * �޸�Ϊ:
     * listeners=PLAINTEXT://192.168.64.7:9092
     * ˵��:
     * 192.168.64.7 kafka�������ĵ�ַ
     */
    /**
     * ������Ϣ������
     */
    private final Producer<String, String> kafkaProdcer;

    /**
     * ���幹�췽��
     */
    private KafkaProducerMain() {
        kafkaProdcer = createKafkaProducer();
    }

    /**
     * ����������
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
                    System.out.println("������Ϣ" + key + "�ɹ�");
                }
            });
        }
        kafkaProdcer.close();
    }

    /**
     * ��ڷ���ִ��
     *
     * @param args ��ڲ���
     */
    public static void main(String[] args) {
        KafkaProducerMain main = new KafkaProducerMain();
        main.produce();
    }
}
