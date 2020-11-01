package pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.web.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.vo.ResultVO;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/31 9:20
 */
//@RestControllerAdvice(basePackages = "pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.web")
public class BusinessExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResultVO runtimeExceptionHandlerMethod(RuntimeException e) {
        return new ResultVO<>("505","运行时异常");
    }
}
