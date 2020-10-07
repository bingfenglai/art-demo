package pers.lbf.springbootshiro.web.handler;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/7 18:01
 */
@RestControllerAdvice(basePackages = "pers.lbf.springbootshiro")
public class AuthcExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public String authcHandler(AuthenticationException e) {
    System.out.println("走这了");
        return e.getLocalizedMessage();
    }
    @ExceptionHandler(AuthorizationException.class)
    public String authzHandler(AuthorizationException e){
        return e.getLocalizedMessage();
    }
}
