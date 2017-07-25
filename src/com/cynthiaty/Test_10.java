package com.cynthiaty;

import java.util.*;
import java.io.*;
import java.util.Map.*;

import static com.cynthiaty.Test_9.isNumeric;

/**
 * 作者：cynthiaty
 * 功能：集合--使用HashMap实现学生管理系统
 * 时间：2017-07-25
 */
public class Test_10 {
    public static void main(String[] args) {
        // write your code here.

        StuManage stuManage = new StuManage();

        //制作简易菜单，选择要进行的操作
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String operateType = null;

        while (true) {
            System.out.println("请选择您要进行的操作：");
            System.out.println("**************** 学生管理系统 ******************");
            System.out.println("1：表示添加一个新生");
            System.out.println("2：表示按学号查找学生");
            System.out.println("3：表示按学号修改学生的成绩");
            System.out.println("4：表示按学号删除离校学生");
            System.out.println("5：表示按总成绩由高到低进行排序");
            System.out.println("6：表示统计学生的总分平均分");
            System.out.println("7：表示统计单科最低分");
            System.out.println("8：表示统计单科最高分");
            System.out.println("9：表示显示所有的学生信息");
            System.out.println("0：表示退出系统");
            System.out.println("************************************************");

            try {
                operateType = br.readLine();
                while(!isNumeric(operateType)) {
                    System.out.println("无效的操作指令，请重输！");
                    System.out.println("**************** 学生管理系统 ******************");
                    System.out.println("1：表示添加一个新生");
                    System.out.println("2：表示按学号查找学生");
                    System.out.println("3：表示按学号修改学生的成绩");
                    System.out.println("4：表示按学号删除离校学生");
                    System.out.println("5：表示按总成绩由高到低进行排序");
                    System.out.println("6：表示统计学生的总分平均分");
                    System.out.println("7：表示统计单科最低分");
                    System.out.println("8：表示统计单科最高分");
                    System.out.println("9：表示显示所有的学生信息");
                    System.out.println("0：表示退出系统");
                    System.out.println("************************************************");

                    operateType = br.readLine();    //重新获取操作指令
                }

                switch (Integer.parseInt(operateType)) {
                    case 1:
                        System.out.println("请输入学生学号：");
                        String no = br.readLine();
                        System.out.println("请输入学生姓名：");
                        String name = br.readLine();
                        System.out.println("请输入语文成绩：");
                        float chineseGrade = Float.parseFloat(br.readLine());
                        System.out.println("请输入数学成绩：");
                        float mathGrade = Float.parseFloat(br.readLine());
                        System.out.println("请输入英语成绩：");
                        float englishGrade = Float.parseFloat(br.readLine());

                        stuManage.insertStu(new Student(no, name, chineseGrade, mathGrade, englishGrade));
                        break;
                    case 2:
                        System.out.println("请输入要查找的学生学号：");
                        stuManage.findStu(br.readLine());
                        break;
                    case 3:
                        System.out.println("请输入要修改的学生学号：");
                        String markNo = br.readLine();
                        System.out.println("请输入该学生新的语文成绩：");
                        Float newChineseGrade = Float.parseFloat(br.readLine());
                        System.out.println("请输入该学生新的数学成绩：");
                        Float newMathGrade = Float.parseFloat(br.readLine());
                        System.out.println("请输入该学生新的英语成绩：");
                        Float newEnglishGrade = Float.parseFloat(br.readLine());
                        stuManage.updateGrade(markNo, newChineseGrade, newMathGrade, newEnglishGrade);
                        break;
                    case 4:
                        System.out.println("请输入要删除的学生学号：");
                        stuManage.deleteStu(br.readLine());
                        break;
                    case 5:
                        stuManage.sortAllGrade();
                        break;
                    case 6:
                        stuManage.getAverage();
                        break;
                    case 7:
                        stuManage.getMinSingleGrade();
                        break;
                    case 8:
                        stuManage.getMaxSingleGrade();
                        break;
                    case 9:
                        stuManage.showInfo();
                        break;
                    case 0:
                        System.exit(0);     // 0->表示正常退出；-1->表示异常退出
                        break;
                    default:
                        System.out.println("无效的操作指令，请重输！");
                        break;
                }
            }catch (Exception e) {
                e.printStackTrace();
            }finally {

            }
        }
    }
}

