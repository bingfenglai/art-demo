package pers.lbf.spring.cloud.alibaba.demo.service.publisher.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 服务发布者
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/20 9:04
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "pers.lbf.spring.cloud.alibaba.demo.service.publisher.dao")
@ComponentScan(basePackages = "pers.lbf.spring.cloud.alibaba.demo.service.publisher")
public class PublisherApp {
    public static void main(String[] args) {
        SpringApplication.run(PublisherApp.class, args);
    }
}
