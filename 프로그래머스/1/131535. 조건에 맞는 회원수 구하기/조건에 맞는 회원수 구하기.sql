-- 코드를 입력하세요
SELECT count(*)
from USER_INFO
where DATE_FORMAT(JOINED, '%Y') = 2021
and AGE between 20 and 29
