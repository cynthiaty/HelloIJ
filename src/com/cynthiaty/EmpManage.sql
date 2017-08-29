--�������ݿ�EmpManage
create database EmpManage

--�������ű�dept
create table dept(
deptNo int primary key,--deptNo��Ϊ����
deptName nvarchar(30),
deptLocal nvarchar(30)
)

--ɾ�����ű�dept
--drop table dept;

--����Ա����emp
create table emp(
empNo int primary key,--empNo��Ϊ����
empName nvarchar(30),
empJob nvarchar(30),
empManager int,
empHiredate datetime,
empSal numeric(10, 2),
empComm numeric(10, 2),--����
deptNo int foreign key references dept(deptNo)--emp.deptNo��Ϊ���ָ������dept.deptNo
)

--ɾ��Ա����emp
--drop table emp;

--��Ӳ���
insert into dept values(1, '����', '����')
insert into dept values(2, '�з���', '����')
insert into dept values(3, '���۲�', '����')
insert into dept values(4, '�г���', '����')
select * from dept

--���Ա��
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

--?��ѯָ���У�����*���ң����ڴ桢Ч�ʵͣ�
--?��ѯsmith��нˮ�����������ڲ���
select empSal, empJob, deptNo from emp where empName = 'smith';
--distinct������ѯ�����ȫһ�µ��У�ֻ����һ��
select distinct deptNo, empName from emp;

--?��ѯÿ��Ա�����깤��(нˮ + ����)
--'�깤��'��Ϊ�еı���
select empName, empSal * 13 + isnull(empComm, 0) * 13 as '�깤��' from emp;

--?��ѯ�¹��ʲ�����3000��Ա��
select * from emp where empSal >= 3000;

--?��ѯ1982.1.1�Ժ���ְ��Ա��
select * from emp where empHiredate > '1982-1-1';

--?��ѯ������2000��3000֮���Ա��
select * from emp where empSal >= 2000 and empSal <= 3000;
select * from emp where empSal between 2000 and 3000;

--?ʹ��like����������ģ����ѯ��%��ʾ����Ϸ���0������ַ���_��ʾ����Ϸ��ĵ����ַ�
--?��ѯ�������ַ�Ϊs������Ա�������͹���
select empName, empSal from emp where empName like 's%';

--?��ѯ�����е������ַ�Ϊm������Ա�������͹���
select empName, empSal from emp where empName like '__m%';

--?ʹ��in�����������ѯ
--?��ѯԱ�����Ϊ7566,7879,7934��Ա��
select * from emp where empNo = 7566 or empNo = 7879 or empNo = 7934;
select * from emp where empNo in(7566, 7879, 7934);

--?��ѯû��ֱ���ϼ���Ա��
--null����ֱ����=����ƥ��
select * from emp where empManager is null;

--?��ѯ���ʸ���1000���߸�λΪmanager��Ա����ͬʱ�����������ֵ�����ĸΪj
select * from emp 
where (empSal > 1000 or empJob = 'manager') and empName like 'j%';

--?order by Ĭ��Ϊ��������asc��;order by descΪ��������
--?����ְ���Ⱥ�˳����������
select * from emp order by empHiredate;

--?���ղ��ű�����򡢹��ʽ�������
select * from emp order by deptNo asc, empSal desc;

--?ͳ������Ա������н���������������򣨱���Ҳ��������
select empName, (empSal + ISNULL(empComm, 0)) * 13 as '�깤��' 
from emp 
order by '�깤��';

--?��ĸ��Ӳ�ѯ�����ݷ��飺min()��max()��avg()��count()��sum()
--?��ѯԱ���е���߹��ʺ���͹���
select empName, empSal from emp where empSal = 
(select max(empSal) from emp);
select empName, empSal from emp where empSal = 
(select MIN(empSal) from emp);

--?��ѯƽ�����ʺ��ܹ���
select avg(empSal) as 'ƽ������', sum(empSal) as '�ܹ���' from emp;

--?��ѯ����ƽ�����ʵ�Ա�������͹���, �������ʽ�������
select empName, empSal from emp 
where empSal > (select avg(empSal) from emp) 
order by empSal desc;

--?ͳ�ƹ��ж�����Ա��
select COUNT(*) as 'Ա������' from emp;

--?group by:�Բ�ѯ�Ľ�����з���ͳ�ƣ�having�Ӿ�:�������Ʒ�����ʾ���
--?��ѯÿ�����ŵ�ƽ�����ʺ���߹���
select AVG(empSal), MAX(empSal), deptNo from emp group by deptNo;

--?��ѯÿ�����ŵ�ÿ�ָ�λ��ƽ�����ʺ���߹��ʣ��������ź���������
select AVG(empSal), MAX(empSal), empJob, deptNo from emp 
group by deptNo, empJob 
order by deptNo;

