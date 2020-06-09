package com.usg.kafka.common.apps;

/**
 * ��������:
 * ��Ŀ����:kafka-usg-demo
 * ��������:2020/6/9
 *
 * @author Lenovo
 */
public class ConstantUtils {
    /**
     * ����BOOTSTRAP_SERVERS����bootstrap.servers
     */
    public static final String BOOTSTRAP_SERVERS = "bootstrap.servers";
    /**
     * ����Ψһ��ʶconsumer������������ַ�����
     * �������ͬ����group  id����ʾ��Щprocesses��������ͬһ��consumer  group
     */
    public static String GROUP_ID = "group.id";

    /**
     * ���ֵΪ�棬��Ϊƫ�������Զ���ʵ�������ύ��
     */
    public static String ENABLE_AUTO_COMMIT = "enable.auto.commit";

    /**
     * consumer��zookeeper�ύoffset��Ƶ�ʣ���λ����
     */
    public static String AUTO_COMMIT_INTERVAL_MS = "auto.commit.interval.ms";

    /**
     * zookeeper �Ự�ĳ�ʱ���ơ�
     * ���consumer�����ʱ����û����zookeeper����������Ϣ�������ᱻ��Ϊ�ҵ��ˣ�����reblance�������
     */
    public static String SESSION_TIMEOUT_MS = "session.timeout.ms";

    /**
     * zookeeper��û�г�ʼ����offsetʱ�����offset������ֵ�Ļ�Ӧ��
     * smallest���Զ���λoffsetΪsmallest��offset
     * largest���Զ���λoffsetΪlargest��offset
     * anything  else����consumer�׳��쳣
     */
    public static String AUTO_OFFSET_RESET = "auto.offset.reset";

    /**
     * The constant KEY_DESERIALIZER.
     */
    public static String KEY_DESERIALIZER = "key.deserializer";

    /**
     * The constant VALUE_DESERIALIZER.
     */
    public static String VALUE_DESERIALIZER = "value.deserializer";
    /**
     * ��������
     */
    public static String TOPIC = "orderinfo";
    /**
     * ��������
     */
    public static String SERVER_IP = "192.168.64.6:9092";
}
