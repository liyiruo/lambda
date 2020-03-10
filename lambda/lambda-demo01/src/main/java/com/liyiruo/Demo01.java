package com.liyiruo;

/**
 * @author liyiruo
 * @data 2020/3/8  8:49 下午
 */
public class Demo01 {
    public static void main(String[] args) {
        //1.传统模式下 新线程的创建
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threading..." + Thread.currentThread().getId());
            }
        }).start();

        //2.jdk8 新特性。lambda表达式优化线程模式

        new Thread(()->{
            System.out.println("threading..." + Thread.currentThread().getId());
        }).start();
    }
}
