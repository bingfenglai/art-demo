package pers.lbf.spring.cloud.alibaba.demo.service.subscriber.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/20 9:34
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RestTemplate rest;

  @Value("${service-publisher.service-url}")
  private String serviceURL;

    @GetMapping("/getInfo")
    public String getInfo(){

        return rest.getForObject(serviceURL+"/test/getInfo",String.class);
    }

}
