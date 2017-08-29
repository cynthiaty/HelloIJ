--创建数据库EmpManage
create database EmpManage

--创建部门表dept
create table dept(
deptNo int primary key,--deptNo作为主键
deptName nvarchar(30),
deptLocal nvarchar(30)
)

--删除部门表dept
--drop table dept;

--创建员工表emp
create table emp(
empNo int primary key,--empNo作为主键
empName nvarchar(30),
empJob nvarchar(30),
empManager int,
empHiredate datetime,
empSal numeric(10, 2),
empComm numeric(10, 2),--奖金
deptNo int foreign key references dept(deptNo)--emp.deptNo作为外键指向主键dept.deptNo
)

--删除员工表emp
--drop table emp;

--添加部门
insert into dept values(1, '财务部', '北京')
insert into dept values(2, '研发部', '北京')
insert into dept values(3, '销售部', '深圳')
insert into dept values(4, '市场部', '深圳')
select * from dept

--添加员工
insert into emp values(
7369, 'smith', 'clerk', 7902, '1980-12-17', 800, null, 2)
insert into emp values(
7499, 'allen', 'salsman', 7698, '1981-02-20', 1600, 300, 3)
insert into emp values(
7521, 'ward', 'salsman', 7698, '1981-02-22', 1250, 500, 3)
insert into emp values(
7566, 'jones', 'manager', 7839, '1981-04-02', 2975, null, 2)
insert into emp values(
7654, 'martin', 'salsman', 7698, '1981-09-28', 1250, 1400, 3)
insert into emp values(
7698, 'blake', 'manager', 7839, '1981-05-01', 2850, null, 3)
insert into emp values(
7782, 'clark', 'manager', 7839, '1981-06-09', 2450, null, 1)
insert into emp values(
7788, 'scott', 'analyst', 7566, '1987-04-19', 3000, null, 2)
insert into emp values(
7839, 'king', 'president', null, '1980-11-17', 5000, null, 1)
insert into emp values(
7844, 'turner', 'salsman', 7698, '1981-09-08', 1500, 0, 3)
insert into emp values(
7876, 'adams', 'clerk', 7788, '1987-05-23', 1100, null, 2)
insert into emp values(
7900, 'james', 'clerk', 7698, '1981-12-03', 950, null, 3)
insert into emp values(
7902, 'ford', 'analyst', 7566, '1981-12-03', 3000, null, 2)
insert into emp values(
7934, 'miller', 'clerk', 7782, '1982-01-23', 1300, null, 1)
select * from emp

--?查询指定列（慎用*查找，耗内存、效率低）
--?查询smith的薪水、工作、所在部门
select empSal, empJob, deptNo from emp where empName = 'smith';
--distinct消除查询结果完全一致的行，只保留一行
select distinct deptNo, empName from emp;

--?查询每个员工的年工资(薪水 + 奖金)
--'年工资'作为列的别名
select empName, empSal * 13 + isnull(empComm, 0) * 13 as '年工资' from emp;

--?查询月工资不低于3000的员工
select * from emp where empSal >= 3000;

--?查询1982.1.1以后入职的员工
select * from emp where empHiredate > '1982-1-1';

--?查询工资在2000到3000之间的员工
select * from emp where empSal >= 2000 and empSal <= 3000;
select * from emp where empSal between 2000 and 3000;

--?使用like操作符进行模糊查询：%表示任意合法的0到多个字符；_表示任意合法的单个字符
--?查询名字首字符为s的所有员工姓名和工资
select empName, empSal from emp where empName like 's%';

--?查询名字中第三个字符为m的所有员工姓名和工资
select empName, empSal from emp where empName like '__m%';

--?使用in进行批量查查询
--?查询员工编号为7566,7879,7934的员工
select * from emp where empNo = 7566 or empNo = 7879 or empNo = 7934;
select * from emp where empNo in(7566, 7879, 7934);

--?查询没有直属上级的员工
--null不可直接用=进行匹配
select * from emp where empManager is null;

--?查询工资高于1000或者岗位为manager的员工，同时满足他们名字的首字母为j
select * from emp 
where (empSal > 1000 or empJob = 'manager') and empName like 'j%';

--?order by 默认为升序排序（asc）;order by desc为降序排序
--?按入职的先后顺序排序（升序）
select * from emp order by empHiredate;

--?按照部门编号升序、工资降序排序
select * from emp order by deptNo asc, empSal desc;

--?统计所有员工的年薪，并按照升序排序（别名也可以排序）
select empName, (empSal + ISNULL(empComm, 0)) * 13 as '年工资' 
from emp 
order by '年工资';

--?表的复杂查询，数据分组：min()、max()、avg()、count()、sum()
--?查询员工中的最高工资和最低工资
select empName, empSal from emp where empSal = 
(select max(empSal) from emp);
select empName, empSal from emp where empSal = 
(select MIN(empSal) from emp);

