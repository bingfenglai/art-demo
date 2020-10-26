package pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**sentinel演示模块启动类
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/26 10:10
 */
@SpringBootApplication(scanBasePackages = "pers.lbf.spring.cloud.alibaba.demo.sentinel.demo")
@EnableDiscoveryClient
public class SentinelDemoApp {

  public static void main(String[] args) {
    SpringApplication.run(SentinelDemoApp.class,args);
  }
}
