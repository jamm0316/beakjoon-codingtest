-- 코드를 입력하세요
SELECT BOOK_ID, DATE_FORMAT(PUBLISHED_DATE, '%Y-%m-%d')
from BOOK
where DATE_FORMAT(PUBLISHED_DATE, '%Y') = '2021' 
  and CATEGORY = '인문'
order by PUBLISHED_DATE;