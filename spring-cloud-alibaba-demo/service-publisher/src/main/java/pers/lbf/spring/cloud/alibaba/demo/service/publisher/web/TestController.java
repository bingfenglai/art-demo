package pers.lbf.spring.cloud.alibaba.demo.service.publisher.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.lbf.spring.cloud.alibaba.demo.service.publisher.dao.IConfigInfoDao;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/20 9:34
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private IConfigInfoDao configInfoDao;


    @Value("${server.port}")
    private String port;

    @GetMapping("/getInfo")
    public String doPay() {
        return "来自服务提供者 " + port + " 的信息：后天刮台风";
    }

    @GetMapping("/getConfig")
    public String getConfig() {

        return configInfoDao.selectInfo().toString();
    }


}
