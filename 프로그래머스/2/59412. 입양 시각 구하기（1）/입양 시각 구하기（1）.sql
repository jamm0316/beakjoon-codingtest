SELECT DATE_FORMAT(DATETIME, '%H') as HOUR, count(DATETIME) as count
from ANIMAL_OUTS
group by HOUR
having HOUR>=9 and HOUR<20
order by HOUR;