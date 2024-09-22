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
# 전체 수 정의
all_number = set(range(1, 10001))

# 생성자가 있는 수 정의
def d(n):
    sum_number = sum(map(int, str(n)))
    return sum_number + n

constructor_number = set()
for n in range(1, 10001):
    constructor_number.add(d(n))

# 전체 수 - 생성자가 있는 수 = 셀프넘버
self_number = all_number - constructor_number

# 셀프넘버 출력
for number in sorted(self_number):
    print(number)
```

---

## 해결한 오류

### 리펙터링

- 기존에 각 자릿수의 합을 구하는 식을 계산하는 식은 하나씩 해석해보아야했다.
- 이에 따라 가독성이 떨어졌지만, 수정된 코드는 숫자를 문자로 바꾼 후 → map()함수를 통해 각 자리 수를 int로 바꾸고 → sum을 통해 모든 자릿 수를 합해주므로 좀 더 직관적인 코드로 수정하였다.
    - 기존코드
        
        ```python
        # 생성자가 있는 수 정의
        def d(n):
            result = n
            while n > 0:
                result += n % 10  # 마지막 자리수 더하기
                n //= 10 # 한자리수 씩 왼쪽으로 옮기기
            return result
        ```
        
    
    - 수정된 코드
        
        ```python
        # 생성자가 있는 수 정의
        def d(n):
            sum_number = sum(map(int, str(n)))
            return sum_number + n
        ```
        

---

- 기존코드
    
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
