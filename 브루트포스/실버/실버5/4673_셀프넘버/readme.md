page link : [https://www.acmicpc.net/problem/4673](https://www.acmicpc.net/problem/4673)

---

# 💡 풀이전략

- 모든 생성자 구하기
    - d(n) 함수를 통해 생성될 수 있는 숫자 계산
- 셀프넘버 찾기
    - 1부터 10,000까지 숫자 중에서 생성되지 않은 숫자를 셀프 넘버로 판정.

## 🎨 사용된 알고리즘

> [!tip]
> - Brute-Force: 완전 탐색
> - Implementation: 구현

---

# code

## Python

```python
# 아이디어: 셀프넘버 = 전체 숫자 - d(n) 생성자로 만들어진 숫자
def d(n):
    result = n
    while n > 0:
        result += n % 10  # 마지막 자리수 더하기
        n //= 10 # 한자리수 씩 왼쪽으로 옮기기
    return result

# 전체 숫자초기화와 generated_number 정의
self_number = set(range(1, 10001))
generated_number = set()
for i in range(1, 10001):
    generated_number.add(d(i))    

# self_number 정의 = 전체숫자 - generated_number
self_number -= generated_number

# 출력
for number in sorted(self_number):
    print(number)
```

---
