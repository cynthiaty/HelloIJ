package com.cynthiaty;

/**
 * 作者：cynthiaty
 * 功能：抽象类存在的必要性
 * 时间：2017-06-15
 */
public class Test_3 {
    public static void main(String[] args) {
        // write your code here.

        //Animal_1 = new Animal_1();    // error，抽象类不能被实例化
    }
}

abstract class Animal_1{
    private String name;
    private int age;

    /*
    public void cry() {
        System.out.println("动物都会叫，但不知道怎么叫！");
    }

    public void eat() {
        System.out.println("动物都要吃东西，但不知道吃什么！");
    }*/

    abstract public void cry();
    abstract public void eat();
}

//当一个类继承的父类为abstract时，我们必须实现抽象类的所有abstract方法
class Dog_1 extends Animal_1 {
    public void cry() {
        System.out.println("狗狗汪汪叫！");
    }

    public void eat() {
        System.out.println("狗狗爱吃骨头:");
    }
}

class Cat_1 extends Animal_1 {
    public void cry() {
        System.out.println("猫咪喵喵叫！");
    }

    public void eat() {
        System.out.println("猫咪爱吃鱼:");
    }
}