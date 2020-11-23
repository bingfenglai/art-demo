package pers.lbf.spring.cloud.alibaba.demo.service.dubbo.user.start;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0 @Description TODO
 * @date 2020/11/23 22:59
 */
@SpringBootApplication(scanBasePackages = "pers.lbf.spring.cloud.alibaba.demo")
@EnableDubbo(scanBasePackages = "pers.lbf.spring.cloud.alibaba.demo.service.dubbo.user.service")
public class UserServiceApp {

  public static void main(String[] args) {
    SpringApplication.run(UserServiceApp.class, args);
    Logger logger = LoggerFactory.getLogger(UserServiceApp.class);
    logger.info("用户服务启动成功");
  }
}
