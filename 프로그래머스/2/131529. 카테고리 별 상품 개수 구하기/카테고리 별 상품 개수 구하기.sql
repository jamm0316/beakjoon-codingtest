-- 코드를 입력하세요
select LEFT(PRODUCT_CODE, 2) as CATEGORY, count(PRODUCT_CODE) as PRODUCTS
from PRODUCT
group by LEFT(PRODUCT_CODE, 2)
order by 1;