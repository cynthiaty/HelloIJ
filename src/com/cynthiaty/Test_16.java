package com.cynthiaty;

import java.io.*;

/**
 * 作者：cynthiaty
 * 功能：文件编程--缓冲字符流（操作对象为String，效率较字符流更高）
 * 时间：2017-07-27
 */
public class Test_16 {
    public static void main(String[] args) {
        // write your code here.

        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            FileReader fr = new FileReader("F:\\Java\\Test\\Hello.java");
            FileWriter fw = new FileWriter("F:\\Java\\Test\\Hello.bat.java");
            br = new BufferedReader(fr);    //不能直接打开文件，需要借助FileReader
            bw = new BufferedWriter(fw);
            String buf = null;

            //循环读取文件，br.readLine()每次读取一行，但读取任何\r\n
            while((buf = br.readLine()) != null) {
                System.out.println(buf);
                bw.write(buf + "\r\n");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(br != null) br.close();
                if(bw != null) bw.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
