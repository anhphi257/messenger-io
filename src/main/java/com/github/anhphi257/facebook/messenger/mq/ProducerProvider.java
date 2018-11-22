package com.github.anhphi257.facebook.messenger.mq;

import com.github.anhphi257.facebook.messenger.Constant;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * Created by phiha on 22/11/2018.
 */
public class ProducerProvider {
    private static Properties props;
    static MessageProducer messageProducer;

    static {
        props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constant.MessageQueue.KAFKA_BROKERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, Constant.MessageQueue.CLIENT_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    }

    public static MessageProducer get() {
        if (messageProducer == null) {
            Producer<String, String> kafkaProducer = new KafkaProducer<>(props);
            messageProducer = new MessageProducer(kafkaProducer);
        }
        return messageProducer;
    }
}
