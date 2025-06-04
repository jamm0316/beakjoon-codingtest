-- 코드를 입력하세요
-- 리뷰 평균 점수는 소수점 세번째자리에서 반올림
-- 
SELECT i.REST_ID, i.REST_NAME, i.FOOD_TYPE, i.FAVORITES, i.ADDRESS, round(avg(r.REVIEW_SCORE), 2) AS SCORE
from REST_INFO i join REST_REVIEW r on (i.REST_ID = r.REST_ID)
where i.ADDRESS like '서울%'
group by i.REST_ID
order by SCORE desc, i.FAVORITES desc;