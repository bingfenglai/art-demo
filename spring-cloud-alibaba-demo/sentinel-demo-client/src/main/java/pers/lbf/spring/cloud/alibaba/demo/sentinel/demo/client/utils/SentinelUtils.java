package pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.client.utils;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

/**sentinel限流、降级处理类
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/30 20:25
 */
public class SentinelUtils {


    /**
     * 限流处理函数
     * @param request
     * @param body
     * @param execution
     * @param ex
     * @return
     */
    public static SentinelClientHttpResponse handleException(HttpRequest request,
                                                             byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
        System.err.println("Oops: " + ex.getClass().getCanonicalName());
        System.err.println("fallback: 服务被限流" );
        return new SentinelClientHttpResponse("系统正忙，请稍后重试");
    }

    /**
     * 服务降级处理函数
     * @param request
     * @param body
     * @param execution
     * @param ex
     * @return
     */
      public static SentinelClientHttpResponse defaultFallbackMethod(HttpRequest request,
                                                        byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
          System.err.println("fallback: " + ex.getClass().getCanonicalName());
          System.err.println("fallback: 服务被降级" );
          return new SentinelClientHttpResponse("系统正忙，请稍后重试1");
      }




}

