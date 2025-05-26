-- 코드를 입력하세요
SELECT count(*) as USERS
from USER_INFO
where TO_CHAR(JOINED, 'yyyy') = 2021
and AGE between 20 and 29
