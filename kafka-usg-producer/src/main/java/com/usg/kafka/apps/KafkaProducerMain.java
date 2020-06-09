package com.usg.kafka.apps;

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
     * ��������
     */
    public final static String TOPIC = "orderinfo";

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
        parmas.put("bootstrap.servers", "192.168.64.6:9092");
        parmas.put("acks", "all");
        parmas.put("retries", 0);
        parmas.put("batch.size", 16384);
        parmas.put("linger.ms", 1);
        parmas.put("buffer.memory", 33554432);
        parmas.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        parmas.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<String, String>(parmas);
        return producer;
    }

    void produce() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final String key = "key" + i;
            String msg = "we send message to kafka server:" + key;
            kafkaProdcer.send(new ProducerRecord<String, String>(TOPIC, key, msg), new Callback() {
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
