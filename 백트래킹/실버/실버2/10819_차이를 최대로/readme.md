page link : [https://www.acmicpc.net/problem/10819](https://www.acmicpc.net/problem/10819)

---

# 💡 풀이전략

- 방법1. 백트래킹 이용
    - 만들 수 있는 모든 순열을 만든다.
        - 백트래킹으로 구현
        - itertools → permutations로 구현
    - 해당 순열을 for문을 이용하여, 위 공식의 결과값을 출력한다.
    - 출력한 값을 max_value 변수와 비교하여, 해당 더 높은 숫자를 넣는다.
    - max_value를 반환한다.

## 🎨 사용된 알고리즘

> [!tip]
> Backtracking: 백트래킹

---

# code

## Python - 방법1

```python
import sys
from itertools import permutations

def make_permutation(numbers):
    return permutations(numbers)

def print_max_value():
    permutations_list = make_permutation(numbers)
    max_value = calculate_permutation(N, permutations_list)
    print(max_value)

def calculate_permutation(N, permutations_list):
    max_value = 0
    for perm in permutations_list:
        total = 0
        for i in range(N - 1):
            total += abs(perm[i] - perm[i + 1])
        max_value = max(max_value, total)
    return max_value

def read_data(input_data):
    lines = [list(map(int, line.split())) for line in input_data.splitlines()]
    N = lines[0][0]
    numbers = sorted(lines[1], reverse = True)
    return N, numbers

if __name__ == '__main__':
    input_data = sys.stdin.read()
    N, numbers = read_data(input_data)
    print_max_value()
```

## Python - 개선된 코드

```python
import itertools

N = int(input())
A = list(map(int, input().split()))

permutations = itertools.permutations(A)

max_value = 0
for perm in permutations:
    total = 0
    for i in range(N - 1):
        total += abs(perm[i] - perm[i + 1])
    
    max_value = max(max_value, total)

print(max_value)
```

## Python - 방법2 - 백트래킹

```python
def calculate_total(arr):
    total = 0
    for i in range(len(arr) - 1):
        total += abs(arr[i] - arr[i + 1])
    return total

def backtrack(depth, current_permutation, visited):
    global max_value
    
    if depth == N:
        # 모든 순열을 완성했을 때, 값 계산
        max_value = max(max_value, calculate_total(current_permutation))
        return
    
    for i in range(N):
        if not visited[i]:
            visited[i] = True
            current_permutation.append(numbers[i])
            # 재귀 호출로 다음 깊이로 이동
            backtrack(depth + 1, current_permutation, visited)
            # 백트래킹: 상태 복원
            current_permutation.pop()
            visited[i] = False

def read_data(input_data):
    lines = [list(map(int, line.split())) for line in input_data.splitlines()]
    N = lines[0][0]
    numbers = lines[1]
    return N, numbers

if __name__ == '__main__':
    input_data = '6\n20 1 15 8 4 10'
    N, numbers = read_data(input_data)

    # 초기 설정
    max_value = 0
    visited = [False] * N
    current_permutation = []
    
    # 백트래킹 시작
    backtrack(0, current_permutation, visited)
    
    # 결과 출력
    print(max_value)
```
