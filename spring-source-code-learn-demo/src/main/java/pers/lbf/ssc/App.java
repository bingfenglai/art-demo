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

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2022/7/24 22:26
 */
@SpringBootApplication
@EnableFeignClients
@EnableAspectJAutoProxy
public class App {

    @Resource
    DemoApi demoApi;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @PostConstruct
    public void test() {

        String q = demoApi.getQ("java");
        System.out.println(q);
    }

//    @Bean
//    public PayService createPayService(OrderService orderService) {
//        return new PayService(orderService);
//    }

//    @Bean(autowire = Autowire.BY_NAME)
//    public PayService createPayService2(OrderService orderService) {
//        return new PayService(orderService);
//    }
}
