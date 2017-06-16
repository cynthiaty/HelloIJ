package com.cynthiaty;

/**
 * 作者：cynthiaty
 * 功能：接口（interface）
 * 时间：2017-06-16
 */
public class Test_4 {
    public static void main(String[] args) {
        // write your code here.

        Human human = new Human();
        human.useUsb(new Camera());
        human.useUsb(new Phone());
        System.out.println("接口名.变量名 --> " + Usb_1.a);
    }
}

interface Usb_1 {
    int a = 1;
    void start();
    void stop();
}

interface Usb_2 extends Usb_1 {
    void charge();
}

interface Usb_3 {
    boolean flag = false;
}

class Human {
    public void useUsb(Usb_2 usb) {
        usb.start();
        usb.charge();
        usb.stop();
    }
}

//当一个类实现了一个接口，就必须实现接口中的所有方法
class Camera implements Usb_2 {
    public void start() {
        System.out.println("相机：主人，笑一个，开始照相了！");
    }

    public void stop() {
        System.out.println("相机：主人，先不陪你了，我得休息一会儿！");
    }

    public void charge() {
        System.out.println("相机：主人，容我补充一点能量吧！");
    }
}

class Phone implements Usb_2, Usb_3 {
    public void start() {
        System.out.println("手机：主人，该给妈妈打电话了！");
    }

    public void stop() {
        System.out.println("手机：主人，先不陪你了，我得休息一会儿！");
    }

    public void charge() {
        System.out.println("手机：主人，容我补充一点能量吧！");
    }
}
