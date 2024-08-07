page link : [https://www.acmicpc.net/problem/20053](https://www.acmicpc.net/problem/20053)

# 💡 풀이전략
1. 입력값 받기
    - Q: 쿼리 갯수
    - N: (쿼리별) 배열의 크기
    - A: (쿼리별) 배열
2. 쿼리별 배열 순환하여 최소값, 최대값 찾기
    
    ```python
    min_max = []
    for i 0~Q:
        min = min(A)
        max = max(A)
        min_max.append(min)
        min_max.append(max)
    	    ' '.join(map(str, min_max))
    ```

## 🎨 사용된 알고리즘

> 구현(implement)

---

# code

## Python

```python
import sys

input = sys.stdin.read
lines = input().split('\n')

# 입력값 받기
Q = int(lines[0])  # 쿼리 개수
A = []
for i in range(0, Q * 2, 2):
    A.append(list(map(int, lines[2 + i].split())))

# 최소, 최대값 구하기
results = []
for case in A:
    min_value = min(case)
    max_value = max(case)
    results.append([min_value, max_value])

# 결과 출력
for result in results:
    print(' '.join(map(str, result)))
```
