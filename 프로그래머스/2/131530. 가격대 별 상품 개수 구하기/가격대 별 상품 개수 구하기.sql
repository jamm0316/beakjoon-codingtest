-- 코드를 입력하세요
select floor(price/10000)*10000 AS PRICE_GROUP, count(*)
from PRODUCT
group by PRICE_GROUP
order by PRICE_GROUP;