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

package pers.lbf.ssc.dynamic.threadpool.refresh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pers.lbf.ssc.dynamic.threadpool.config.ThreadPoolConfig;
import pers.lbf.ssc.dynamic.threadpool.config.ThreadPoolPropertyConfig;
import pers.lbf.ssc.dynamic.threadpool.constants.ConfigSourceTypeEnums;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2022/11/16 21:59
 */
@Component
@Slf4j
public class RefreshDtpConfigImplWithProperty extends BaseRefreshDtpConfig implements RefreshDtpConfig {

    @Resource
    private ThreadPoolPropertyConfig threadPoolPropertyConfig;

    @Resource(name = ThreadPoolConfig.THREAD_POOL_EXE_NAME)
    private Executor executor;

    public RefreshDtpConfigImplWithProperty() {
        super(ConfigSourceTypeEnums.PROPERTY);
    }

    @Override
    public ThreadPoolPropertyConfig getDtpConfig() {
        log.info("从配置文件当中加载线程池配置");
        return threadPoolPropertyConfig;
    }

    @Override
    protected Executor getExecutor() {
        return this.executor;
    }
}
