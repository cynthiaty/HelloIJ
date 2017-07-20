package com.cynthiaty;

import java.io.*;

/**
 * 作者：cynthiaty
 * 功能：对象数组的使用
 * 时间：2017-07-20
 */
public class Test_6 {
    public static void main(String[] args) throws Exception {
        // write your code here.

        Dog_3[] dogs = new Dog_3[4];

        //从控制台依次获取狗狗的信息
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        for(int i = 0; i < dogs.length; i++) {
            dogs[i] = new Dog_3();      //这一步很重要，需要给每只狗狗分配内存，否则异常NullPointerException
            System.out.println("请输入第" + (i + 1) + "只狗狗的名字：");
            String name = br.readLine();//从控制台读取狗狗的名字
            dogs[i].setName(name);
            System.out.println("请输入第" + (i + 1) + "只狗狗的体重：");
            float weight = Float.parseFloat(br.readLine());//从控制台读取狗狗的体重
            dogs[i].setWeight(weight);
        }

        //计算狗狗的平均体重
        float allWeight = 0;
        float averageWeight = 0;
        for(int i = 0; i < dogs.length; i++) {
            allWeight += dogs[i].getWeight();
        }
        averageWeight = allWeight / dogs.length;
        System.out.println("狗狗的平均体重 = " + averageWeight);

        //找出体重最大的狗狗
        //假设第一只狗狗的体重是最大的（）选择排序
        int maxIndex = 0;
        float maxWeight = dogs[0].getWeight();
        for(int i = 1; i < dogs.length; i++) {
            if (maxWeight < dogs[i].getWeight()) {
                maxIndex = i;
                maxWeight = dogs[i].getWeight();
            }
        }
        System.out.println("体重最大的狗狗名字 = " + dogs[maxIndex].getName() + "\n"
                + "体重 = " + maxWeight);
    }
}

class Dog_3 {
    private String name;
    private float weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
