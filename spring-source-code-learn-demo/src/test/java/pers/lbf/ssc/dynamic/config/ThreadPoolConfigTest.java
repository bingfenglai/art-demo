package pers.lbf.ssc.dynamic.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pers.lbf.ssc.App;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

@SpringBootTest(classes = {
        App.class
})
class ThreadPoolConfigTest {

    @Resource(name = ThreadPoolConfig.THREAD_POOL_EXE_NAME)
    Executor executor;

    @Resource
    ThreadPoolPropertyConfig threadPoolPropertyConfig;

    @Test
    void createThreadPoolExecutor() {
        System.out.println(executor.toString());
        System.out.println(threadPoolPropertyConfig.toString());
    }
}