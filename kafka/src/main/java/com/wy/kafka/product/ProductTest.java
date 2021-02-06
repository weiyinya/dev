package com.wy.kafka.product;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author Dwen
 * @date 2020-12-30 17:08
 */
public class ProductTest {

    private final KafkaProducer<String, String> producer;
    private final String topic;
    private static final String GROUPID = "groupA";
    public static String topicc = "test_topic";//定义主题

    public ProductTest(String topicName) {
        Properties props = new Properties();
        /**
         * 更多生产者信息见：
         * @see  org.apache.kafka.clients.producer.ProducerConfig
         */

        // 该配置用于发现kafka集群。只需填写部分kafka集群地址，客户端会根据填写的地址发现所有集群信息，并实时更新。
        props.put("bootstrap.servers", "localhost:9092");
        /**
         * 接收确认
         * 0 生产者不用确认是否发送成功，持续发送消息
         * 1 生产者需要server自己确认接收成功，无需follow接收成功
         * all | -1 生产者需要server以及follow均接收成功
         */
        props.put("acks", "all");
        props.put("retries", 0);
        /**
         * 批发送的最大字节数（byte）。通常与linger.ms结合使用
         */
        props.put("batch.size", 16384);
        /**
         * 延迟发送时间。
         * 如果发送recard未到达batch.size，那么将等待linger.ms时间，缓存请求，批发送。
         *
         * 默认linger.ms=0，即不进行批发送。
         */
        props.put("linger.ms", 500);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        this.producer = new KafkaProducer<String, String>(props);
        this.topic = topicName;
    }

    public void run() {
        int messageNo = 1;
        try {
            for(;;) {
                String messageStr="你好，这是第"+messageNo+"条数据";
                producer.send(new ProducerRecord<String, String>(topic, "Message", messageStr));
                //生产了100条就打印
                if(messageNo%100==0){
                    System.out.println("发送的信息:" + messageStr);
                }
                //生产1000条就退出
                if(messageNo%1000==0){
                    System.out.println("成功发送了"+messageNo+"条");
                    break;
                }
                messageNo++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProductTest test = new ProductTest("KAFKA_TEST");
        test.run();
    }
}
