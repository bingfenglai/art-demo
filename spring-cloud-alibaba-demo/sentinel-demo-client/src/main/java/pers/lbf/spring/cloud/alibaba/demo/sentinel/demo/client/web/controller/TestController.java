package pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.client.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.client.pojo.vo.ResultVO;

import java.util.Objects;

/**
 * 测试接口，负责演示调用服务
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/30 20:52
 */
@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public ResultVO test() throws Exception {

        ResultVO resultVO = restTemplate.getForObject("http://127.0.0.1:9004/test/info", ResultVO.class);
        System.out.println(Objects.requireNonNull(resultVO).toString());
        return resultVO;
    }
}
