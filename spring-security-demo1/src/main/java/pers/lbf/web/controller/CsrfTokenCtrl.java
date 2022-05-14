package pers.lbf.web.controller;


import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/8/21 12:29
 */
@RestController
@RequestMapping("/csrf")
public class CsrfTokenCtrl {

    @GetMapping(value = "/getToken")
    public HashMap<String, String> getToken(HttpServletRequest request) {
        /**
         * 在这里可以先过滤掉一些非法的请求，只允许内部静态网页服务器请求
         */

        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        HashMap<String, String> map = new HashMap<>();
        map.put("csrf_header", token.getHeaderName());
        map.put("csrf", token.getToken());
        return map;
    }

}
