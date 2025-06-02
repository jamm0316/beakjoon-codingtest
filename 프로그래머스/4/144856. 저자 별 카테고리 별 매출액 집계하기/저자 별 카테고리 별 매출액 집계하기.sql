-- 코드를 입력하세요
SELECT a.AUTHOR_ID, a.AUTHOR_NAME, b.CATEGORY, sum(b.PRICE * s.SALES) AS TOTAL_SALES
from BOOK b
  join BOOK_SALES s on (s.BOOK_ID = b.BOOK_ID)
  join AUTHOR a on (a.AUTHOR_ID = b.AUTHOR_ID)
where YEAR(SALES_DATE) = 2022 && MONTH(SALES_DATE) = 1
group by a.AUTHOR_ID, b.CATEGORY
order by a.AUTHOR_ID, b.CATEGORY desc;