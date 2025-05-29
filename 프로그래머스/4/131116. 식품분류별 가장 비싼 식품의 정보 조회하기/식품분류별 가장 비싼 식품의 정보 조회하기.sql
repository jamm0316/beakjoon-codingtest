-- 코드를 입력하세요
SELECT CATEGORY, PRICE AS MAX_PRICE, PRODUCT_NAME 
from FOOD_PRODUCT
where (CATEGORY, PRICE) in (
    select CATEGORY, max(PRICE)
    from FOOD_PRODUCT
    where CATEGORY in ('과자', '김치', '국', '식용유')
    group by CATEGORY
 )
order by MAX_PRICE desc;