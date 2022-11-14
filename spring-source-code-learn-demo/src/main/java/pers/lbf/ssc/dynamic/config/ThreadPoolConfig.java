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

package pers.lbf.ssc.dynamic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2022/11/14 22:39
 */
@Configuration
public class ThreadPoolConfig {

    public static final String THREAD_POOL_EXE_NAME = "default-exe";

    @Bean(THREAD_POOL_EXE_NAME)
    public Executor createThreadPoolExecutor(ThreadPoolPropertyConfig config) throws IllegalAccessException, InstantiationException {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(config.getCorePoolSize());
        executor.setMaxPoolSize(config.getMaxPoolSize());
        executor.setQueueCapacity(config.getQueueCapacity());
        executor.setThreadNamePrefix(config.getThreadNamePrefix());
        executor.setAllowCoreThreadTimeOut(config.getAllowCoreThreadTimeOut());
        executor.setKeepAliveSeconds(config.getKeepAliveSeconds());
        executor.setRejectedExecutionHandler(config.getHandlerType().newInstance());
        return executor;
    }

}
