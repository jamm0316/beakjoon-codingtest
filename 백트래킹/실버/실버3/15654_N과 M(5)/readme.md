page link : [https://www.acmicpc.net/problem/15654](https://www.acmicpc.net/problem/15654)

---

# 💡 풀이전략

- 백트래킹 이용
- len(seqeunce) == M일 경우, sequence 반환.
- visited를 이용해 이미 방문한 인덱스는 재 방문 안함.
    - 방문하지 않은 것은 sequence에 append.
- 새로운 session에서는 sequence, visited 초기화
    - visited = Flase로 변환
    - sequence.pop()을 이용해 들어갔던 숫자 제거

## 🎨 사용된 알고리즘

> [!tip]
> Backtracking: 백트래킹

---

# code

## Python

```python
import sys

def backtrack(N, M, sequence, number_list, visited):
    if len(sequence) == M:
      print(" ".join(map(str, sequence)))
      return
    
    
    for i in range(N):
        if not visited[i]:
            sequence.append(number_list[i])
            visited[i] = True
            backtrack(N, M, sequence, number_list, visited)
        
            sequence.pop()
            visited[i] = False

input_data = sys.stdin.read()
lines = input_data.splitlines()
N, M = map(int, lines[0].split())
visited = [False] * N
number_list = sorted(list(map(int, lines[1].split())))

backtrack(N, M, [], number_list, visited)
```
