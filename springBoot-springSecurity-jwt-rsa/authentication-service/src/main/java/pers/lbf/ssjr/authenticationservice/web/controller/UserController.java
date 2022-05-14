package pers.lbf.ssjr.authenticationservice.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/9/3 16:28
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/")
    @PreAuthorize("hasAuthority('user:info')")
    public String getUser() {
        return "success";
    }
}
