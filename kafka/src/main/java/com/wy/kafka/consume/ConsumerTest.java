package com.wy.kafka.consume;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author Dwen
 * @date 2020-12-30 17:13
 */
public class ConsumerTest{
    private final KafkaConsumer<String, String> consumer;
    private ConsumerRecords<String, String> msgList;
    private final String topic;
    private static final String GROUPID = "groupA";
    public static String topicc = "test_topic";//定义主题

    public ConsumerTest(String topicName) {
        Properties props = new Properties();

        /**
         * @see org.apache.kafka.clients.consumer.ConsumerConfig
         */
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", GROUPID);
        //禁止自动提交，改为手动提交
        props.put("enable.auto.commit", "false");
        //自动提交间隔。仅在enable.auto.commit=true时生效
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        this.consumer = new KafkaConsumer<String, String>(props);
        this.topic = topicName;
        this.consumer.subscribe(Arrays.asList(topic));
    }


    public void run() {
        int messageNo = 1;
        System.out.println("---------开始消费---------");
        try {
            for (;;) {
                msgList = consumer.poll(Duration.ofSeconds(10L));
                if(null!=msgList&&msgList.count()>0) {
                    for (ConsumerRecord<String, String> record : msgList) {
                        System.out.println(messageNo+"=======receive: key = " + record.key() + ", value = " + record.value()+" offset==="+record.offset());
                        //当消费了1000条就退出
                        if(messageNo%1000==0){
                            break;
                        }
                        messageNo++;
                    }
                } else {
                    Thread.sleep(1000);
                }

                consumer.commitSync();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

    public static void main(String args[]) {
        ConsumerTest test1 = new ConsumerTest(topicc);
        test1.run();
    }
}
