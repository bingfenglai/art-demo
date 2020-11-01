package pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.vo.ResultVO;

import java.util.HashMap;

/**测试接口
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/26 10:37
 */
@RestController
@RequestMapping("/test")
public class DemoController {

    /**
     * 原函数
     * @return map
     */
//    @SentinelResource(value = "/test/info",
//            fallback = "infoFallback",
//            blockHandler = ""
//            )
    @GetMapping("/info")
    public ResultVO info() {

        throw new RuntimeException("sleep~~~~~");
//        return map;
    }


    @GetMapping("/getInfoById")
    public ResultVO getInfoById(@RequestParam Integer id) {
        HashMap<Object, Object> map = new HashMap<>(3);
        map.put("id",id);
        map.put("name","Java开发实践");
        map.put("type","微信公众平台");
        return new ResultVO<>(map);
    }

    /**降级以及其他异常
     * fallback函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
     * @return
     */
    public ResultVO infoFallback(Throwable e){

        return new ResultVO<>("203","服务被降级,请稍后重试"+e.getMessage());
    }

    /**限流异常
     *  Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
     * @return
     */
    public ResultVO backExceptionHandler() {
        return new ResultVO<> ("406","服务被限流，请稍后重试");
    }
}
