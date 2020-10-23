package pers.lbf.spring.cloud.alibaba.demo.service.config.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**配置服务
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/22 9:03
 */
@SpringBootApplication
@ComponentScan("pers.lbf.spring.cloud.alibaba.demo.service.config")
public class ConfigApp {

  public static void main(String[] args) {
    SpringApplication.run(ConfigApp.class,args);
  }
}
