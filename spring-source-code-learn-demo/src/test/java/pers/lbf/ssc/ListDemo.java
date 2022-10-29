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

package pers.lbf.ssc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2022/10/28 22:08
 */
public class ListDemo {

    public static void main(String[] args) {
        // 44166
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        int num = 10000000;
        long s1 = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            arrayList.add(i);
        }
        long s2 = System.currentTimeMillis();

        for (int i = 0; i < num; i++) {
            linkedList.add(i);
        }

        long s3 = System.currentTimeMillis();

        arrayList.remove(130430);
        long s4 = System.currentTimeMillis();

        linkedList.remove(130430);
        long s5 = System.currentTimeMillis();

        long l1 = s2 - s1;
        long l2 = s3 - s2;
        long l3 = s4 - s3;
        long l4 = s5 - s4;


        System.out.println("arrayList 插入耗时: " + l1);
        System.out.println("linkedList 插入耗时: " + l2);
        System.out.println("arrayList删除耗时：" + l3);
        System.out.println("linkedList删除耗时：" + l4);

    }
}
