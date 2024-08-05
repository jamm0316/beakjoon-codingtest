page link : [https://www.acmicpc.net/problem/19951](https://www.acmicpc.net/problem/19951)

---

# 💡 풀이전략
1. 입력값 받기
    1. N: 배열의 크기
    2. Q: 쿼리 갯수
    3. A: 배열
    4. queries: 쿼리
2. prefix_sum 배열 구축
    1. [0]*(N+1) 배열 초기화
    2. 쿼리를 순환하며, 해당 배열의 값 업데이트 → O(n^2)
3. prefix_sum과 기존 배열의 합 구하기

## 🎨 사용된 알고리즘

[**차분배열(Diffence Araay)**](https://www.notion.so/c44a8d6a3c774aa89ef88ab30ecc3c41?pvs=21): 업데이트 시작과 끝 부분에 차이를 적용하여 구간 업데이트를 수행

---

## pseudo code

💡 **아이디어**

[**차분배열(Diffence Araay)**](https://www.notion.so/c44a8d6a3c774aa89ef88ab30ecc3c41?pvs=21)을 통해 시간복잡도를 O(1)로 줄일 수 있다.

- **1단계**: 배열의 크기 N, 쿼리의 갯수 Q, 초기 배열 A, 쿼리 리스트를 입력받기.
- **2단계**: 차분 배열 diff를 구축하여 구간 업데이트 추적.
- **3단계**: 차분 배열 diff를 사용하여 배열 A를 업데이트.
- **4단계**: 최종 업데이트된 배열을 출력.

차분 배열을 사용하여 구간 업데이트를 O(Q)시간에 수행, 최종 배열 업데이트를 O(N)시간에 처리.

**따라서, 기존 O(QxN) → O(Q+N)으로 시간 복잡도 측면에서 효율성 확보**

```python
1. 입력값 받기
   - N: 배열의 크기
   - Q: 쿼리 갯수
   - A: 초기 배열
   - queries: 쿼리 리스트

2. 차분 배열(diff) 구축
   diff = [0] * (N + 1)
   for a, b, k in queries:  # 시간복잡도: O(Q)
       - diff[a - 1] += k
       - if b < N:
           - diff[b] -= k

3. 배열 업데이트
   update = []
   current_sum = 0
   for i in range(N):  # 시간복잡도: O(N)
       current_sum += diff[i]
       update.append(A[i] + current_sum)

4. 결과값 반환
   result = ' '.join(map(str, update))
   print(result)
```

- 기존코드
    
    ```python
    1. 입력값 받기
      - N: 배열의 크기
      - Q: 쿼리 갯수
      - A: 배열
      - queries: 쿼리
    2. prefix_sum 배열 구축
       prefix_sum = [0] * (N + 1)
       for a, b, k in queries:  # 시간복잡도: O(n^2)
           for i in range(a, b + 1):
               prefix_sum[i] += k
     3. for i in range(1, N + 1):
            A[i - 1] = prefix_sum[i] + A[i - 1]
     4. print(A)
    ```
    

---

# code

## Python

```python
import sys

input = sys.stdin.read()
lines = input.split('\n')

# 입력값 받기
N, Q = list(map(int, lines[0].split()))
A = list(map(int, lines[1].split()))
queries = []
for line in range(2, Q + 2):
    query = list(map(int, lines[line].split()))
    queries.append(query)

# diff 구축
diff = [0] * (N + 1)
for a, b, k in queries:
    diff[a - 1] += k
    if b < N:
        diff[b] -= k

# 배열 업데이트
update = []
current_sum = 0
for i in range(N):
    current_sum += diff[i]
    update.append(A[i] + current_sum)

# 결과값 반환
result = ' '.join(map(str, update))
print(result)
```

## 해결한 오류

### 1. 차분배열(Diffrence Array)을 통한 시간 복잡도 효율성 재고

- 기존 코드에서 prefix_sum을 구하기 위해 queries와 각 query별 range를 순환하는 이중 순환 구조로 코드를 작성하여, O(QxN)이라는 비효율적인 코드 작성함.
    
    ```python
    # prefix_sum 배열 구축
    prefix_sum = [0] * (N + 1)
    for a, b, k in queries:
        for i in range(a, b + 1):
            prefix_sum[i] += k
    ```
    
- 이에 따라, 차분배열이라는 개념을 도입하여, current_sum을 사용해 기존배열을 한번만 순회하며, current_sum으로 기존배열 업데이트 진행하여 O(Q+N)의 시간 복잡도를 가짐.
    
    ```python
    # diff 구축
    diff = [0] * (N + 1)
    for a, b, k in queries:
        diff[a - 1] += k
        if b < N:
            diff[b] -= k
    
    # 배열 업데이트
    update = []
    current_sum = 0
    for i in range(N):
        current_sum += diff[i]
        update.append(A[i] + current_sum)
    ```
    

---

- 기존코드
    
    ```python
    import sys
    
    input = sys.stdin.read()
    lines = input.split('\n')
    
    # 입력값 받기
    N, Q = list(map(int, lines[0].split()))
    A = list(map(int, lines[1].split()))
    queries = []
    for line in range(2, Q + 2):
        query = list(map(int, lines[line].split()))
        queries.append(query)
    
    # prefix_sum 배열 구축
    prefix_sum = [0] * (N + 1)
    for a, b, k in queries:
        for i in range(a, b + 1):
            prefix_sum[i] += k
    
    # 두 배열의 합
    for i in range(1, N + 1):
        A[i - 1] = prefix_sum[i] + A[i - 1]
    
    # 배열 출력
    result = ' '.join(map(str, A))
    print(result)
    ```

---
