package pers.lbf.webflux.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/24 19:53
 */
public interface DemoService {

    Logger logger = LoggerFactory.getLogger(DemoService.class);

    /**
     * 模拟获取信息
     *
     * @return info
     */
    String getInfo();

    @Async
    Future<String> getInfo2();

    @Async
    Future<String> sayHello(String name);

    String sayHello2(String name);
}
