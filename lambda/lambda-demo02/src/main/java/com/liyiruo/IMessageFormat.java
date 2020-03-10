package com.liyiruo;

/**
 * 1.lambda表达式 接口里只能有一个必须实现的方法方法
 * 2.但是可以有多个default方法
 * 3.可以有多个static 方法
 * 4.可以是重写Object的方法
 *
 * 消息传输格式转换接口
 *
 * @author liyiruo
 * @data 2020/3/9  2:53 上午
 */
@FunctionalInterface
public interface IMessageFormat {
    /**
     * 消息转换方法
     *
     * @param message 要转换的消息
     * @param format  转换的格式【xml/json】
     * @return {{@link java.lang.String}} 返回转换后的数据
     * @author liyiruo
     * @date 2020/3/9 2:55 上午
     */
    String format(String message, String format);

    /**
     * 验证消息是否合格
     *
     * @param msg 要验证的消息
     * @return {{@link boolean}} 返回的验证结果
     * @author liyiruo
     * @date 2020/3/9 3:10 上午
     */
    static boolean verifyMessage(String msg) {
        if (msg != null) {
            return true;
        }
        return false;
    }

    /**
     * 有这个方法不可以
     *
     * @param
     * @return {{@link null}}
     * @author liyiruo
     * @date 2020/3/9 3:19 上午
     */
    //  boolean test();
    /**
     *如果是重写了Object的方法 可以
     * @param
     * @return {{@link java.lang.String}}
     * @author liyiruo
     * @date 2020/3/9 3:21 上午
     */
    String toString();
}
