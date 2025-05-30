-- 코드를 작성해주세요
select sum(count2) AS FISH_COUNT
from (select count(i.FISH_TYPE) AS count2
from FISH_NAME_INFO n join FISH_INFO i on (n.FISH_TYPE = i.FISH_TYPE)
where n.FISH_NAME in ('BASS', 'SNAPPER')
group by n.FISH_TYPE) AS temp;