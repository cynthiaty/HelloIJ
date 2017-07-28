package com.cynthiaty;

import java.io.*;

/**
 * 作者：cynthiaty
 * 功能：文件编程--文件的创建（文件夹是一种特殊的文件）
 * 时间：2017-07-27
 */
public class Test_13 {
    public static void main(String[] args) {
        // write your code here.

        String path1 = "F:\\Java\\Test\\dir";
        String path2 = "\\Hello.java";
        File file = new File(path1);  //创建一个文件对象

        //创建文件夹
        if(!file.isDirectory()) {
            //文件夹不存在，创建一个文件夹
            file.mkdir();
        }
        else {
            //文件夹已存在，列出该文件夹下的文件列表
            File[] listFiles = file.listFiles();
            for(int i = 0; i < listFiles.length; i++) {
                System.out.println(listFiles[i].getName());
            }
        }

        //创建文件
        path1 += path2;
        System.out.println("path1 = " + path1);
        file = new File(path1);
        if(!file.exists()) {
            //文件不存在，可以创建新文件
            try {
                file.createNewFile();
            }catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("文件不存在, 可以创建！");
            System.out.println("文件名 = " + file.getName() + "\n"
                    + "文件的绝对路径 = " + file.getAbsolutePath() + "\n"
                    + "文件大小 = " + file.length() + " 字节");
        }
        else {
            System.out.println("文件已存在, 不能创建！");
            System.out.println("文件名 = " + file.getName() + "\n"
                    + "文件的绝对路径 = " + file.getAbsolutePath() + "\n"
                    + "文件大小 = " + file.length() + " 字节");
        }
    }
}
