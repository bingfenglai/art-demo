package pers.lbf.springbootshiro.web.authc.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.lbf.springbootshiro.server.authc.service.AuthcService;
import pers.lbf.springbootshiro.web.authc.pojo.vo.LoginVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

/**认证模块
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
    public HashMap<Object, Object> login(@RequestBody LoginVO loginVO) throws AuthenticationException {
        boolean flags = authcService.login(loginVO);
        HashMap<Object, Object> map = new HashMap<>(3);
        if (flags){
            Serializable id = SecurityUtils.getSubject().getSession().getId();
            map.put("msg","登录成功");
            map.put("token",id);
            return map;
        }else {
            return null;
        }
    }



    @RequestMapping("/unauthc")
    public String unauthc() {
       return "请先登录";
    }

}
