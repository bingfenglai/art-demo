package pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**测试接口
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/26 10:37
 */
@RestController
@RequestMapping("/test")
public class DemoController {

    @GetMapping("/info")
    public String info() {
        return "Wxp: Java开发实践";
    }
}
