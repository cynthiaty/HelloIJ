package com.cynthiaty;

/**
 * 作者：cynthiaty
 * 功能：线程使用的两种方式：1、继承Thread类，重写run方法；2、实现Runnable接口，重写run方法
 * 时间：2017-07-26
 */
public class Test_11 {
    public static void main(String[] args) {
        // write your code here.

        Dog_2 dog2 = new Dog_2();   //创建线程-->新建状态（new）
        dog2.start();               //调用start()方法-->就绪状态（Runnable）

        Dog_4 dog4 = new Dog_4();
        Thread thread = new Thread(dog4);//借助Thread创建线程-->新建状态（new）
        thread.start();             //调用start()方法-->就绪状态（Runnable）
    }
}

class Dog_2 extends Thread {
    int counter = 0;

    @Override
    public void run() {             //运行状态（Running）
        while (true) {
            try {
                sleep(500); //调用sleep()方法-->阻塞状态（Blocked），并释放资源
            }catch (Exception e) {
                e.printStackTrace();
            }

            counter++;
            System.out.println("我是一个线程，我爱计数！  " + counter);
            if(counter == 5) {
                break;              //退出循环，线程死亡-->死亡状态（Dead）
            }
        }
    }
}

class Dog_4 implements Runnable {
    int times = 0;

    @Override
    public void run() {             //运行状态（Running）
        while (true) {
            try {
                Thread.sleep(1000); //调用sleep()方法-->阻塞状态（Blocked），并释放资源
            }catch (Exception e) {
                e.printStackTrace();
            }

            times++;
            System.out.println("我是一个线程，时间的魅力在于沉淀！  " + times);
            if(times == 5) {
                break;              //退出循环，线程死亡-->死亡状态（Dead）
            }
        }
    }
}