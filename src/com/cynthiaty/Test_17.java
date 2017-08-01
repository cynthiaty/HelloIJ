package com.cynthiaty;

import java.net.*;
import java.io.*;

/**
 * 作者：cynthiaty
 * 功能：Socket网络编程--半双工（服务端）
 * 时间：2017-07-31
 */
public class Test_17 {
    public static void main(String[] args) {
        // write your code here.

        Server server = new Server();
    }
}

class Server {
    Socket socket = null;

    //读取客户端发来的数据
    InputStreamReader isr = null;
    BufferedReader br = null;

    //从控制台输入信息回送给客户端
    PrintWriter pw = null;
    InputStreamReader isr2 = null;
    BufferedReader br2 = null;

    public Server() {
        try {
            //创建9999端口监听服务
            ServerSocket ss = new ServerSocket(9999);
            System.out.println("服务器正在9999端口监听。。。");

            //阻塞等待客户端主动连接
            socket = ss.accept();
            System.out.println("客户端已连接！");

            while (true) {
                //读取客户端发送的数据
                isr = new InputStreamReader(socket.getInputStream());
                br = new BufferedReader(isr);
                String str = br.readLine();
                System.out.println("客户端发送：" + str);
                if(str.equals("bye")) {
                    System.out.println("通话结束，关闭连接！");
                    socket.close();
                    break;
                }

                //从控制台输入信息回送给客户端，true->代表即时刷新
                pw = new PrintWriter(socket.getOutputStream(), true);
                isr2 = new InputStreamReader(System.in);
                br2 = new BufferedReader(isr2);
                System.out.println("服务器说：");
                String str2 = br2.readLine();
                pw.println(str2);
                if(str2.equals("bye")) {
                    System.out.println("通话结束，关闭连接！");
                    socket.close();
                    break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(socket != null) socket.close();
                if(pw != null) pw.close();
                if(br != null) br.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
