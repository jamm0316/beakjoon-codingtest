-- 코드를 입력하세요
SELECT a.APNT_NO, p.PT_NAME, p.PT_NO, a.MCDP_CD, d.DR_NAME, a.APNT_YMD
from APPOINTMENT a 
  join DOCTOR d on (a.MDDR_ID = d.DR_ID)
  join PATIENT p on (a.PT_NO = p.PT_NO)
where a.APNT_CNCL_YN = 'N' and DATE_FORMAT(a.APNT_YMD, '%Y-%m-%d') = '2022-04-13'
order by a.APNT_YMD;