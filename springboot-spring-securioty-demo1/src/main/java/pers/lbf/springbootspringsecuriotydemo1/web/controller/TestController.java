package pers.lbf.springbootspringsecuriotydemo1.web.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/8/27 20:02
 */
@RestController
@RequestMapping("/product")
public class TestController {


    @GetMapping("/")
    @PreAuthorize("hasAuthority('product:get')")
    public String get() {
        return "调用成功";
    }
}
