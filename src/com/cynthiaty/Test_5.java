package com.cynthiaty;

/**
 * 作者：cynthiaty
 * 功能：实现接口 VS 继承类
 * 时间：2017-06-20
 */
public class Test_5 {
    public static void main(String[] args) {
        // write your code here.

        LittleMonkey littleMonkey = new LittleMonkey();
        littleMonkey.jump();
        littleMonkey.swimming();
        littleMonkey.fly();
    }
}

class Monkey {
    final public void jump() {
        System.out.println("猴子会跳跃！");
    }
}

interface Fish_1 {
    void swimming();
}

interface Bird {
    public void fly();
}

/**
 * 继承是先天的能力，实现接口是后天努力获得的
 * 1、实现接口可以看作是对单继承的一种补充
 * 2、实现接口可以在不打破继承关系的前提下，对类功能扩展，非常灵活
 */
class LittleMonkey extends Monkey implements Fish_1, Bird {
    @Override
    public void swimming() {
        System.out.println("我学会游泳了！");
    }

    @Override
    public void fly() {
        System.out.println("我学会飞了！");
    }
}