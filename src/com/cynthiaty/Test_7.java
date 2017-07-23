package com.cynthiaty;

import java.util.Calendar;

/**
 * 作者：cynthiaty
 * 功能：排序算法--冒泡排序法、选择排序法、插入排序法、快速排序法
 * 时间：2017-07-21
 */
public class Test_7 {
    public static void main(String[] args) {
        // write your code here.

        int[] arr = {5, -1, 8, 9, -2};
        /*int len = 50000;
        int[] arr = new int[len];
        for(int i = 0; i < len; i++) {
            arr[i] = (int)(Math.random() * len);//Math.random() 0.0 <= x < 1.0
        }*/

        //Bubble bubble = new Bubble();
        //Select select = new Select();
        //Insert insert = new Insert();
        Quick quick = new Quick();

        Calendar calendar = Calendar.getInstance();
        System.out.println("排序前：" + calendar.getTime());
        //bubble.sort(arr);
        //select.sort(arr);
        //insert.sort(arr);
        quick.sort(0, arr.length -1, arr);
        calendar = Calendar.getInstance();
        System.out.println("排序后：" + calendar.getTime());


        for(int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
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

class Insert {
    public void sort(int[] arr) {
        //将第一个数一个视作有序列表，其余的数视作一个无序列表，依次将无序列表插入有序列表
        for(int i = 1; i < arr.length; i++) {
            int varInsert = arr[i];     //待插入的数
            int indexOrdered = i - 1;   //与varInsert进行比较的数的下标

            //确定varInsert插入有序列表的位置
            while(indexOrdered >= 0 && varInsert < arr[indexOrdered]) {
                //若varInsert小于前一个数，则有序列表向后移动+1，indexOrdered向前移动-1
                arr[indexOrdered + 1] = arr[indexOrdered];
                indexOrdered--;
            }

            //将varInsert插入有序列表
            arr[indexOrdered + 1] = varInsert;
        }
    }
}

class Quick {
    public void sort(int left, int right, int[] arr) {
        //采用递归思想，以数列的中间值为参考值，小于参考值的放在参考值的left，大于参考值的放在参考值的right
        int varLeft = left;
        int varRight = right;
        int varMark = arr[left + (right - left) / 2];   //将数列中的中间值作为参考值
        int temp = 0;

        while (varLeft < varRight) {
            while (arr[varLeft] < varMark) varLeft++;
            while (arr[varRight] > varMark) varRight--;

            if (varLeft >= varRight) break;

            temp = arr[varLeft];
            arr[varLeft] = arr[varRight];
            arr[varRight] = temp;

            if(arr[varLeft] == varMark) --varRight;
            if(arr[varRight] == varMark) ++varLeft;
        }

        if(varLeft == varRight) {
            varLeft++;
            varRight--;
        }

        if(left < varRight) sort(left, varRight, arr);
        if(right > varLeft) sort(varLeft, right, arr);
    }
}