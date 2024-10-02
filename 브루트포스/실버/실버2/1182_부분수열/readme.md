page link : [https://www.acmicpc.net/problem/1182](https://www.acmicpc.net/problem/1182)

---

# 💡 풀이전략

- 구하고자 하는 값
    - 주어지는 수열에서 합이 S가 되는 경우의 수
- 풀이 절차
    - 백트래킹 → 수열의 크기가 작으므로 가능
    1. 선택지: 각 원소를 포함하거나 포함하지 않거나
    2. 각 재귀 단계에서 현재 원소를 포함할지 여부 결정
    3. 수열 끝까지 탐색이 완료되면 원소들의 합 검증

## 🎨 사용된 알고리즘

Back-Tracking: 백트래킹

---

# code

## Python

```python
def backtrack(idx, current_sum, count):
    global result
    
    if idx == N:
        if count > 0 and current_sum == S:
            result += 1
        return

    # 현재 원소를 선택하는 경우
    backtrack(idx + 1, current_sum + sequence[idx], count + 1)

    # 현재 원소를 선택하지 않는 경우
    backtrack(idx + 1, current_sum, count)

# 입력 처리
N, S = map(int, input().split())
sequence = list(map(int, input().split()))

result = 0

# 백트래킹 함수 호출
backtrack(0, 0, 0)
print(result)
```
