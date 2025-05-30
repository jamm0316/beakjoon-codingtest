-- 코드를 입력하세요
SELECT CAR_ID, 
CASE
  WHEN 
    SUM(CASE 
        WHEN '2022-10-16' BETWEEN START_DATE and END_DATE 
        THEN 1 ELSE 0 
        END) > 0
  THEN '대여중'
  ELSE '대여 가능'
END AS AVAILABILITY
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by CAR_ID
order by CAR_ID desc;