package com.cynthiaty;

import java.io.*;

/**
 * 作者：cynthiaty
 * 功能：文件编程--字符流实现文件读写操作（只能操作文本文件）
 */
public class Test_15 {
    public static void main(String[] args) {
        // write your code here.

        File file1 = new File("F:\\Java\\Test\\shine.txt");
        File file2 = new File("F:\\Java\\Test\\shine.bat.txt");
        FileReader fr = null;
        FileWriter fw = null;

        try {
            fr = new FileReader(file1);     //File没有读写的能力，所以借助FileReader实现
            fw = new FileWriter(file2);
            char[] buff = new char[1024];   //定义一个字符数组
            int n = 0;                      //记录实际读取到的字符数

            while ((n = fr.read(buff)) != -1) {
                String str = new String(buff, 0, n);
                System.out.println(str);

                fw.write(buff, 0, n);   //写入文件
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(fr != null) fr.close();  //关闭文件流
                if(fw != null) fw.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
