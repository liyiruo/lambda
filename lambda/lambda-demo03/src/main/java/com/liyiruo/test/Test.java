package com.liyiruo.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * 1.静态方法引用的使用
 * 类型名称，方法名称（）-》类型名称：方法名称
 *
 * @author liyiruo
 * @data 2020/3/9  9:18 下午
 */
public class Test {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("tom", "男", 16));
        personList.add(new Person("jerry", "男", 15));
        personList.add(new Person("shuke", "女", 30));
        personList.add(new Person("tome", "男", 45));
        personList.add(new Person("beita", "女", 33));
        personList.add(new Person("damu", "女", 44));

        //1.匿名内部类的方式排序
        /*
        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
*/
        //2.lambda表达式的实现方式
//        Collections.sort(personList, (p1, p2) -> p1.getAge() - p2.getAge());

        //3.静态方法引用
//        Collections.sort(personList, Person::compareByAge);

        //4.实例方法的引用

      /*  PersonUtil pu = new PersonUtil();
        Collections.sort(personList,pu::compareByName);
        System.out.println("tom".hashCode());
        System.out.println("jerry".hashCode());
        System.out.println(personList);*/

        //5.构造方法引用
        IPerson ip = Person::new;
        Person person = ip.initPerson("jerry", "男", 22);
        System.out.println(person);


    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {
    private String name;
    private String gender;
    private int age;

    public static int compareByAge(Person p1, Person p2) {
        return p1.getAge() - p2.getAge();
    }
}

class PersonUtil {
    //增加一个实例方法
    public int compareByName(Person p1, Person p2) {
        return p1.getName().hashCode() - p2.getName().hashCode();
    }
}

interface IPerson{
    //抽象方法：通过制定类型的构造方法初始化对象数据
    Person initPerson(String name, String gender, int age);
}