class StuManage {
    private Map<String, Student> map = null;

    public StuManage() {
        this.map = new HashMap<>();
    }

    //添加一个新生
    public void insertStu(Student stu) {
        map.put(stu.getNo(), stu);
    }

    //按学号查找学生
    public void findStu(String no) {
        if(map.containsKey(no)) {
            Student tempStu = map.get(no);
            System.out.println("找到该学生，信息如下：" + "\n"
                    + "学号 = " + tempStu.getNo() + "\n"
                    + "姓名 = " + tempStu.getName() + "\n"
                    + "语文成绩 = " + tempStu.getChineseGrade() + "\n"
                    + "数学成绩 = " + tempStu.getMathGrade() + "\n"
                    + "英语成绩 = " + tempStu.getEnglishGrade());
        }
        else {
            System.out.println("没有找到该学生！");
        }
    }

    //按学号修改学生的成绩
    public void updateGrade(String no, float newChinese, float newMath, float newEnglish) {
        if(map.containsKey(no)) {
            Student tempStu = map.get(no);
            tempStu.setChineseGrade(newChinese);
            tempStu.setMathGrade(newMath);
            tempStu.setEnglishGrade(newEnglish);
            map.put(no, tempStu);

            System.out.println("找到该学生，成绩修改成功！");
        }
        else {
            System.out.println("未找到该学生！");
        }
    }

    //按学号删除离校学生
    public void deleteStu(String no) {
        if(map.containsKey(no)) {
            map.remove(no);
            System.out.println("找到该学生，删除成功！");
        }
        else {
            System.out.println("未找到该学生！");
        }
    }

    //按总成绩由高到低进行排序
    public void sortAllGrade() {
        List<Entry<String, Student>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Entry<String, Student>>() {
            public int compare(Entry<String, Student> o1, Entry<String, Student> o2) {
                float tempGrade1 = o1.getValue().getChineseGrade() + o1.getValue().getMathGrade()
                        + o1.getValue().getEnglishGrade();
                float tempGrade2 = o2.getValue().getChineseGrade() + o2.getValue().getMathGrade()
                        + o2.getValue().getEnglishGrade();

                //return Float.compare(tempGrade1, tempGrade2);    //由小到大升序排序
                return Float.compare(tempGrade2, tempGrade1);    //由大到小降序排序
            }
        });

        System.out.println("按总成绩由高到低进行排序，结果如下：");

