-- 코드를 작성해주세요
select e.emp_no, e.emp_name,
    case 
        when avg(g.score) >= 96 then 'S'
        when avg(g.score) >= 90 then 'A'
        when avg(g.score) >= 80 then 'B'
        else 'C'
    end as GRADE,
    case
        when avg(g.score) >= 96 then e.sal * 20 / 100
        when avg(g.score) >= 90 then e.sal * 15 / 100
        when avg(g.score) >= 80 then e.sal * 10 / 100
        else e.sal * 0
     end as BONUS
from hr_employees e join hr_grade g on (e.emp_no = g.emp_no)
group by e.emp_no, e.emp_name
order by e.emp_no;