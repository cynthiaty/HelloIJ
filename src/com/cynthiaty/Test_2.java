package com.cynthiaty;

/**
 * 作者：cynthiaty
 * 功能：演示多态--一个引用（类型）在不同情况下的多种状态
 * 时间：2017-06-06
 */
public class Test_2 {

    public static void main(String[] args) {
        // write your code here

        //Animal animal = new Dog();
        //animal.cry();
        Master master = new Master();
        master.feed(new Dog(), new Bone());
        master.feed(new Cat(), new Fish());
    }
}

class Master {
    public void feed(Animal animal, Food food) {
        animal.eat();
        food.show();
    }
}

class Food {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("显示食物名称！");
    }
}

class Bone extends Food {
    public void show() {
        System.out.println("骨头！");
    }
}

class Fish extends Food {
    public void show() {
        System.out.println("鱼！");
    }
}

class Animal {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void cry() {
        System.out.println("动物都会叫！");
    }

    public void eat() {
        System.out.println("动物都要吃东西！");
    }
}

class Dog extends Animal {
    public void cry() {
        System.out.println("狗狗汪汪叫！");
    }

    public void eat() {
        System.out.println("狗狗爱吃骨头:");
    }
}

class Cat extends Animal {
    public void cry() {
        System.out.println("猫咪喵喵叫！");
    }

    public void eat() {
        System.out.println("猫咪爱吃鱼:");
    }
}