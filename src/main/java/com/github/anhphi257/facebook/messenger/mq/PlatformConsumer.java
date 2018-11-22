package com.github.anhphi257.facebook.messenger.mq;

import com.github.anhphi257.facebook.messenger.Constant;
import com.github.anhphi257.facebook.messenger.MessageProcessor;
import com.github.anhphi257.facebook.messenger.models.FacebookIncomeMessage;
import com.github.anhphi257.facebook.messenger.models.FacebookOutcomeMessage;
import com.github.anhphi257.facebook.messenger.utils.GsonUtils;
import com.github.anhphi257.facebook.messenger.utils.HttpRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by phiha on 01/10/2018.
 */
public class PlatformConsumer implements Runnable {
    private static Properties props = new Properties();
    private KafkaConsumer<String, String> consumer;
    private MessageProcessor processor;

    static {
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constant.MessageQueue.KAFKA_BROKERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, Constant.MessageQueue.CONSUMER.GROUP_ID);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
    }


    private String topic;

    public PlatformConsumer(String topic) {
        this.topic = topic;
        init();
    }

    private void init() {
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
        ConsumerRecords<String, String> records = consumer.poll(100);
        for (ConsumerRecord<String, String> record : records) {
            long offset = record.offset();
            String json = record.value();
            FacebookIncomeMessage incomeMessage = GsonUtils.parse(json, FacebookIncomeMessage.class);
            FacebookOutcomeMessage outcomeMessage = processor.process(incomeMessage);
            try {
                HttpRequest.sendPost(Constant.Facebook.CALLBACK_URL, GsonUtils.toJson(outcomeMessage), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
