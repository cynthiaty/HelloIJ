package com.cynthiaty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 作者：cynthiaty
 * 功能：集合--使用ArrayList实现员工管理系统
 * 时间：2017-07-24
 */
public class Test_9 {
    public static void main(String[] args) throws Exception {
        // write your code here.

        EmpManage empManage = new EmpManage();

        //制作简易菜单，选择要进行的操作
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String operateType = null;

        while (true) {
            System.out.println("请选择您要进行的操作：");
            System.out.println("************************************************");
            System.out.println("1：表示添加一个新员工");
            System.out.println("2：表示按员工编号查找指定员工");
            System.out.println("3：表示按员工编号调整指定员工的薪资");
            System.out.println("4：表示按员工编号删除指定员工");
            System.out.println("5：表示按薪水由高到低进行排序");
            System.out.println("6：表示统计员工的平均薪资");
            System.out.println("7：表示统计员工的最低薪水");
            System.out.println("8：表示统计员工的最高薪水");
            System.out.println("9：表示显示所有的员工信息");
            System.out.println("0：表示退出系统");
            System.out.println("************************************************");

            operateType = br.readLine();
            while(!isNumeric(operateType)) {
                System.out.println("无效的操作指令，请重输！");
                System.out.println("************************************************");
                System.out.println("1：表示添加一个新员工");
                System.out.println("2：表示按员工编号查找指定员工");
                System.out.println("3：表示按员工编号调整指定员工的薪资");
                System.out.println("4：表示按员工编号删除指定员工");
                System.out.println("5：表示按薪水由高到低进行排序");
                System.out.println("6：表示统计员工的平均薪资");
                System.out.println("7：表示统计员工的最低薪水");
                System.out.println("8：表示统计员工的最高薪水");
                System.out.println("9：表示显示所有的员工信息");
                System.out.println("0：表示退出系统");
                System.out.println("************************************************");

                operateType = br.readLine();    //重新获取操作指令
            }

            switch (Integer.parseInt(operateType)) {
                case 1:
                    System.out.println("请输入员工编号：");
                    String no = br.readLine();
                    System.out.println("请输入员工姓名：");
                    String name = br.readLine();
                    System.out.println("请输入员工薪水：");
                    float sal = Float.parseFloat(br.readLine());

                    empManage.insertEmp(new Employee(no, name, sal));
                    break;
                case 2:
                    System.out.println("请输入要查找的员工编号：");
                    empManage.findEmp(br.readLine());
                    break;
                case 3:
                    System.out.println("请输入要调整薪资的员工编号：");
                    String markNo = br.readLine();
                    System.out.println("请输入该员工新的薪资：");
                    Float newSal = Float.parseFloat(br.readLine());
                    empManage.updateSal(markNo, newSal);
                    break;
                case 4:
                    System.out.println("请输入要删除的员工编号：");
                    empManage.deleteEmp(br.readLine());
                    break;
                case 5:
                    empManage.sortSal();
                    break;
                case 6:
                    empManage.getAverage();
                    break;
                case 7:
                    empManage.getMinSal();
                    break;
                case 8:
                    empManage.getMaxSal();
                    break;
                case 9:
                    empManage.showInfo();
                    break;
                case 0:
                    System.exit(0);     // 0->表示正常退出；-1->表示异常退出
                    break;
                default:
                    System.out.println("无效的操作指令，请重输！");
                    break;
            }
        }
    }

