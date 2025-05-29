-- 코드를 입력하세요
SELECT i.FLAVOR
from ICECREAM_INFO i join FIRST_HALF f on (i.FLAVOR = f.FLAVOR)
where f.TOTAL_ORDER > 3000 and i.INGREDIENT_TYPE = 'fruit_based';