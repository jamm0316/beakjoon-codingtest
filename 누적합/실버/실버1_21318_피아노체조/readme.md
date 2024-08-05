page link : [https://www.acmicpc.net/problem/21318](https://www.acmicpc.net/problem/21318)

---

# 💡 풀이전략

1. 입력값 받기
    - N: 배열의 범위
    - A: 배열
    - Q: 쿼리 갯수
    - queries: 쿼리 배열
2. 변수 정의
    - mistake_array = []
    - prefix_sum = []
3. mistake_array 구축
4. prefix_sum 구축
5. 쿼리별 prefix_sum 결과값 도출
- 아이디어
    1. 쿼리의 범위를 순환하며, 배열을 순환해 n개의 숫자를 비교해하는 방법은 O(n^2)의 시간복잡도를 갖는다. 이러한 시간 복잡도로는 0.5초 제한시간을 만족하기 어렵다.
        1. 시간 복잡도를 O(n)으로 줄일 수 있는 방법은?
        - **실수할 횟수를 미리 구한다 O(n)**
        - **실수할 횟수의 누적합을 미리 구한다 O(n)**
        - **그 누적합을 query별로 O(1)로 접근한다.**
            - O(n^2) → O(n) * 2 + O(1)로 줄일 수 있다.

## 🎨 사용된 알고리즘
prefix_sum(누적합)

---

## pseudo code

```python
1. 입력값 받기
  - N: 배열의 범위
  - A: 배열
  - Q: 쿼리 갯수
  - queries: 쿼리 배열

2. 변수 정의
  - mistake_array = [0] * (N + 1)
  - prefix_sum = [0] * (N + 1)

3. mistake_array 구축
for i 1 ~ N:
    if A[i - 1] > A[i]:
        mistake_array[i] += 1

4. prefix_sum 구축
for i 1 ~ (N + 1)
    prefix_sum[i] = mistake_array[i] + prefix_sum[i - 1]

5. each query 결과값 도출
results = []
for i, j in queries:
    result = prefix_sum[j - 1] - prefix_sum[i - 1]  #마지막 실수는 포함 안함
    results.append(result)

6. 결과값 출력
for result in results:
    print(result)
```

---

# code

## Python

```python
import sys

input = sys.stdin.read()
lines = input.split('\n')

# 입력값 읽기
N = int(lines[0])
A = list(map(int, lines[1].split()))
K = int(lines[2])
queries = []
for line in range(3, 3 + K):
    query = list(map(int, lines[line].split()))
    queries.append(query)

# 변수 정의
mistake_array = [0] * (N + 1)
prefix_sum = [0] * (N + 1)

# mistake_array 구축
for i in range(1, N):
    if A[i - 1] > A[i]:
        mistake_array[i] += 1

# prefix_sum 구축
for i in range(1, N + 1):
    prefix_sum[i] = mistake_array[i] + prefix_sum[i - 1]

# query별 결과값 도출
results = []
for i, j in queries:
    result = prefix_sum[j - 1] - prefix_sum[i - 1]
    results.append(result)

for result in results:
    print(result)
```
