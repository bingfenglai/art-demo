package pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.client.web.handler;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.client.pojo.vo.ResultVO;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/30 21:41
 */
//@RestControllerAdvice(basePackages = "pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.client.web.controller")
public class SentinelExceptionHandler {

    /**
     * 限流异常处理
     * @return
     */
    @ExceptionHandler(value = HttpClientErrorException.TooManyRequests.class)
    public ResultVO blockExceptionHandlerMethod() {
        ResultVO<Object> resultVO = new ResultVO<>("333", "请求过于频繁，请稍后重试");
        return resultVO;
    }

    /**
     * 降级异常处理
     * @return
     */
    @ExceptionHandler(value = DegradeException.class)
    public ResultVO fallbackExceptionHandlerMethod() {
        ResultVO<Object> resultVO = new ResultVO<>("444", "服务升级中，请稍后重试");
        return resultVO;
    }
}
