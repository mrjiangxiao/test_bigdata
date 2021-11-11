package com.atguigu.flume.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;
import java.util.Map;

/**
 * @author Jx
 * @create 2021/10/15-12:51
 */
public class TimeStampInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        //向头部信息添加时间戳
        //给到HDFS存储使用,控制日志信息发送到正确的文件夹目录下(Y-m-d),防止发生零点漂移
        String log = new String(event.getBody());
        JSONObject jsonobject = JSONObject.parseObject(log);

        //获取数据json中的时间戳
        String ts = jsonobject.getString("ts");

        //获取头部信息
        Map<String, String> headers = event.getHeaders();

        headers.put("timestamp",ts);

        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        for (Event event : events) {
            intercept(event);
        }
        return events;
    }

    @Override
    public void close() {

    }

    public static class MyBuilder implements Interceptor.Builder {

        @Override
        public Interceptor build() {
            return new TimeStampInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
