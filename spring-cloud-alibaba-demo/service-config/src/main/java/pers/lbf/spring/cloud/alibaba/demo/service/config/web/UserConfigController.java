package pers.lbf.spring.cloud.alibaba.demo.service.config.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/22 15:41
 */
@RequestMapping("/user")
@RefreshScope
@RestController
public class UserConfigController {

    @Value("${userCacheEnabled:false}")
    private boolean userCacheEnabled;

    @GetMapping("/getUserCacheEnabled")
    public boolean getUserCacheEnabled() {
        return this.userCacheEnabled;
    }

}
