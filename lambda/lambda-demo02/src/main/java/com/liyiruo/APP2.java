package com.liyiruo;

/**
 * @author liyiruo
 * @data 2020/3/9  4:58 上午
 */
public class APP2 {

    String s1 = "全局变量";

    public void testInnerClass() {
        String s2 = "局部变量";

        new Thread(

                new Runnable() {
                    String s3 = "内部变量";

                    @Override
                    public void run() {
                        //访问全局变量
//                        System.out.println(this.s1);//this 关键字～表示是当前内部类型的对象
                        System.out.println(s1);

                        System.out.println(s2);//局部变量的访问，～不能对局部变量进行数据的修改【final】
//                        s2 = "hello";

                        System.out.println(s3);
                        System.out.println(this.s3);


                    }
                }
        ).start();
    }


    public void testLambda() {
        String s2 = "局部变量lambda";
        new Thread(
                () -> {
                    String s3 = "内部变量lambda";
                    //访问全局变量
                    System.out.println(this.s1);//this关键字，表示的就是所属方法所在类型的对象
                    //访问局部
                    System.out.println(s2);

//                    s2 = "hello";
                    System.out.println(s3);

                    s3 = "lambda 内部变量直接修改";

                }

        ).start();
    }


    public static void main(String[] args) {
        APP2 app2 = new APP2();
        app2.testInnerClass();
        app2.testLambda();


    }
}
