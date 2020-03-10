package com.liyiruo.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liyiruo
 * @data 2020/3/10  12:46 上午
 */
public class Test2 {
    public static void main(String[] args) {

        //1.添加测试数据，存储多个账号的列表
        List<String> accounts = new ArrayList<>();
        accounts.add("1234");
        accounts.add("12345");
        accounts.add("123456");
        accounts.add("123");
        accounts.add("12");
        accounts.add("1");

        //1.1 业务需要：长度大于等于5的有效账号
        for (String account :
                accounts) {
            if (account.length() >= 5) {
                System.out.println("foreach>>:R"+account);
            }
        }
        //1.2迭代方式进行操作
        Iterator<String> it = accounts.iterator();
        while (it.hasNext()) {
            String account = it.next();
            if (account.length() >= 5) {
                System.out.println("it>>:"+account);
            }
        }

        //1.3 Stream 结合lambda表达式，完成业务逻辑
        List<String> collect = accounts.stream().filter(a -> a.length() >= 5).collect(Collectors.toList());

        System.out.println(collect);
    }
}
