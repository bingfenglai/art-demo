package pers.lbf.webflux.demo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.lbf.webflux.demo.pojo.ResultVO;
import pers.lbf.webflux.demo.service.DemoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**WebFlux 的Netty是多路IO复用模型
 * 对于服务器而言可以使用少数线程处理多个任务，
 * 但对于客户端来说，服务器的响应时间并不会得到提升，它还是会等待服务器执行完毕
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/24 13:03
 */
@RestController
@RequestMapping("/demo")
@EnableAsync
public class DemoController {

    @Autowired
    private DemoService demoService;

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/getInfo")
    public Mono<ResultVO> getInfo() {

        ResultVO vo = new ResultVO("303", "wxb: Java开发实践");
        return Mono.just(vo);
    }

    @GetMapping("/infoList")
    public Flux<ResultVO> getInfoList(){
        ResultVO ok1 = ResultVO.ok();
        ResultVO ok2 = ResultVO.ok();
        ResultVO ok3 = ResultVO.ok();
        ResultVO ok4 = ResultVO.ok();
        ResultVO ok5 = ResultVO.ok();
        ResultVO ok6 = ResultVO.ok();
        return Flux.just(ok1,ok2,ok6,ok5,ok3,ok4);
    }


    @GetMapping("/getinfo2")
    public Mono<ResultVO> getinfo2() throws ExecutionException, InterruptedException {
        logger.info("主线程进入");
        String info = demoService.getInfo();
        Future<String> future = demoService.getInfo2();
        logger.info(info);
        logger.info(future.get());
        logger.info("主线程准备返回");
    return Mono.just(new ResultVO("A0000", future.get()));
    }


    /**
     * 只有返回Mono时才有“不适当地调用阻塞方法”
     */
    @GetMapping("/sayHello/{name}")
    public void sayHello(@PathVariable String name) throws ExecutionException, InterruptedException {
        Future<String> stringFuture = demoService.sayHello(name + "1");
        String s = demoService.sayHello2(name + "2");
        String info = demoService.getInfo();
        logger.info(info);
        // 启动异步线程执行sayHello方法后，继续往下执行到获取异步线程执行结果前阻塞结果
        logger.info(stringFuture.get());
        logger.info(s);
    }



}
