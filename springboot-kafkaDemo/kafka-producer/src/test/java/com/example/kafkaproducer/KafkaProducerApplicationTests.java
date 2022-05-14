package com.example.kafkaproducer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.Resource;

@SpringBootTest
class KafkaProducerApplicationTests {

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;
    
    @Test
    void contextLoads() {
    }
    
    @Test
    public void testSendMsg() {

        kafkaTemplate.send("orderCreate", String.valueOf(System.currentTimeMillis()))
                .completable();


    }

}
