package com.liyiruo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyiruo
 * @data 2020/3/9  5:33 上午
 */
public class APP3 {
    public static void test(MyInterface<String, List> inter) {

        List<String> list = inter.strategy("hello", new ArrayList());
        System.out.println(list);
    }


    public static void main(String[] args) {
        //测试方法一
        test(new MyInterface<String, List>() {
            @Override
            public List strategy(String s, List list) {
                list.add(s);
                return list;
            }
        });

        //测试方法二
        test((x, y) -> {
            y.add(x);
            return y;
        });

        /*
        （x,y）->{...} -->test(param) -->param=MyInterface -->lambda表达式 -> MyInterface 类型
        这个就是对于lambda表达式的类型的检查，MyInterface 接口就是 lambda 表达式的目标类型（target typing)

        (x,y)->{...} MyInterface.strategy(T t, R r) --> MyInterface<String,list> inter
        --> T==String R ==list --> lambda -->(x,y) ==strategy(T t,R r) --> x==T==String  y==R==list
        lambda 表达式参数的类型检查
         */
    }


}



@FunctionalInterface
interface MyInterface<T, R> {
    R strategy(T t, R r);
}
