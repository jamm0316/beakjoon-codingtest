-- 코드를 입력하세요
SELECT MCDP_CD as '진료과코드', 
       count(distinct pt_no) as '5월예약건수'
from APPOINTMENT
WHERE DATE_FORMAT(APNT_YMD, '%Y-%m') = '2022-05'

group by MCDP_CD
ORDER BY COUNT(DISTINCT PT_NO) ASC, MCDP_CD ASC;