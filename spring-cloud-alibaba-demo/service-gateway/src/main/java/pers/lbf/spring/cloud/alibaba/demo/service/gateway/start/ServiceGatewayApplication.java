package pers.lbf.spring.cloud.alibaba.demo.service.gateway.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * 微服务网关、动态扩容、负载均衡、灰度发布 @Description //TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/11/23 16:34
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableWebFlux
public class ServiceGatewayApplication {

  public static void main(String[] args) {

    SpringApplication.run(ServiceGatewayApplication.class, args);
  }
}
