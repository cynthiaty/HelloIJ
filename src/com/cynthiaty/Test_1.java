package com.cynthiaty;

/**
 * 作者：cynthiaty
 * 功能：丢手绢游戏
 * 时间：2017-06-02
 */
public class Test_1 {   //一个文件中，有且只有一个public类

    public static void main(String[] args) {
        // write your code here

        CycleLink cycleLink = new CycleLink();
        cycleLink.setLen(5);
        cycleLink.createLink();
        cycleLink.setK(2);
        cycleLink.setM(3);
        cycleLink.showLink();
        cycleLink.play();
    }
}

class Child {
    protected int no;                 //小朋友编号
    protected Child nextChild = null; //指向下一个小朋友

    public Child(int no) {
        this.no = no;
    }
}

class CycleLink {
    private int len;                //链表长度
    private Child firstChild = null;//创建指向链表头的引用
    private Child temp = null;      //一个临时引用
    private int k = 0;              //开始丢手绢的人
    private int m = 0;              //报数阈值

    public CycleLink() {

    }

    //指定丢手绢游戏的小朋友个数
    public void setLen(int len) {
        this.len = len;
    }

    //指定开始丢手绢的小朋友，从1开始报数
    public void setK(int k) {
        this.k = k;
    }

    //指定每次报数为m的小朋友拿到手绢并out
    public void setM(int m) {
        this.m = m;
    }

    //进行丢手绢游戏
    public void play() {
        Child temp = this.firstChild;
        Child temp2 = this.firstChild;

        //1、找到开始丢手绢的小朋友k
        for (int i = 1; i < this.k; i++) {
            temp = temp.nextChild;
        }

        System.out.println("依次out的小朋友编号：");
        while (this.len != this.m - 1) {
            //2、手绢传递到报数为m的小朋友
            for (int j = 1; j < this.m; j++) {
                if (j == this.m - 1) {
                    temp2 = temp;   //找到要out的小朋友的前一个小朋友
                }
                temp = temp.nextChild;
            }

            //3、该小朋友out，游戏从out的小朋友的下一个小朋友继续
            System.out.println(temp.no);
            temp2.nextChild = temp.nextChild;
            temp = temp.nextChild;
            this.len--;
        }

        //4、游戏结束，show 最终胜出的小朋友
        System.out.println("游戏胜出的小朋友编号：");
        do {
            System.out.println(temp.no);
            temp = temp.nextChild;
            this.len--;
        }while (this.len != 0);

    }

    //初始化环形链表
    public void createLink() {
        for (int i = 1; i <= this.len; i++) {

            if (i == 1) {
                Child child = new Child(i);
                this.firstChild = child;
                temp = child;
            }
            else if (i == this.len) {
                Child child = new Child(i);
                temp.nextChild = child;
                temp = child;
                temp.nextChild = this.firstChild;   //指向链表头
            }
            else {
                Child child = new Child(i);
                temp.nextChild = child;
                temp = child;
            }
        }
    }

    //打印环形链表
    public void showLink() {
        Child temp = this.firstChild;
        do {
            System.out.println("小朋友编号：" + temp.no);
            temp = temp.nextChild;
        }while (temp != this.firstChild);
    }
}
