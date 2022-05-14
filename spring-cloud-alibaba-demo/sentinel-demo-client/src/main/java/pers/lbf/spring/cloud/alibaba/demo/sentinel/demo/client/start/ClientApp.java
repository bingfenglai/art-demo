package pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.client.start;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.client.utils.SentinelUtils;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/30 20:22
 */
@SpringBootApplication(scanBasePackages = "pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.client")
@EnableDiscoveryClient
public class ClientApp {

    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class, args);
    }

    @Bean
    @SentinelRestTemplate(fallbackClass = SentinelUtils.class,
            fallback = "defaultFallbackMethod",
            blockHandlerClass = SentinelUtils.class,
            blockHandler = "handleException")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
