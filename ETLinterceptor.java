package com.atguigu.flume.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.Iterator;
import java.util.List;

/**
 * @author Jx
 * @create 2021/10/15-11:43
 */
public class ETLinterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        //过滤掉不是JSON格式的文件
        String log = new String(event.getBody());
        if (JSONUtils.isJSON(log)) {
            return event;
        } else {
            return null;
        }
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        //使用迭代器,删除为null的日志数据
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext()) {
            Event event = iterator.next();
            if (intercept(event) == null) {
            iterator.remove();
            }
        }
        return events;
    }

    @Override
    public void close() {

    }

    public static class MyBuilder implements Interceptor.Builder {

        @Override
        public Interceptor build() {
            return new ETLinterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
