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

package pers.lbf.spidemo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * 插件创建工厂类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2022/8/15 22:25
 */
public class PluginFactory {

    public void installPlugins() {

        // 初始化应用上下文
        Map<String, Object> context = new LinkedHashMap<>();
        // 应用版本
        context.put("_version", "1.0.0");
        // bean 对象
        context.put("_beans", new ArrayList<>());
        // 切面对象
        context.put("_aspects", new LinkedHashMap<>());

        // 扫描 classpath 下 Plugin 的实现

        ServiceLoader<Plugin> serviceLoader = ServiceLoader.load(Plugin.class);

        // 执行安装插件方法
        for (Plugin plugin : serviceLoader) {
            plugin.install(context);
        }

    }
}
