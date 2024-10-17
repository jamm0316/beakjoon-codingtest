page link : [https://www.acmicpc.net/problem/15656](https://www.acmicpc.net/problem/15656)

---

# 💡 풀이전략

- 백트래킹
- len(seqence) == M일 경우 join으로 출력

## 🎨 사용된 알고리즘

> [!tip]
> BackTracking: 백트래킹

---

# code

## Python

```python
import sys

def backtrack(N, M, sequence, numbers):
    if len(sequence) == M:
        print(' '.join(map(str, sequence)))
        return
    
    for i in range(N):
        sequence.append(numbers[i])
        backtrack(N, M, sequence, numbers)

        sequence.pop()

input_data = sys.stdin.read()
lines = [list(map(int, line.split())) for line in input_data.splitlines()]
N, M = lines[0]
numbers = sorted(lines[1])
backtrack(N, M, [], numbers)
```
