select dname from dept;


select dname 
from dept
join emp
on dept.DEPTNO = emp.DEPTNO
group by dname
having count(emp.EMPNO) > 5;

select comm from emp;

select ename from emp where ename like 's%';