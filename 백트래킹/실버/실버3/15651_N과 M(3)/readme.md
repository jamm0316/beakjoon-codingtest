page link : [https://www.acmicpc.net/problem/15651](https://www.acmicpc.net/problem/15651)

---

# 💡 풀이전략

- 전략
    - 백트래킹 사용
    - 서로 중복을 허용하며, 1~4까지 오름차순 출력
- 절차
    1. if len(sequece) == M 이면 sequence를 출력하고, 해당 함수 retrun
    2. 1 ~ N +1까지 순회하며 sequence에 appending하고, 각 순회하는 session별로 1 ~ N +1까지 순회하는 backtrack함수 재귀 호출

## 🎨 사용된 알고리즘

> [!tip]
> Backtracking: 백트래킹

---

# code

## Python

```python
import sys

def backtrack(N, M, sequence):
    if len(sequence) == M:
        print(' '.join(map(str, sequence)))
        return
    
    for i in range(1, N + 1):
        sequence.append(i)
        backtrack(N, M, sequence)

        sequence.pop()

input_data = sys.stdin.read()
N, M = map(int, input_data.split())
backtrack(N, M, [])

```
