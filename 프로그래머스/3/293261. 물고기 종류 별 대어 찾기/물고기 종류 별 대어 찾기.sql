-- 코드를 작성해주세요
WITH MAX_LENGTH_FISH AS (
    SELECT n.FISH_TYPE, n.FISH_NAME, max(i.LENGTH) AS MAX_LENGTH
    FROM FISH_INFO i JOIN FISH_NAME_INFO n ON (i.FISH_TYPE = n.FISH_TYPE)
    GROUP BY n.FISH_TYPE, n.FISH_NAME 
)

SELECT i.ID, m.FISH_NAME, m.MAX_LENGTH AS LENGTH
FROM FISH_INFO i RIGHT JOIN MAX_LENGTH_FISH m ON (i.FISH_TYPE = m.FISH_TYPE)
WHERE i.FISH_TYPE = m.FISH_TYPE and i.LENGTH = m.MAX_LENGTH
ORDER BY i.ID;