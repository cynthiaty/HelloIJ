package com.cynthiaty;

/**
 * 作者：cynthiaty
 * 功能：多线程编程--线程同步机制（解决线程并发引发的安全问题）
 * 时间：2017-07-26
 */
public class Test_12 {
    public static void main(String[] args) {
        // write your code here.

        TicketWindows tw = new TicketWindows();

        //一个线程对象只能启用一个线程
        //即线程的start()方法只能调用一次，否则Exception
        Thread thread1 = new Thread(tw);
        Thread thread2 = new Thread(tw);
        Thread thread3 = new Thread(tw);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class TicketWindows implements Runnable {
    //电影院有3个售票窗口，共卖出2000张电影票
    private int n = 200;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            }catch (Exception e) {
                e.printStackTrace();
            }

            //synchronized (this) ->同步代码块，保证代码的原子性
            //this对象为java任意类型(Object)；Object对象的状态也称为对象锁（1->空闲， 0->占用）
            synchronized (this) {
                if(n > 0) {
                    System.out.println("当前正在售出第 " + n + " 张票！");
                    n--;
                }
                else {
                    break;
                }
            }
        }
    }
}