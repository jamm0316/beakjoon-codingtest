page link : [https://www.acmicpc.net/problem/1065](https://www.acmicpc.net/problem/1065)

---

# 💡 풀이전략

- 각 자리 수 별 차가 일정한 수 모두 찾기
    - 10의 자리 숫자 까지는 모두 한수
    - 100의 자리 숫자부터 조건 부여
        - `digit[1] - digit[0] == digit[2] - digit[1]`

## 🎨 사용된 알고리즘


> [!tip]
> Brute-Force(브루트 포스): 완전탐색

---

# code

## Python

```python
def count_hansu(N):
    if N <= 99:
        return N
        
    count = 99
    for i in range(100, N + 1):
        digit = list(map(int, str(i)))

        if digit[1] - digit[0] == digit[2] - digit[1]:
            count += 1
    return count

N = int(input())
print(count_hansu(N))
```
