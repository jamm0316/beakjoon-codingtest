page link : [https://www.acmicpc.net/problem/15649](https://www.acmicpc.net/problem/15649)

---

# 💡 풀이전략

구하고자 하는 값: 조합 → nCm

---

- 전략
    - 백트래킹 사용
    - 1부터 N까지 숫자들 중 M개를 고른 수열 생성
    - 중복 허용하지 않으므로, 한번 사용한 숫자 사용 안함
    - 사전 순이므로, 순서대로 선택
- 절차
    1. N과 M을 입력받음.
    2. M개의 수열을 만들기 위해 백트래킹 정의
        1. 현재 수열의 길이가 M에 도달하면 수열 출력
        2. 1부터 N까지 숫자 중에서, 아직 사용하지 않은 숫자 선택하여 재귀 호출
    3. 이미 사용한 숫자는 다음 재귀 단계에서 제외
    4. 재귀 호출에서 돌아오면 수열에서 숫자를 제거해 다음 선택 진행

## 🎨 사용된 알고리즘

>[!tip]
> BackTracking: 백트래킹

---

# code

## Python

```python
import sys

def backtrack(N, M, sequence, visited):
    if len(sequence) == M:
        print(' '.join(map(str, sequence)))
        return
    
    for i in range(1, N + 1):
        if not visited[i]:
            visited[i] = True
            sequence.append(i)
            backtrack(N, M, sequence, visited)

            sequence.pop()
            visited[i] = False

input_data = sys.stdin.read()
N, M = map(int, input_data.split())
visited = [False] * (N + 1)
backtrack(N, M, [], visited)
```
