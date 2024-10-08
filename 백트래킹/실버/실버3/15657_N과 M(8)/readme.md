page link : [https://www.acmicpc.net/problem/15657](https://www.acmicpc.net/problem/15657)

---

# 💡 풀이전략

1. **숫자 선택**: 각 단계에서 아직 선택하지 않은 숫자를 선택하여 수열에 추가.
2. **종료 조건**: 선택한 숫자의 개수가 M이 되면, 해당 수열을 출력.
3. **백트래킹**: 선택된 숫자를 하나씩 제거하고, 다른 경우를 탐색.
4. **순서 유지**: 문제에서 순서를 유지해야 하기 때문에, 선택한 숫자보다 더 뒤에 있는 숫자들만 선택하여 중복된 결과를 방지.

## 🎨 사용된 알고리즘

> [!tip]
> Backtracking: 백트래킹

---

# code

## Python

```python
import sys

def backtrack(N, M, sequence, numbers, visited, start):
    if len(sequence) == M:
        print(' '.join(map(str, sequence)))
        return
    
    for i in range(start, N):
        sequence.append(numbers[i])
        backtrack(N, M, sequence, numbers, visited, i)
        sequence.pop()

def parse_data(input_data):
    lines = input_data.splitlines()
    N, M = map(int, lines[0].split())
    numbers = sorted(list(map(int, lines[1].split())))
    return N, M, numbers

if __name__ == '__main__':
    input_data = sys.stdin.read()
    N, M, numbers = parse_data(input_data)
    visited = [False] * N
    backtrack(N, M, [], numbers, visited, 0)
```

---
