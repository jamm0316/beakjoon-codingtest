page link : [https://www.acmicpc.net/problem/15655](https://www.acmicpc.net/problem/15655)

---

# 💡 풀이전략

- 백트래킹 사용
    - 사용이유: 조합을 탐색하기 위해 재귀를 사용하여, 각 세션을 탐색해야하며, 조건이 있는 조합을 탐색해야하므로 백트래킹 사용.
- 결과값 출력이 ascending으로 sort되어 있고, 각 세션별로 이전 세션 숫자는 탐색하지 않는 조건이 있다.
- len(sequecne) == M 이면 해당 sequence를 join하여 출력
- backtrack시 sequence.append(number[i])하며, 범위는 start를 변수로 주어 시작하는 위치를 단계별로 1씩 증가시킨다.
- sequence.pop()을 이용하여 해당 세션이 끝나면 appending된 마지막 숫자를 제거해준다.

## 🎨 사용된 알고리즘

> [!tip]
> Backtracking: 백트래킹

---

# code

## Python

```python
import sys

def backtrack(N, M, sequence, numbers, start):
    if len(sequence) == M:
        print(" ".join(map(str, sequence)))
        return
    
    for i in range(start, N):
        sequence.append(numbers[i])
        start += 1
        backtrack(N, M, sequence, numbers, start)
        
        sequence.pop()
    

def read_data(input_data):
    lines = [list(map(int, line.split())) for line in input_data.splitlines()]
    N, M = lines[0]
    numbers = sorted(lines[1])
    return N, M, numbers

if __name__ == '__main__':
    input_data = sys.stdin.read()
    N, M, numbers = read_data(input_data)
    backtrack(N, M, [], numbers, 0)
```