--?查询平均工资和总工资
select avg(empSal) as '平均工资', sum(empSal) as '总工资' from emp;

--?查询高于平均工资的员工姓名和工资, 并按工资降序排序
select empName, empSal from emp 
where empSal > (select avg(empSal) from emp) 
order by empSal desc;

--?统计共有多少名员工
select COUNT(*) as '员工总数' from emp;

--?group by:对查询的结果进行分组统计；having子句:用于限制分组显示结果
--?查询每个部门的平均工资和最高工资
select AVG(empSal), MAX(empSal), deptNo from emp group by deptNo;

--?查询每个部门的每种岗位的平均工资和最高工资，并按部门号升序排序
select AVG(empSal), MAX(empSal), empJob, deptNo from emp 
group by deptNo, empJob 
order by deptNo;

--having和group by配合使用，是对分组后的结果进行再次筛选
--order by一般都是在语句的最后
--sql语句的执行顺序往往是自右向左
--?查询平均工资高于2000的部门号和该部门的平均工资，并按平均工资进行升序排序
select AVG(empSal), deptNo from emp 
group by deptNo 
having AVG(empSal) > 2000 
order by AVG(empSal);

--多表查询：指基于两个或两个以上的表或视图的查询
--?查询销售部门的位置和该部门员工的姓名
select dept.deptLocal, emp.empName 
from dept, emp 
where dept.deptName = '销售部' and dept.deptNo = emp.deptNo;

--?查询员工姓名、工资、及所在部门的名字，并按部门排序
select e.empName, e.empSal, d.deptName 
from emp e, dept d
where e.deptNo = d.deptNo
order by d.deptNo;

--?查询部门号为2的员工姓名、工资及部门名称
select e.empName, e.empSal, d.deptName 
from emp e, dept d
where d.deptNo = 2 and e.deptNo = d.deptNo;

--自连接：指在同一张表的连接查询
--?查询ford及他的上级的名字
--分析：将emp表视为2张表, 分别为clerk，manager
select clerk.empName 职员, manager.empName 上级 
from emp clerk, emp manager
where clerk.empName = 'ford' and clerk.empManager = manager.empNo;

--内连接：只显示匹配上的数据
--?查询公司每个员工以及他的上级的名字
select clerk.empName 职员, manager.empName 上级 
from emp clerk, emp manager 
where clerk.empManager = manager.empNo;

select clerk.empName 职员, manager.empName 上级 
from emp clerk inner join emp manager 
on clerk.empManager = manager.empNo;

--左外连接：指左边表的记录全部显示，如果没有匹配的记录就显示null
--右外连接：指右边表的记录全部显示，如果没有匹配的记录就显示null
--?查询公司每个员工以及他的上级的名字（包括没有上级的员工）
select clerk.empName 职员, manager.empName 上级 
from emp clerk left join emp manager 
on clerk.empManager = manager.empNo;

--?子查询：指嵌套在其他sql语句中的select语句，也叫嵌套查询。
--?查询与smith在同一部门的员工信息
select * from emp where deptNo = 
(select deptNo from emp where empName = 'smith') and empName != 'smith';

--?查询和部门号为1的工作岗位相同的员工的信息
--in用于匹配多个查询结果
select * from emp where empJob in 
(select distinct empJob from emp where deptNo = 1) and deptNo != 1;

--?在from子句中使用子查询：将子查询的结果视作一个临时表，使用时必须取别名
--?查询高于部门平均工资的员工的姓名、工资、部门号、以及部门的平均工资
select emp.empName, emp.empSal, emp.deptNo, temp.myAvg    
from emp, (select AVG(empSal) myAvg, deptNo from emp group by deptNo) temp
where emp.deptNo = temp.deptNo and emp.empSal > temp.myAvg;

--分页查询
--top num：取出列表最前面的num条记录
--?查询薪水排在前面第5~第9位的员工信息
select top 5 * from emp 
where empNo not in (select top 4 empNo from emp order by empSal desc)
order by empSal desc;

--?删除一张表中的重复记录（严格建表的话不存在这种情况，因为主键是不允许重复的）
create table cat(
catId integer,
catName nvarchar(30)
)

insert into cat values(1, 'red');
insert into cat values(2, 'orange');
insert into cat values(3, 'yellow');
insert into cat values(4, 'green');
insert into cat values(5, 'blue');
insert into cat values(6, 'purple');
--insert into cat select * from cat;--数据以2的幂次方增长
--identity(1,1)：代表自增长，一般用来修饰主键

select * from cat;

--首先，使用distinct将唯一记录放入临时表temp_table
select distinct * into temp_table from cat;
--其次，清空cat表的记录
delete from cat;
--第三，将临时表temp_table中的记录拷贝到cat表中
insert into cat select * from temp_table;
--最后，删除临时表temp_table
drop table temp_table;