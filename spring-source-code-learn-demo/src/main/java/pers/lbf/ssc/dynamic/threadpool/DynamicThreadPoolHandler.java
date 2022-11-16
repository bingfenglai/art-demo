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

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pers.lbf.ssc.dynamic.threadpool.constants.ConfigSourceTypeEnums;
import pers.lbf.ssc.dynamic.threadpool.event.ThreadPoolConfigRefreshEvent;
import pers.lbf.ssc.dynamic.threadpool.refresh.RefreshDtpConfig;

import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2022/11/14 21:01
 */
@Component
@Slf4j
public class DynamicThreadPoolHandler {

    @Autowired
    private List<RefreshDtpConfig> refreshDtpConfigs;


    @EventListener
    public void resolveThreadPoolConfigRefresh(ThreadPoolConfigRefreshEvent event) {
        //获取线程池并更新参数

        ConfigSourceTypeEnums type = (ConfigSourceTypeEnums) event.getSource();

        for (RefreshDtpConfig refreshDtpConfig : refreshDtpConfigs) {
            boolean flag = refreshDtpConfig.refreshDtpConfig(type);
            if (flag) {
                log.info("线程池更新完成，source={}", type.name());
                break;
            }
        }

        // 5.若更新失败则回滚到旧的配置
        // 6.调用通知接口，通知线程池变更结果。
        // 7.记录变更日志
    }


}


