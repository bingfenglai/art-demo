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

package pers.lbf.ssc.transaction.util;

import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 事务工具类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2022/11/7 23:27
 */

public final class TransactionUtils {

    private TransactionUtils() {
    }

    public static void doTransactionCompletion(Runnable run) {

        // 如果当前有事务被激活，则注册回调函数
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            DoTransactionCompletion doTransactionCompletion = new DoTransactionCompletion(run);
            TransactionSynchronizationManager.registerSynchronization(doTransactionCompletion);
        }

    }

    /**
     * 事务成功之后做某事，例如发布成功事件、例如向mq发送消息
     */
    static class DoTransactionCompletion implements TransactionSynchronization {

        private final Runnable run;

        public DoTransactionCompletion(Runnable run) {
            if (null == run) {
                throw new IllegalStateException("参数 run 不能为空");
            }
            this.run = run;
        }

        @Override
        public void afterCompletion(int status) {
            if (status != TransactionSynchronization.STATUS_COMMITTED) {
                return;
            }

            this.run.run();

        }
    }
}
