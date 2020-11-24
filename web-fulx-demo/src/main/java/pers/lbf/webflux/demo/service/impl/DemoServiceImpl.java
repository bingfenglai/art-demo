package pers.lbf.webflux.demo.service.impl;


import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import pers.lbf.webflux.demo.service.DemoService;

import java.util.concurrent.Future;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/24 19:44
 */

@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String getInfo() {
        logger.warn("子线程获取信息");
        return "wxb Java开发实践";

    }

    @Override
    public Future<String> getInfo2() {
        logger.warn("子线程2获取信息");
        return new AsyncResult<>("wxb Java开发实践");
    }

    @Override
    public Future<String> sayHello(String name) {
        logger.warn(String.format("Hello %s",name));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("say ok ");
    }

    @Override
    public String sayHello2(String name) {
        logger.warn(String.format("Hello %s",name));
        return "ok";
    }


}
