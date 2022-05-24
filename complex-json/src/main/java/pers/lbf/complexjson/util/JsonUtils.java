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

package pers.lbf.complexjson.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import java.util.Map;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2022/5/24 22:43
 */
public final class JsonUtils {

    /**
     * 将json转map
     *
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> jsonToMap(String jsonStr) {

        Gson gson = new Gson();
        return gson.fromJson(jsonStr, new TypeToken<Map<String, Object>>() {
        }.getType());
    }

    /**
     * 获取值
     *
     * @param jsonStr
     * @param path
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getValue(String jsonStr, String path, Class<T> clazz) {

        Map<String, Object> map = jsonToMap(jsonStr);
        OgnlContext ognlContext = new OgnlContext();
        ognlContext.setRoot(map);
        try {
            return (T) Ognl.getValue(path, ognlContext, ognlContext.getRoot());
        } catch (OgnlException e) {
            e.printStackTrace();
        }


        return null;
    }

}
