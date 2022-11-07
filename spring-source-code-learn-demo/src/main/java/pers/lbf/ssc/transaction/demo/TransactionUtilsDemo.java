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

package pers.lbf.ssc.transaction.demo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.lbf.ssc.transaction.util.TransactionUtils;

import java.util.UUID;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2022/11/7 23:37
 */
@Service
public class TransactionUtilsDemo {

    @Transactional(rollbackFor = Exception.class)
    public void doSomething() {

        // start tx
        String s = String.valueOf(UUID.randomUUID().getLeastSignificantBits());
        // end tx
        TransactionUtils.doTransactionCompletion(() -> {
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("事务%s已完成%n", s);
        });

        System.out.println("方法执行结束");


    }
}
