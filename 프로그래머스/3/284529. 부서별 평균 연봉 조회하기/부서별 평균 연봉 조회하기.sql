-- 코드를 작성해주세요
select d.DEPT_ID, d.DEPT_NAME_EN, round(avg(e.SAL),0) AS AVG_SAL
from HR_DEPARTMENT d join HR_EMPLOYEES e on (d.DEPT_ID = e.DEPT_ID)
where (d.DEPT_ID, d.DEPT_NAME_EN) in (
    select DEPT_ID, DEPT_NAME_EN
    from HR_DEPARTMENT
    group by DEPT_ID)
group by d.DEPT_ID
order by AVG_SAL desc;