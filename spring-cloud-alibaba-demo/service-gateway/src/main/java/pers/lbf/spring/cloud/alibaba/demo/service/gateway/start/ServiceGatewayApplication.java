package pers.lbf.spring.cloud.alibaba.demo.service.gateway.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 微服务网关、动态扩容、负载均衡、灰度发布 @Description //TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/11/23 16:34
 */
@SpringBootApplication
public class ServiceGatewayApplication {

  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(ServiceGatewayApplication.class);

    SpringApplication.run(ServiceGatewayApplication.class, args);
    logger.info("服务网关启动成功");
  }
}