    //判断字符串是否为数字
    public static boolean isNumeric(String str){
        for (int i = 0; i < str.length(); i++){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}

//雇员管理类
class EmpManage {
    private ArrayList al = null;

    public EmpManage() {
        this.al = new ArrayList();
    }

    //添加新员工
    public void insertEmp(Employee emp) {
        al.add(emp);
    }

    //按编号查找指定员工
    public void findEmp(String no) {
        for(int i = 0; i < al.size(); i++) {
            Employee tempEmp = (Employee)al.get(i);
            if(no.equals(tempEmp.getNo())) {
                System.out.println("找到该员工，信息如下：" + "\n"
                        + "编号 = " + tempEmp.getNo() + "\n"
                        + "姓名 = " + tempEmp.getName() + "\n"
                        + "薪水 = " + tempEmp.getSal());
                break;
            }
        }
    }

    //调整员工薪资
    public void updateSal(String no, float newSal) {
        for(int i = 0; i < al.size(); i++) {
            Employee tempEmp = (Employee)al.get(i);
            if(no.equals(tempEmp.getNo())) {
                tempEmp.setSal(newSal);
                break;
            }
        }
    }

    //员工离职，从管理系统删除
    public void deleteEmp(String no) {
        for(int i = 0; i < al.size(); i++) {
            Employee tempEmp = (Employee)al.get(i);
            if(no.equals(tempEmp.getNo())) {
                al.remove(tempEmp);
                break;
            }
        }
    }

    //员工按薪水由高到低排序
    public void sortSal() {
        //选择排序，假设第一个员工是薪水最高的，依次比较
        for(int i = 0; i < al.size() - 1; i++) {
            Employee maxSalEmp = (Employee) al.get(i);
            int maxIndex = i;

            for(int j = i + 1; j < al.size(); j++) {
                if(maxSalEmp.getSal() < ((Employee)al.get(j)).getSal()) {
                    maxSalEmp = (Employee)al.get(j);
                    maxIndex = j;
                }
            }

            al.set(maxIndex, al.get(i));
            al.set(i, maxSalEmp);
        }
    }

    //统计员工的平均工资
    public void getAverage() {
        float allSal = 0;
        float salAverage = 0;

        for(int i = 0; i < al.size(); i++) {
            Employee tempEmp = (Employee)al.get(i);
            allSal += tempEmp.getSal();
        }
        salAverage = allSal / al.size();
        System.out.println("员工的平均薪水为：salAverage = " + salAverage);
    }

    //统计员工的最低工资
    public void getMinSal() {
        Employee minSalEmp = (Employee) al.get(0);

        for(int i = 1; i < al.size(); i++) {
            if(minSalEmp.getSal() > ((Employee)al.get(i)).getSal()) {
                minSalEmp = (Employee)al.get(i);
            }
        }

        System.out.println("找到薪水最低的员工，信息如下：" + "\n"
                + "编号 = " + minSalEmp.getNo() + "\n"
                + "姓名 = " + minSalEmp.getName() + "\n"
                + "薪水 = " + minSalEmp.getSal());
    }

    //统计员工的最高工资
    public void getMaxSal() {
        Employee maxSalEmp = (Employee) al.get(0);

        for(int i = 1; i < al.size(); i++) {
            if(maxSalEmp.getSal() < ((Employee)al.get(i)).getSal()) {
                maxSalEmp = (Employee)al.get(i);
            }
        }

        System.out.println("找到薪水最高的员工，信息如下：" + "\n"
                + "编号 = " + maxSalEmp.getNo() + "\n"
                + "姓名 = " + maxSalEmp.getName() + "\n"
                + "薪水 = " + maxSalEmp.getSal());
    }

    //遍历员工信息
    public void showInfo() {
        System.out.println("员工信息表如下：");

        for(int i = 0; i < al.size(); i++) {
            Employee tempEmp = (Employee)al.get(i);
            System.out.println("编号 = " + tempEmp.getNo() + "  "
                    + "姓名 = " + tempEmp.getName() + "  "
                    + "薪水 = " + tempEmp.getSal());
        }
    }
}

//雇员类
class Employee {
    private String no;
    private String name;
    private float sal;

    public Employee() {
    }

    public Employee(String no, String name, float sal) {
        this.no = no;
        this.name = name;
        this.sal = sal;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSal() {
        return sal;
    }

    public void setSal(float sal) {
        this.sal = sal;
    }
}