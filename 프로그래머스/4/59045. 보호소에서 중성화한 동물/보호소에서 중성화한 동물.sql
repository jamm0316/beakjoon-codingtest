-- 코드를 입력하세요
select o.ANIMAL_ID, o.ANIMAL_TYPE, o.NAME
from
    (SELECT ANIMAL_ID, ANIMAL_TYPE, NAME
    from ANIMAL_INS
    where SEX_UPON_INTAKE like '%Intact%') i
join ANIMAL_OUTS o on (i.ANIMAL_ID = o.ANIMAL_ID)
where o.SEX_UPON_OUTCOME like '%Spayed%'
  or o.SEX_UPON_OUTCOME like '%Neutered%'
order by 1;