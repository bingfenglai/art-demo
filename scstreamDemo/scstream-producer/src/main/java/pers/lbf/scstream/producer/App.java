package pers.lbf.scstream.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.function.Consumer;

/**
 * @author ferryman
 * @since 2022/6/24 14:45
 */
@SpringBootApplication
@RestController
@Slf4j
public class App {

    @Resource
    StreamBridge streamBridge;

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }


    @GetMapping("/{msg}")
    public Object sendMsg(@PathVariable("msg") String msg) {
        return streamBridge.send("outputkafka-out-0",msg);
    }

    @Bean
    public Consumer<Message<String>> slink(){

        return msg -> {
            log.info("收到消息: {}",msg.getPayload());
            Acknowledgment acknowledgment = msg.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
            msg.getHeaders().keySet().forEach(val ->log.info(val));
            msg.getHeaders().values().forEach(val ->log.info(val.toString()));

            if (null!=acknowledgment){
                log.info("消息确认消费");
                acknowledgment.acknowledge();
            }else {
                log.warn("acknowledgment is null");
            }
        };
    }



}
