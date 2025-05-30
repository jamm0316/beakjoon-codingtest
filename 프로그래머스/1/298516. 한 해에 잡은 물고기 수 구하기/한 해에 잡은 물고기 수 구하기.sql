-- 코드를 작성해주세요
select count(ID) AS FISH_COUNT
from FISH_INFO
where YEAR(TIME) = 2021;