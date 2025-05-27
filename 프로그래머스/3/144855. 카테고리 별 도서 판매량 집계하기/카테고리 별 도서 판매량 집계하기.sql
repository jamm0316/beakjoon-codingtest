-- 코드를 입력하세요
SELECT b.CATEGORY AS CATEGORY, sum(s.SALES) AS TOTAL_SALES
from BOOK b
  join BOOK_SALES s
  on (b.BOOK_ID = s.BOOK_ID)
where TO_CHAR(s.SALES_DATE, 'mm') = '01'
group by CATEGORY
order by CATEGORY;