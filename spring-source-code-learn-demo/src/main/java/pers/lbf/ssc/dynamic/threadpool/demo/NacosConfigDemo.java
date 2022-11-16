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

package pers.lbf.ssc.dynamic.threadpool.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pers.lbf.ssc.cache.CacheService;
import pers.lbf.ssc.dynamic.threadpool.config.ThreadPoolConfig;
import pers.lbf.ssc.dynamic.threadpool.config.ThreadPoolPropertyConfig;
import pers.lbf.ssc.dynamic.threadpool.constants.ConfigSourceTypeEnums;
import pers.lbf.ssc.dynamic.threadpool.event.ThreadPoolConfigRefreshEvent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2022/11/16 21:18
 */
@Component
@Slf4j
public class NacosConfigDemo implements ApplicationContextAware {

    static ApplicationContext context;

    @Resource
    private CacheService cacheService;


    @EventListener(RefreshScopeRefreshedEvent.class)
    @Async
    public void listenConfigRefreshEvent() {
        log.info("触发刷新事件");
        if (!checkedDtpIsRefresh()) {
            log.info("线程池信息未更新");
            return;
        }
        log.info("线程池{}配置已更新", ThreadPoolConfig.THREAD_POOL_EXE_NAME);
        ApplicationEventPublisher eventPublisher = NacosConfigDemo.context;
        ThreadPoolConfigRefreshEvent event = new ThreadPoolConfigRefreshEvent(ConfigSourceTypeEnums.PROPERTY);
        eventPublisher.publishEvent(event);
    }

    private boolean checkedDtpIsRefresh() {

        ThreadPoolPropertyConfig newConfig = context.getAutowireCapableBeanFactory().getBean(ThreadPoolPropertyConfig.class);
        String oldMd5 = (String) cacheService.get(ThreadPoolConfig.THREAD_POOL_EXE_NAME);
        try {
            String newMd5 = getMd5(newConfig);

            if (Objects.equals(newMd5, oldMd5)) {
                return false;
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return true;
    }

    private String getMd5(ThreadPoolPropertyConfig newConfig) throws NoSuchAlgorithmException {

        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(newConfig.toString().getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        NacosConfigDemo.context = applicationContext;
    }


    /**
     * 将启动时初始化的线程池配置的摘要信息存入缓存
     */
    @PostConstruct
    public void init() throws NoSuchAlgorithmException {
        ThreadPoolPropertyConfig config = context.getAutowireCapableBeanFactory().getBean(ThreadPoolPropertyConfig.class);
        String md5 = this.getMd5(config);
        this.cacheService.set(ThreadPoolConfig.THREAD_POOL_EXE_NAME, md5);
    }

    /**
     * 清除缓存当中的md5
     */
    @PreDestroy
    public void destroy() {
        this.cacheService.remove(ThreadPoolConfig.THREAD_POOL_EXE_NAME);
    }
}
