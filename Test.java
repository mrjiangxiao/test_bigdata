package com.atguigu.flume.interceptor;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Jx
 * @create 2021/10/15-11:42
 */
public class Test {
    public static void main(String[] args) {
        JSONObject jsonObject = JSONObject.parseObject("{\"a\":\"b\"}");
        String a = jsonObject.getString("a");
        System.out.println(a);
    }
}
