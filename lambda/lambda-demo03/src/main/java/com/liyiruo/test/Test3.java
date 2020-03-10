package com.liyiruo.test;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import sun.lwawt.macosx.CSystemTray;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream 常见操作API介绍
 * 1.聚合操作
 * <p>
 * 2.stream的处理流程
 * 数据源
 * 数据转换
 * 获取结果
 * 3.获取Stream对象
 * 1.从集合或者数组中获取
 * Collection。stream，如ccounts.stream（）
 * Collection。parallelStream()
 * Arrays.stream(T t)
 * 2.BufferReader
 * BufferRead.lines()->stream()
 * 3.静态工厂
 * java.util.stream.Instream.range()
 * java.nio.file.Files.walk()
 * 4.自定构建
 * java.util.Spliterator
 * 5.更多的方式……
 * Random.ints()
 * Pattern.splitAsStream()……
 * 4.中间操作API{intermediate}
 * 操作结果是一个Stream，中间操作可以有一个或者多个连续的中间操作，需要注意的是，
 * 中间操作只记录操作方式，不做具体执行，直到结束操作发生时，才做数据的最终执行。
 * 中间操作：就是业务逻辑处理
 * 中间操作过程：无状态；数据处理时，不受前置中间操作的影响。
 * map/filter/peek/parallel/sequential/unordered
 * 有状态：数据处理时，受到前置中间操作的影响
 * distinct/sorted/limit/skip
 * 5.终结操作/结束操作{Terminal}
 * 需要注意：一个Stream对象，只能有一个Terminal操作，这个操作一旦发生，就会真实处理数据，生成对应的处理结果
 * 终结操作：非短路操作：当前的Stream对象必须处理完集合中所有数据，才能得到处理结果。
 * forEach/forEachOrdered/toArray/reduce/collect/min/max/count/iterator
 * 短路操作：当前的Steam对象在处理过程中，一旦满足某个条件，就可以得到结果。
 * anyMatch/allMatch/noneMatch/findFirst/findAny等
 * short-circuiting,无限大的Stream->有限大的stream
 *
 * @author liyiruo
 * @data 2020/3/10  1:01 上午
 */
public class Test3 {

    public static void main(String[] args) {
        //1.批量数据 ->steam对象
        //多个数据
        Stream stream = Stream.of("zhangsha", "lisi", "wangwu");
        //数组
        String[] strArrays = new String[]{"xueqi", "biyao"};
        Stream stream1 = Arrays.stream(strArrays);

        //列表
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        Stream stream2 = list.stream();

        //集合
        Set<String> set = new HashSet<>();
        set.add("y");
        set.add("u");
        set.add("i");
        set.add("o");
        Stream stream3 = set.stream();

        //map
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 12);
        map.put("c", 123);
        map.put("d", 1234);
        Stream stream4 = map.entrySet().stream();

        //2.Stream 对象对于基本数据类型的功能封装
        //int /long /double
        IntStream.of(10, 20, 30).forEach(System.out::println);
        IntStream.range(1, 5).forEach(System.out::println);
        IntStream.rangeClosed(1, 5).forEach(System.out::println);

        //3.Stream 对象 --> 转换得到指定的数据类型
        //数组
        Object[] objx = stream.toArray(String[]::new);
        //字符串
        String str = stream1.collect(Collectors.joining()).toString();
        System.out.println(str);
        //列表
        List<String> listx = (List<String>) stream2.collect(Collectors.toList());
        System.out.println(listx);
        //集合
        Set<String> setx = (Set<String>) stream3.collect(Collectors.toSet());
        System.out.println(setx);
        //Map
        Map<String, String> mapx = (Map<String, String>) stream4.collect(Collectors.toMap(x -> x, y -> "v:" + y));
        System.out.println(mapx);


        //4.stream中常见API操作
        List<String> accountList = new ArrayList<>();
        accountList.add("1");
        accountList.add("23");
        accountList.add("456");
        accountList.add("7890");
        //map()中间操作，map()方法接收一个Functional接口

//        accountList=accountList.stream().map(x->"this is a number>>"+x).collect(Collectors.toList());

        //filter() 添加过滤条件，过滤符合条件的用户
//        accountList=accountList.stream().filter(x->x.length()<3).collect(Collectors.toList());
//        System.out.println(accountList);

        //forEach 增强型循环
        accountList.forEach(x -> System.out.println("66666~:" + x));

        //peek()中间操作，迭代数据完成数据的一次处理过程
        accountList.stream()
                .peek(x -> System.out.println("peek 1:" + x))
                .peek(x -> System.out.println("peek 2:" + x))
                .forEach(System.out::println);


        //Stream中对于数据运算的支持
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(1234567);
        intList.add(1);
        intList.add(12);
        intList.add(123);
        intList.add(1234);
        intList.add(12345);
        intList.add(1234567);
        intList.add(1);
        //skip()中间操作，有状态，跳过部分数据
        intList.stream().skip(3).forEach(System.out::println);
        //limit() 中间操作，有状态，限制输出数据量
        System.out.println("------");
        intList.stream().skip(2).limit(2).forEach(System.out::println);
        System.out.println("------");
        //distinct()中间操作，有状态，剔除重复的数据
        intList.stream().distinct().forEach(System.out::println);
        System.out.println("------");
        //sorted()中间操作，有状态，排序
        intList.stream().sorted().forEach(System.out::println);
        //max() 获取最大值\
        System.out.println("------");
        Optional<Integer> max = intList.stream().max((x, y) -> x - y);
        System.out.println("max:" + max.get());
        System.out.println("------");
        //min()获取最小值
        Optional<Integer> min = intList.stream().min((x, y) -> x - y);
        System.out.println("min:" + min.get());
        //reduce()合并处理数据
        Optional<Integer> reduce = intList.stream().reduce((sum, x) -> sum + x);
        System.out.println("reduce:"+reduce.get());

    }

}
