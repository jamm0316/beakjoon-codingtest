-- 코드를 입력하세요
select round(avg(DAILY_FEE), 0) AS AVERAGE_FEE
from CAR_RENTAL_COMPANY_CAR
where CAR_TYPE = 'SUV';