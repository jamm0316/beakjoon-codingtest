page link : [https://www.acmicpc.net/problem/16507](https://www.acmicpc.net/problem/16507)

# 💡 풀이전략
1. 2차원 배열에 대한 누적합(prefix_sum)을 구한다.
2. 범위에 대한 부분합을 구한다.
3. 부분합에 대한 평균을 구한다.

## 🎨 사용된 알고리즘
누적합(prefix_sum)

---

## pseudo code

```python
1. 입력값 받기
	- R: row(열)
	- C: column(행)
	- K: 쿼리 갯수
	- A: 배열
	- queries: 쿼리
	
2. prefix_sum 초기화
    prefix_sum = [0] * (R + 1)
    for i in range(R + 1):
        prefix_sum[i] = [0] * (C + 1)

3. prefix_sum 구축
    for i in range(1, R + 1):
        for j in range(1, C + 1):
            prefix_sum[i][j] = A[i - 1][j - 1] + prefix_sum[i - 1][j] + prefix_sum[i][j - 1] - prefix_sum[i - 1][j - 1]

4. 쿼리별 부분합 구하기
    for i, j, x, y in queries:
        partial_sum = prefix_sum[x][y] - prefix_sum[x][j - 1] - prefix_sum[i - 1][y] + prefix_sum[i - 1][j - 1]
        count = (x - i + 1) * (y - j + 1)  # 더한 값의 갯수
        result = partial_sum // count  # 평균구하기
        results.append(result)

5. 결과값 반환
```

---

# code

## Python

```python
import sys

input = sys.stdin.read()
lines = input.split('\n')
R, C, K = list(map(int, lines[0].split()))
A = []
for line in range(1, R + 1):
    row = list(map(int, lines[line].split()))
    A.append(row)

queries = []
for line in range(R + 1, R + 1 + K):
    query = list(map(int, lines[line].split()))
    queries.append(query)

# prefix_sum 초기화
prefix_sum = [0] * (R + 1)
for i in range(R + 1):
    prefix_sum[i] = [0] * (C + 1)

# prefix_sum 구축
for i in range(1, R + 1):
    for j in range(1, C + 1):
        prefix_sum[i][j] = A[i - 1][j - 1] + prefix_sum[i - 1][j] + prefix_sum[i][j - 1] - prefix_sum[i - 1][j - 1]

# 쿼리별 부분합 구하기
results = []
for i, j, x, y in queries:
    partial_sum = prefix_sum[x][y] - prefix_sum[x][j - 1] - prefix_sum[i - 1][y] + prefix_sum[i - 1][j - 1]
    count = (x - i + 1) * (y - j + 1)
    result = partial_sum // count
    results.append(result)

# 결과값 반환
for result in results:
    print(result)
```
