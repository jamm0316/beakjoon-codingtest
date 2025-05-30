-- 코드를 입력하세요
SELECT user_id, PRODUCT_ID
from ONLINE_SALE
group by user_id, PRODUCT_ID
having count(PRODUCT_ID) >= 2
order by user_id, PRODUCT_ID desc;