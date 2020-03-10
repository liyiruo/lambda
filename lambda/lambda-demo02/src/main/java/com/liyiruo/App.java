package com.liyiruo;

import com.liyiruo.impl.MessageFormatImpl;
import com.liyiruo.impl.UserCredentialImpl;

import java.util.UUID;
import java.util.function.*;

/**
 * 需求改动：
 * 所有的用户验证，可以同时获取用户的验证信息【是否认证成功】｜ 成功～返回用户｜null
 */
public class App {
    public static void main(String[] args) {
        IUserCredential ic = new UserCredentialImpl();
        System.out.println(ic.verifyUser("admin"));
        System.out.println(ic.getCredential("manage"));
        String msg = "hello world";
        if (IMessageFormat.verifyMessage(msg)) {

            IMessageFormat mfi = new MessageFormatImpl();
            System.out.println(mfi.format("hello", "json"));

        }


        //匿名内部类的 实现接口的抽象方法
        IUserCredential ic2 = new UserCredentialImpl() {
            @Override
            public String verifyUser(String username) {
                return "admin".equals(username) ? "管理员" : "会员";
            }
        };
        System.out.println(ic2.verifyUser("manager"));
        System.out.println(ic2.verifyUser("admin"));


        //lambda表达式，针对函数式接口的简单实现
        IUserCredential ic3 = (String username) -> {
            return "admin".equals(username) ? "lbd管理员" : "lbd会员";
        };

        /**
         * lambda表达式  相当于实现了这个 Predicate接口，并重写了它的方法  有返回值
         * @param args
         * @return {}
         * @author liyiruo
         * @date 2020/3/9 3:40 上午
         */
        Predicate<String> pre = (String username) -> {
            return "admin".equals(username);
        };
        System.out.println(pre.test("admin"));
        System.out.println(pre.test("manager"));

        /**
         * lambda表达式  相当于实现了这个 Consumer接口，并重写了它的方法  没有返回值
         * @param args
         * @return {}
         * @author liyiruo
         * @date 2020/3/9 3:52 上午
         */

        Consumer<String> con = (String message) -> {
            System.out.println("要发送的消息" + message);
            System.out.println("消息发送完成");
        };
        con.accept("hello ni hao");
        con.accept("i am liyiruo");

        /**
         * lambda表达式  相当于实现了这个 Function接口，并重写了它的方法 ；
         *              传入两个参数，一个是传入的类型 一个是返回的类型
         * @param args String类型
         * @return {Integer 类型}
         * @author liyiruo
         * @date 2020/3/9 3:52 上午
         */
        Function<String, Integer> fun = (String gender) -> {
            return "male".equals(gender) ? 1 : 0;
        };
        System.out.println(fun.apply("male"));
        System.out.println(fun.apply("female"));


        /**
         * lambda表达式  相当于实现了这个 Supplier接口，并重写了它的方法  没有参数，有一个get()方法
         * @param args
         * @return {String 类型}
         * @author liyiruo
         * @date 2020/3/9 4:00 上午
         */
        Supplier<String> sup = () -> {
            return UUID.randomUUID().toString();
        };
        System.out.println(sup.get());
        System.out.println(sup.get());
        System.out.println(sup.get());
        System.out.println(sup.get());

        /**
         * lambda表达式  相当于实现了这个 UnaryOperator (extends{@link Function})接口，并重写了它的方法
         *              传入一个类型 返回一个类型（两个类型相同）
         * @param args
         * @return {}
         * @author liyiruo
         * @date 2020/3/9 4:08 上午
         */
        UnaryOperator<String> uo = (String img) -> {
            img += "[100*200]";
            return img;
        };
        System.out.println(uo.apply("这是一个字符串"));
        // System.out.println(UnaryOperator.identity().toString());//这个方法有什么用呢？


        /**
         * lambda表达式  相当于实现了这个 BinaryOperator接口，并重写了它的方法  没有返回值
         * @param args
         * @return {}
         * @author liyiruo
         * @date 2020/3/9 4:14 上午
         */
        BinaryOperator<Integer> bo = (Integer i1, Integer i2) -> {
            return i1 > i2 ? i1 : i2;
        };
        System.out.println(bo.apply(12, 13));



        /*
        java.util.function 提供了大量的函数式接口
        Predicate 接收T对象，返回一个boolean类型结果
        Consumer 接收参数T对象，没有返回值
        Function 接收参数T对象，返回R对象
        Supplier 不接受任何参数，直接通过get()获取制定类型的对象
        UnaryOperator 接口参数T对象，执行业务处理后，返回更新后的T对象
        BinaryOperator 接口接收两个T对象，执行业务处理后，返回一个T对象
         */


        //1.lambda表达式的基本语法
        /*
        1）声明：就是和lambda表达式绑定的接口类型
        2）参数：包含在一对（）括号中，和绑定的接口中的接口中的抽象方法中的参数个数几顺序一致。
        3）操作法：->
        4)执行代码块： 包含在一对打括号中，出现在操作符号右侧
        [接口声明]=（参数）->{执行代码块}
         */


        //没有参数，没有返回值的lambda表达式绑定的接口
        //如果只有一行代码，可以不要{}
        ILambda1 l11 = () -> System.out.println("就一行");
        ILambda1 l12 = () -> {
            System.out.println("第一行");
            System.out.println("第二行");
        };

        l11.test();
        l12.test();


        //带有参数，没有返回值的lambda表达式

        ILambda2 l21 = (String a) -> {
            System.out.println("你输入的是：==》" + a);
        };
        l21.test("你好");


        //带有2参数，没有返回值的lambda表达式
        ILambda3 l31=(String name,int n)->{
            System.out.println(name + ",你的年龄是：" + n);
        };
        l31.test("liyiruo",18);


        //如果你足够懒，参数的类型 可以不写
        ILambda3 l32=( name, n)->{
            System.out.println(name + ",你的年龄是：" + n);
        };
        l32.test("liyiruo",18);

        //带有参数，带有返回值的lambda表达式
        ILambda4 l41=(a)->{
            return a + "";
        };
        System.out.println(l41.test(999));


        //带有2个参数，带有返回值的lambda表达式
        ILambda5 l51=(a,b)->{
            return a + b;
        };
        System.out.println(l51.test(2, 3));
        //如果你足够懒 你可以这样写 如果要执行的代码块只有一行，return 可以不写
        ILambda5 l52=(a,b)->a+b;
        System.out.println(l52.test(15, 16));

        /*
        1.lambda表达式，必须和接口进行绑定
        2.lambda表达式的参数，可以附带0个到n个参数，括号中的参数类型可以不用指定，
            jvm在运行式，会自动根据绑定的抽象方法中的参数进行推到导。
        3.lambda表达式的返回值，如果代码块只有一行，并且没有大括号，不用写return关键字。
            单行代码的执行结果，会自动返回
            如果添加了大括号，或者有多行代码必须通过return关键字返回执行结果。
         */

    }

    //没有参数，没有返回值的lambda表达式绑定的接口
    interface ILambda1 {
        void test();
    }

    //带有参数，没有返回值的lambda表达式
    interface ILambda2 {
        void test(String name);
    }
    interface ILambda3{
        void test(String name, int n);
    }

    //带有参数，带有返回值的lambda表达式
    interface ILambda4{
        String test(int a);
    }
    //带有2个参数，带有返回值的lambda表达式
    interface ILambda5{
        int test(int a, int b);
    }

}
