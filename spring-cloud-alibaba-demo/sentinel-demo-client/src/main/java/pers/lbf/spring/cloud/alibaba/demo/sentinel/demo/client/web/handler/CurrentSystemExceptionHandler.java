package pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.client.web.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.client.pojo.vo.ResultVO;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/11/1 9:30
 */
@RestControllerAdvice(basePackages = "pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.client.web.controller")
public class CurrentSystemExceptionHandler {

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResultVO internalServerErrorHandlerMethod(HttpServerErrorException.InternalServerError err) {
        return new ResultVO<>("500", err.getMessage());
    }
}
