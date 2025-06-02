-- 코드를 입력하세요
SELECT YEAR(s.SALES_DATE) AS YEAR,
       MONTH(s.SALES_DATE) AS MONTH,
       i.GENDER AS GENDER,
       count(distinct(i.USER_ID)) USERS
from USER_INFO i join ONLINE_SALE s on (i.USER_ID = s.USER_ID)
where i.GENDER is not null
group by YEAR(s.SALES_DATE), MONTH(s.SALES_DATE), i.GENDER
order by YEAR(s.SALES_DATE), MONTH(s.SALES_DATE), i.GENDER;