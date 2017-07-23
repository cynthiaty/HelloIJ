package com.cynthiaty;

/**
 * 作者：cynthiaty
 * 功能：查找--二分查找法
 * 时间：2017-07-23
 */
public class Test_8 {
    public static void main(String[] args) {
        // write your code here.

        int[] arr = {-2, -1, 5, 8, 9};

        BinaryFind find = new BinaryFind();
        find.find(0, arr.length - 1, 8, arr);
    }
}

class BinaryFind {
    //使用二分法查找，该数列必须为有序数列
    public void find(int left, int right, int valFind, int[] arr) {
        //找到数列的中间值
        int indexMiddle = (left + right) / 2;
        int valMark = arr[indexMiddle];

        if(left <= right) {
            if(valFind < valMark) {
                //在中间值的左边进行查找
                find(left, indexMiddle - 1, valFind, arr);
            }
            else if(valFind > valMark) {
                //在中间值的右边进行查找
                find(indexMiddle + 1, right, valFind, arr);
            }
            else if(valFind == valMark) {
                System.out.println("要查找的数的下标为: index = " + indexMiddle);
            }
        }
        else {
            System.out.println("没有找到指定的数！");
        }
    }
}
