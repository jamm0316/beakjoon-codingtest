-- 코드를 입력하세요
SELECT u.USER_ID, 
       u.NICKNAME, 
       CONCAT(CITY, ' ', STREET_ADDRESS1, ' ', STREET_ADDRESS2) AS 전체주소,
       CONCAT(LEFT(TLNO,3), '-', SUBSTR(TLNO,4, 4), '-', RIGHT(TLNO, 4)) AS 전화번호
from USED_GOODS_BOARD b join USED_GOODS_USER u on (b.WRITER_ID = u.USER_ID)
where (u.USER_ID) in (
    select u.USER_ID
    from USED_GOODS_BOARD b join USED_GOODS_USER u on (b.WRITER_ID = u.USER_ID)
    group by u.USER_ID
    having count(BOARD_ID) >= 3
)
group by USER_ID
order by u.USER_ID desc;