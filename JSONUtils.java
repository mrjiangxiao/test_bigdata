package com.atguigu.flume.interceptor;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Jx
 * @create 2021/10/15-11:51
 */
public class JSONUtils {
    public static boolean isJSON(String log) {
        //判断是否是JSON日志文件
        boolean flag = false;
        try {
            JSONObject.parseObject(log);
            flag = true;
        } catch (JSONException e) {
            flag = false;
        }

        return flag;
    }
}
