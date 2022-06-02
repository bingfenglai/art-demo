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

package pers.lbf.requestmerge;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2022/6/1 22:45
 */
public class MergeDemo {

    /**
     * 总库存数
     */
    private Integer totalStock = 5;

    private BlockingDeque<RequestPromise> deque = new LinkedBlockingDeque<>();

    public static void main(String[] args) {

    }


    /**
     * 扣减库存方法
     *
     * @param ur
     * @return
     */
    Result deducting(UserRequest ur) {

        return Result.builder()
                .success(true)
                .message("操作成功！")
                .code(2000)
                .build();
    }

}

