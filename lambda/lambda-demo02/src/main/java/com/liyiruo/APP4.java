package com.liyiruo;

/**
 * @author liyiruo
 * @data 2020/3/9  5:55 上午
 */
public class APP4 {
    interface Param1 {
        void outInfo(String info);
    }

    interface Param2 {
        void outInfo(String info);
    }


    public void lambdaMethod(Param1 param) {
        param.outInfo("hello param1 liyiruo!");

    }

    public void lambdaMethod(Param2 param) {
        param.outInfo("hello param2 liyiruo!");
    }

    public static void main(String[] args) {
        APP4 app = new APP4();
        app.lambdaMethod(new Param1() {
            @Override
            public void outInfo(String info) {
                System.out.println(info);
            }
        });

        app.lambdaMethod(new Param2() {
            @Override
            public void outInfo(String info) {

                System.out.println("-----------");
                System.out.println(info);
            }
        });


        /*
        lambda 表达式存在类型检查 -》 自动推导lambda表达式的目标类型
        lambdaMethod()-> 方法 -》重载方法
                -》Param1 函数式接口
                -》Param2 函数式接口

                调用方法 --》传递Lambda 表达式 -》自动推导 -》 Param1 | Param2
                */


//        app.lambdaMethod(()->{}); //此处不能用lambda 表达式 无法确定（）里式哪个接口


    }

}
