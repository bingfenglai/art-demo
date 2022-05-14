package com.example.kafkaconsumer.message.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author ferryman
 * @since 2022/4/22 15:38
 */
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "orderCreate",groupId = "app"+"#{T(java.util.UUID).randomUUID()}")
    public void consumer(ConsumerRecord<String, String> record, Acknowledgment ack) throws InterruptedException {
        log.info("收到消息{}",record.toString());
        TimeUnit.SECONDS.sleep(1);
        ack.acknowledge();
    }
}
