package com.cynthiaty;

import java.io.*;

/**
 * 作者：cynthiaty
 * 功能：文件编程--字节流实现文件读写操作（可以操作任何文件：二进制文件、文本文件）
 * 时间：2017-07-27
 */
public class Test_14 {
    public static void main(String[] args) {
        // write your code here.

        //拷贝照片（二进制文件只能用字节流读取）
        File file1 = new File("F:\\Java\\Test\\flower.jpg");
        File file2 = new File("F:\\Java\\Test\\flower.bat.jpg");
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(file1);   //File没有读写的能力，所以借助InputStream实现
            fos = new FileOutputStream(file2);
            byte[] bytes = new byte[1024];      //定义一个字节数组
            int n = 0;                          //实际读取到的字节数
            while ((n = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    if (fis != null) fis.close();
                    if (fos != null) fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
