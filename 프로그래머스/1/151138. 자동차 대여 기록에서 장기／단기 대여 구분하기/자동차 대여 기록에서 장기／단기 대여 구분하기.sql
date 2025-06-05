-- 코드를 입력하세요
select HISTORY_ID, CAR_ID,
       DATE_FORMAT(START_DATE, '%Y-%m-%d') AS START_DATE,
       DATE_FORMAT(END_DATE, '%Y-%m-%d') AS END_DATE,
       CASE
         WHEN DATEDIFF(END_DATE, START_DATE) + 1 >= 30 THEN '장기 대여'
         ELSE '단기 대여'
       END AS RENT_TYPE
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where YEAR(START_DATE) = 2022 and MONTH(START_DATE) = 09
order by HISTORY_ID desc;