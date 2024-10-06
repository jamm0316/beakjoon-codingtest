page link : [https://www.acmicpc.net/problem/15652](https://www.acmicpc.net/problem/15652)

---

# 💡 풀이전략

- 전략
    - 백트래킹 이용
    - 자신을 포함하여 중복되지 않는 오름차순 출력.
- 절차
    1. N, M, sequence, start를 매개변수로 받는 backtrack함수 정의
    2. len(sequence) == M이면 해당 리스트를 출력하고 함수 반환
    3. start number ~ N + 1까지 순회하면서 sequence에 appending
    4. backtrack함수 재귀 호출하면서, start number에 i를 argument로 주어, 재귀 안에서는 i번째 부터 appending되게 설계함.
    5. 재귀의 if조건에 충족 시 retrun한 후 sequence.pop()을 통해 제일 마지막 숫자 제거.

## 🎨 사용된 알고리즘

> [!tip]
> Backtracking: 백트래킹

---

# code

## Python

```python
def backtrack(N, M, sequence, start):
    if len(sequence) == M:
        print(' '.join(map(str, sequence)))
        return

    for i in range(start, N + 1):
        sequence.append(i)
        backtrack(N, M, sequence, i)

        sequence.pop()

input_data = '3 3'
N, M = map(int, input_data.split())
backtrack(N, M, [], 1)
```
