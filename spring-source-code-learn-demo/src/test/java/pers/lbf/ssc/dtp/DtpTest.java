/*
 * Copyright 2020 赖柄沣 bingfengdev@aliyun.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package pers.lbf.ssc.dtp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.ApplicationEventPublisher;
import pers.lbf.ssc.App;
import pers.lbf.ssc.dynamic.threadpool.config.ThreadPoolPropertyConfig;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2022/11/16 22:49
 */
@SpringBootTest(classes = App.class)
public class DtpTest {

    @Resource
    ApplicationEventPublisher publisher;

    @Resource
    ThreadPoolPropertyConfig threadPoolPropertyConfig;

    @Test
    public void test() throws InterruptedException {
//        Thread.sleep(3 * 1000);
        threadPoolPropertyConfig.setCorePoolSize(10);
        threadPoolPropertyConfig.setMaxPoolSize(20);
        publisher.publishEvent(new RefreshScopeRefreshedEvent());
        Thread.sleep(10 * 1000);

    }

}
