package pers.lbf.spring.cloud.alibaba.demo.web.user.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0 @Description TODO
 * @date 2020/11/23 23:35
 */
@SpringBootApplication(scanBasePackages = "pers.lbf.spring.cloud.alibaba.demo.web.user.controller")
@EnableDiscoveryClient
public class UserWebApp {
    public static void main(String[] args) {
        SpringApplication.run(UserWebApp.class, args);
    }
}
