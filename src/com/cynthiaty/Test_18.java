package com.cynthiaty;

import java.net.*;
import java.io.*;

/**
 * 作者：cynthiaty
 * 功能：Socket网络编程--半双工（客户端）
 * 时间：2017-07-31
 */
public class Test_18 {
    public static void main(String[] args) {
        // write your code here.

        Client client = new Client();
    }
}

class Client {
    Socket socket = null;

    //从控制台输入信息发送给服务端
    PrintWriter pw = null;
    InputStreamReader isr = null;
    BufferedReader br = null;

    //读取服务端回送的数据
    InputStreamReader isr2 = null;
    BufferedReader br2 = null;

    public Client() {
        try {
            //主动向服务端发送连接请求
            socket = new Socket("127.0.0.1", 9999);

            while (true) {
                //向服务端发送数据，true->代表即时刷新
                pw = new PrintWriter(socket.getOutputStream(), true);
                isr = new InputStreamReader(System.in);
                br = new BufferedReader(isr);
                System.out.println("客户端说：");
                String str = br.readLine();
                pw.println(str);
                if(str.equals("bye")) {
                    System.out.println("通话结束，关闭连接！");
                    socket.close();
                    break;
                }

                //读取服务端回送的数据
                isr2 = new InputStreamReader(socket.getInputStream());
                br2 = new BufferedReader(isr2);
                String str2 = br2.readLine();
                System.out.println("服务器说：" + str2);
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
                if(br2 != null) br2.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}