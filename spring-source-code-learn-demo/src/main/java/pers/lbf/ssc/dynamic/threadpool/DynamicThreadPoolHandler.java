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

package pers.lbf.ssc.dynamic.threadpool;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pers.lbf.ssc.cache.CacheService;
import pers.lbf.ssc.dynamic.config.ThreadPoolConfig;
import pers.lbf.ssc.dynamic.event.ThreadPoolConfigRefreshEvent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2022/11/14 21:01
 */
@Component
public class DynamicThreadPoolHandler {

    @Resource
    CacheService cacheService;

    static final String dtpKey = ThreadPoolConfig.THREAD_POOL_EXE_NAME;

    @EventListener(ThreadPoolConfigRefreshEvent.class)
    public void resolveThreadPoolConfigRefresh() {
        // 1.获取新的配置信息
        // 2.生成md5
        // 3.与旧的md5比较，如果不一致则获取线程池并更新参数
        // 4.如果一致，则忽略配置更新
        // 5.若更新失败则回滚到旧的配置
        // 6.调用通知接口，通知线程池变更结果。
        // 7.记录变更日志
    }

    /**
     * 将启动时初始化的线程池配置的摘要信息存入缓存
     */
    @PostConstruct
    public void init() {

    }

    /**
     * 清除缓存当中的md5
     */
    @PreDestroy
    public void destroy() {

    }


}


