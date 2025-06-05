-- 코드를 입력하세요
select USER_ID, PRODUCT_ID
from ONLINE_SALE
group by USER_ID, PRODUCT_ID
having count(USER_ID) >= 2 and count(PRODUCT_ID) >=2
order by USER_ID, PRODUCT_ID desc;