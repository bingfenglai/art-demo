package pers.lbf.spring.cloud.alibaba.demo.service.subscriber.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/20 9:04
 */
@SpringBootApplication(scanBasePackages = "pers.lbf.spring.cloud.alibaba.demo.service.subscriber")
@EnableDiscoveryClient
public class SubscriberApp {
  public static void main(String[] args) {
    SpringApplication.run(SubscriberApp.class,args);
  }

    /**
     * 通过restTemplate进行服务调用
     * 通过@LoadBalanced注解开启负载均衡和主机地址端口映射
   * @return restTemplate
   */
  @LoadBalanced
  @Bean
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }

}
