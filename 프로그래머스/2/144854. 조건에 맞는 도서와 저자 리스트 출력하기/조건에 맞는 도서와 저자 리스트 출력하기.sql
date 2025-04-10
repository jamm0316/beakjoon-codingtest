-- 코드를 입력하세요
SELECT BOOK.BOOK_ID, AUTHOR.AUTHOR_NAME, DATE_FORMAT(BOOK.PUBLISHED_DATE, '%Y-%m-%d') as PUBLISHED_DATE
from BOOK join AUTHOR on (BOOK.AUTHOR_ID = AUTHOR.AUTHOR_ID)
where CATEGORY = '경제'
order by BOOK.PUBLISHED_DATE asc