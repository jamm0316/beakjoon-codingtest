-- 보호기간이 오름차순 상위 2마리 : 아이디, 이름

-- 코드를 입력하세요
SELECT o.ANIMAL_ID, o.NAME
from ANIMAL_INS i
  right join ANIMAL_OUTS o
  on (i.ANIMAL_ID = o.ANIMAL_ID)
order by (o.DATETIME - i.DATETIME) desc
limit 2;