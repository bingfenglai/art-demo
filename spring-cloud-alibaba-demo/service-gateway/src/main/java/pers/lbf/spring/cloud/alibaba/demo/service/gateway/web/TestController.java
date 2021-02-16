package pers.lbf.spring.cloud.alibaba.demo.service.gateway.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0 @Description TODO
 * @date 2020/11/26 17:00
 */
@RestController
@RequestMapping("/333")
public class TestController {

  @Value("${test}")
  private String test;

  @GetMapping("/")
  public Mono<String> getTest() {
    return Mono.just(test);
  }
}
