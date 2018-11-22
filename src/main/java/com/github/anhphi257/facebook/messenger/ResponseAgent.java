package com.github.anhphi257.facebook.messenger;

import com.github.anhphi257.facebook.messenger.mq.PlatformConsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResponseAgent {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(Config.MessageQueue.NUM_PARTITIONS);
        for(int i = 0; i < Config.MessageQueue.NUM_PARTITIONS; i++) {
            threadPool.submit(new PlatformConsumer(Config.MessageQueue.TOPIC));
        }
    }
}
