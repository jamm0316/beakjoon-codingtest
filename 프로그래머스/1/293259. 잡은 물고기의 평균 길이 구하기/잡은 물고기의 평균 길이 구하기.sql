-- 코드를 작성해주세요
select round(avg(LENGTH), 2) AS AVERAGE_LENGTH
from (
    select ID, COALESCE(LENGTH, 10) AS LENGTH
    from FISH_INFO
) AS TEMP;

