-- 코드를 입력하세요
SELECT p.PRODUCT_CODE, sum(o.SALES_AMOUNT * p.PRICE) as SALES
from PRODUCT as p
    join OFFLINE_SALE as o
    on (p.PRODUCT_ID = o.PRODUCT_ID)
group by p.PRODUCT_CODE
order by SALES desc, p.PRODUCT_CODE asc