--having��group by���ʹ�ã��ǶԷ����Ľ�������ٴ�ɸѡ
--order byһ�㶼�����������
--sql����ִ��˳����������������
--?��ѯƽ�����ʸ���2000�Ĳ��źź͸ò��ŵ�ƽ�����ʣ�����ƽ�����ʽ�����������
select AVG(empSal), deptNo from emp 
group by deptNo 
having AVG(empSal) > 2000 
order by AVG(empSal);

--����ѯ��ָ�����������������ϵı����ͼ�Ĳ�ѯ
--?��ѯ���۲��ŵ�λ�ú͸ò���Ա��������
select dept.deptLocal, emp.empName 
from dept, emp 
where dept.deptName = '���۲�' and dept.deptNo = emp.deptNo;

--?��ѯԱ�����������ʡ������ڲ��ŵ����֣�������������
select e.empName, e.empSal, d.deptName 
from emp e, dept d
where e.deptNo = d.deptNo
order by d.deptNo;

--?��ѯ���ź�Ϊ2��Ա�����������ʼ���������
select e.empName, e.empSal, d.deptName 
from emp e, dept d
where d.deptNo = 2 and e.deptNo = d.deptNo;

--�����ӣ�ָ��ͬһ�ű�����Ӳ�ѯ
--?��ѯford�������ϼ�������
--��������emp����Ϊ2�ű�, �ֱ�Ϊclerk��manager
select clerk.empName ְԱ, manager.empName �ϼ� 
from emp clerk, emp manager
where clerk.empName = 'ford' and clerk.empManager = manager.empNo;

--�����ӣ�ֻ��ʾƥ���ϵ�����
--?��ѯ��˾ÿ��Ա���Լ������ϼ�������
select clerk.empName ְԱ, manager.empName �ϼ� 
from emp clerk, emp manager 
where clerk.empManager = manager.empNo;

select clerk.empName ְԱ, manager.empName �ϼ� 
from emp clerk inner join emp manager 
on clerk.empManager = manager.empNo;

--�������ӣ�ָ��߱�ļ�¼ȫ����ʾ�����û��ƥ��ļ�¼����ʾnull
--�������ӣ�ָ�ұ߱�ļ�¼ȫ����ʾ�����û��ƥ��ļ�¼����ʾnull
--?��ѯ��˾ÿ��Ա���Լ������ϼ������֣�����û���ϼ���Ա����
select clerk.empName ְԱ, manager.empName �ϼ� 
from emp clerk left join emp manager 
on clerk.empManager = manager.empNo;

--?�Ӳ�ѯ��ָǶ��������sql����е�select��䣬Ҳ��Ƕ�ײ�ѯ��
--?��ѯ��smith��ͬһ���ŵ�Ա����Ϣ
select * from emp where deptNo = 
(select deptNo from emp where empName = 'smith') and empName != 'smith';

--?��ѯ�Ͳ��ź�Ϊ1�Ĺ�����λ��ͬ��Ա������Ϣ
--in����ƥ������ѯ���
select * from emp where empJob in 
(select distinct empJob from emp where deptNo = 1) and deptNo != 1;

--?��from�Ӿ���ʹ���Ӳ�ѯ�����Ӳ�ѯ�Ľ������һ����ʱ��ʹ��ʱ����ȡ����
--?��ѯ���ڲ���ƽ�����ʵ�Ա�������������ʡ����źš��Լ����ŵ�ƽ������
select emp.empName, emp.empSal, emp.deptNo, temp.myAvg    
from emp, (select AVG(empSal) myAvg, deptNo from emp group by deptNo) temp
where emp.deptNo = temp.deptNo and emp.empSal > temp.myAvg;

--��ҳ��ѯ
--top num��ȡ���б���ǰ���num����¼
--?��ѯнˮ����ǰ���5~��9λ��Ա����Ϣ
select top 5 * from emp 
where empNo not in (select top 4 empNo from emp order by empSal desc)
order by empSal desc;

--?ɾ��һ�ű��е��ظ���¼���ϸ񽨱�Ļ������������������Ϊ�����ǲ������ظ��ģ�
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
--insert into cat select * from cat;--������2���ݴη�����
--identity(1,1)��������������һ��������������

select * from cat;

--���ȣ�ʹ��distinct��Ψһ��¼������ʱ��temp_table
select distinct * into temp_table from cat;
--��Σ����cat��ļ�¼
delete from cat;
--����������ʱ��temp_table�еļ�¼������cat����
insert into cat select * from temp_table;
--���ɾ����ʱ��temp_table
drop table temp_table;