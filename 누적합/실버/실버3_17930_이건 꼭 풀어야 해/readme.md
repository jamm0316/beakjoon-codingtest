page link : [https://www.acmicpc.net/status?user_id=jamm0316&problem_id=17390&from_mine=1](https://www.acmicpc.net/status?user_id=jamm0316&problem_id=17390&from_mine=1)

---

# 💡 풀이전략

1. 입력값 받기
2. 배열 오름차순으로 정렬
3. 누적합계산
4. 누적합에서 query에 따른 결과값 도출

## 🎨 사용된 알고리즘
누적합(prefix_sum)

---

# code

## Python

```python
import sys

input = sys.stdin.read()
lines = input.split('\n')

N, Q = list(map(int, lines[0].split()))
A = list(map(int, lines[1].split()))
A.sort()

queries = []
for line in range(2, Q + 2):
    queries.append(list(map(int, lines[line].split())))

prefix_sum = [0] * (N + 1)
for i in range(1, N + 1):
    prefix_sum[i] = A[i - 1] + prefix_sum[i - 1]

results = []
for i, j in queries:
    result = prefix_sum[j] - prefix_sum[i - 1]
    results.append(result)

for result in results:
    print(result)
```

## 해결한 오류

### 1. set() → list.sort

- set은 중복요소를 제거하므로 배열에 동일한 값이 있을 때, 값이 제거되어 index에 변화를 줌.
- 따라서 set()함수가 아닌 list.sort메서드를 사용하여 오름차순으로 정렬

ex) 

```python
A = [1, 2, 2, 1, 3, 5, 5, 4, 3, 3]
set(A)  #출력: [1, 2, 3, 4, 5]
A.sort  #출력: [1, 1, 2, 2, 3, 3, 4, 4, 5, 5]  기본: 오름차순
A.sort(reverse = True)  #출력: [5, 5, 4, 4, 3, 3, 2, 2, 1, 1]  역순: 내림차순
```

---

- 기존코드
    
    ```python
    import sys
    
    input = sys.stdin.read()
    lines = input.split('\n')
    
    N, Q = list(map(int, lines[0].split()))
    A = list(set(map(int, lines[1].split())))
    
    queries = []
    for line in range(2, Q + 2):
        queries.append(list(map(int, lines[line].split())))
    
    prefix_sum = [0] * (N + 1)
    for i in range(1, N + 1):
        prefix_sum[i] = A[i - 1] + prefix_sum[i - 1]
    
    results = []
    for i, j in queries:
        result = prefix_sum[j] - prefix_sum[i - 1]
        results.append(result)
    
    for result in results:
        print(result)
    ```
    

---

[질문저장소](https://www.notion.so/24cc8195aa2a47a19e7ed111d71e30d4?pvs=21)
