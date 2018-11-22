package com.github.anhphi257.facebook.messenger.mq;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by phiha on 22/11/2018.
 */
public class MessageProducer {
    private Producer<String, String> producer;

    MessageProducer(Producer<String, String> producer) {
        this.producer = producer;
    }

    public void send(String topic, String key, String message) throws ExecutionException, InterruptedException {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, message);
        Future<RecordMetadata> metadataFuture = producer.send(record);
        RecordMetadata metadata = metadataFuture.get();
        System.out.println(metadata.offset());
        System.out.println(metadata.partition());
    }
}
