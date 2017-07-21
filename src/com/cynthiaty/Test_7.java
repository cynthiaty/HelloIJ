package com.cynthiaty;

import java.util.Calendar;

/**
 * 作者：cynthiaty
 * 功能：排序算法--冒泡排序法、选择排序法
 * 时间：2017-07-21
 */
public class Test_7 {
    public static void main(String[] args) {
        // write your code here.

        //int[] arr = {5, -1, 8, 9, -2};
        int len = 50000;
        int[] arr = new int[len];
        for(int i = 0; i < len; i++) {
            arr[i] = (int)(Math.random() * len);//Math.random() 0.0 <= x < 1.0
        }

        //Bubble bubble = new Bubble();
        Select select = new Select();

        Calendar calendar = Calendar.getInstance();
        System.out.println("排序前：" + calendar.getTime());
        //bubble.sort(arr);
        select.sort(arr);
        calendar = Calendar.getInstance();
        System.out.println("排序后：" + calendar.getTime());

        /*
        for(int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }*/
    }
}

class Bubble {
    public void sort(int[] arr) {
        int temp = 0;

        //外层循环决定跑几趟
        for(int i = 0; i < arr.length - 1; i++) {
            //依次比较前后两个数的大小，若前数大于后数则交换
            for(int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}

class Select {
    public void sort(int[] arr) {
        int temp = 0;

        //外层循环决定跑几趟
        for(int i = 0; i < arr.length - 1; i++) {
            //假设无序数列的第一个数为最小的
            int min = arr[i];
            int minIndex = i;

            //依次从无序数列中找到最小的数
            for(int j = i + 1; j < arr.length; j++) {
                if(min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }

            //将最小数放到数列的第一个位置
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}