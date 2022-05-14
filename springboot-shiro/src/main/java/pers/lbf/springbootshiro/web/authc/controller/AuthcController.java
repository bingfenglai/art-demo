package pers.lbf.springbootshiro.web.authc.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.lbf.springbootshiro.server.authc.service.AuthcService;
import pers.lbf.springbootshiro.web.authc.pojo.vo.LoginVO;

/**
 * 认证模块
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/6 10:07
 */
@RestController
@RequestMapping("/authc")
public class AuthcController {

    @Autowired
    private AuthcService authcService;

    @PostMapping("/login")
    public boolean login(@RequestBody LoginVO loginVO) throws AuthenticationException {

        return authcService.login(loginVO);
    }


    @GetMapping("/unauthc")
    public String unauthc() {
        return "请先登录";
    }

}