        for(Map.Entry<String, Student> varMap : list) {
            System.out.println("学号 = " + varMap.getValue().getNo() + "  "
                    + "姓名 = " + varMap.getValue().getName() + "  "
                    + "语文成绩 = " + varMap.getValue().getChineseGrade() + "  "
                    + "数学成绩 = " + varMap.getValue().getMathGrade() + "  "
                    + "英语成绩 = " + varMap.getValue().getEnglishGrade());
        }
    }

    //统计学生的总分平均分
    public void getAverage() {
        float allGrade = 0;
        float averageGrade = 0;

        Iterator it = map.keySet().iterator();
        while(it.hasNext()) {
            String tempNo = (String) it.next();
            allGrade += map.get(tempNo).getChineseGrade()
                    + map.get(tempNo).getMathGrade()
                    + map.get(tempNo).getEnglishGrade();
        }

        averageGrade = allGrade / map.size();
        System.out.println("学生的总分平均分 = " + averageGrade);
    }

    //统计单科最低分
    public void getMinSingleGrade() {
        List<Entry<String, Student>> list = new LinkedList<>(map.entrySet());
        String minChineseNo = list.get(0).getValue().getNo();
        String minMathNo = list.get(0).getValue().getNo();
        String minEnglishNo = list.get(0).getValue().getNo();

        for(int i = 1; i < list.size(); i++) {
            Student tempStu = list.get(i).getValue();

            if(map.get(minChineseNo).getChineseGrade() > tempStu.getChineseGrade()) {
                minChineseNo = tempStu.getNo();
            }
            if(map.get(minMathNo).getMathGrade() > tempStu.getMathGrade()) {
                minMathNo = tempStu.getNo();
            }
            if(map.get(minEnglishNo).getEnglishGrade() > tempStu.getEnglishGrade()) {
                minEnglishNo = tempStu.getNo();
            }
        }

        System.out.println("语文最低分 = " + map.get(minChineseNo).getChineseGrade()
                + ", 该学生信息如下：" + "\n"
                + "学号 = " + map.get(minChineseNo).getNo() + "\n"
                + "姓名 = " + map.get(minChineseNo).getName());
        System.out.println("数学最低分 = " + map.get(minMathNo).getMathGrade()
                + ", 该学生信息如下：" + "\n"
                + "学号 = " + map.get(minMathNo).getNo() + "\n"
                + "姓名 = " + map.get(minMathNo).getName());
        System.out.println("英语最低分 = " + map.get(minEnglishNo).getEnglishGrade()
                + ", 该学生信息如下：" + "\n"
                + "学号 = " + map.get(minEnglishNo).getNo() + "\n"
                + "姓名 = " + map.get(minEnglishNo).getName());
    }

    //统计单科最高分
    public void getMaxSingleGrade() {
        List<Entry<String, Student>> list = new Vector<>(map.entrySet());
        String maxChineseNo = list.get(0).getValue().getNo();
        String maxMathNo = list.get(0).getValue().getNo();
        String maxEnglishNo = list.get(0).getValue().getNo();

        for(int i = 1; i < list.size(); i++) {
            Student tempStu = list.get(i).getValue();

            if(map.get(maxChineseNo).getChineseGrade() < tempStu.getChineseGrade()) {
                maxChineseNo = tempStu.getNo();
            }
            if(map.get(maxMathNo).getMathGrade() < tempStu.getMathGrade()) {
                maxMathNo = tempStu.getNo();
            }
            if(map.get(maxEnglishNo).getEnglishGrade() < tempStu.getEnglishGrade()) {
                maxEnglishNo = tempStu.getNo();
            }
        }

        System.out.println("语文最高分 = " + map.get(maxChineseNo).getChineseGrade()
                + ", 该学生信息如下：" + "\n"
                + "学号 = " + map.get(maxChineseNo).getNo() + "\n"
                + "姓名 = " + map.get(maxChineseNo).getName());
        System.out.println("数学最高分 = " + map.get(maxMathNo).getMathGrade()
                + ", 该学生信息如下：" + "\n"
                + "学号 = " + map.get(maxMathNo).getNo() + "\n"
                + "姓名 = " + map.get(maxMathNo).getName());
        System.out.println("英语最高分 = " + map.get(maxEnglishNo).getEnglishGrade()
                + ", 该学生信息如下：" + "\n"
                + "学号 = " + map.get(maxEnglishNo).getNo() + "\n"
                + "姓名 = " + map.get(maxEnglishNo).getName());
    }

    //显示所有的学生信息
    public void showInfo() {
        System.out.println("学生信息表如下：");

        Iterator it = map.keySet().iterator();  //Iterator迭代器，HashMap借助该迭代器遍历对象
        while(it.hasNext()) {   //boolean，true->iteration中还有key值， false->遍历完成，退出循环
            String tempNo = (String) it.next();
            System.out.println("学号 = " + map.get(tempNo).getNo() + "  "
                    + "姓名 = " + map.get(tempNo).getName() + "  "
                    + "语文成绩 = " + map.get(tempNo).getChineseGrade() + "  "
                    + "数学成绩 = " + map.get(tempNo).getMathGrade() + "  "
                    + "英语成绩 = " + map.get(tempNo).getEnglishGrade());
        }
    }
}

class Student {
    private String no;              //学号
    private String name;            //姓名
    private float chineseGrade;     //语文
    private float mathGrade;        //数学
    private float englishGrade;     //英语

    public Student(String no, String name, float chineseGrade, float mathGrade, float englishGrade) {
        this.no = no;
        this.name = name;
        this.chineseGrade = chineseGrade;
        this.mathGrade = mathGrade;
        this.englishGrade = englishGrade;
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

    public float getChineseGrade() {
        return chineseGrade;
    }

    public void setChineseGrade(float chineseGrade) {
        this.chineseGrade = chineseGrade;
    }

    public float getMathGrade() {
        return mathGrade;
    }

    public void setMathGrade(float mathGrade) {
        this.mathGrade = mathGrade;
    }

    public float getEnglishGrade() {
        return englishGrade;
    }

    public void setEnglishGrade(float englishGrade) {
        this.englishGrade = englishGrade;
    }
}