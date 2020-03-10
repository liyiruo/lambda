package com.liyiruo.impl;

import com.liyiruo.IMessageFormat;

/**
 * @author liyiruo
 * @data 2020/3/9  3:13 上午
 */
public class MessageFormatImpl implements IMessageFormat {
    @Override
    public String format(String message, String format) {
        System.out.println("消息转换……");
        return message;
    }
}
