page link : https://www.acmicpc.net/problem/1212

# 💡 풀이전략
1. **입력 받기**
2. **8진수를 10진수로 변환**
    1. int를 사용
3. **10진수를 2진수로 변환**
    1. bin을 사용
    2. 결과 문자열에서 접두사 ‘0b’를 제거
4. **출력**

## 🎨 사용된 알고리즘
구현(plementation)

---

## pseudo code

```python
# Step 1: 입력 받기
octal_number = input().strip()

# Step 2: 8진수를 10진수로 변환
decimal_number = int(octal_number, 8)

# Step 3: 10진수를 2진수로 변환
binary_number = bin(decimal_number)[2:]

# Step 4: 변환된 2진수를 출력
print(binary_number)
```

---

# code

## Python

```python
# 8진수를 입력받습니다.
octal_number = input().strip()

# 8진수를 10진수로 변환합니다.
decimal_number = int(octal_number, 8)

# 10진수를 2진수로 변환합니다.
binary_number = bin(decimal_number)[2:]

# 변환된 2진수를 출력합니다.
print(binary_number)
```

## 해결한 오류

### 1. 큰 입력 값에 대한 시간초과 오류

**파이썬 내장 함수와 내가 작성한 코드가 다른 이유**

1. 내부 최적화
    - int와 bin은 C로 구현되어있으며, 내부적으로 최적화가 되어있음.
2. 시간 복잡도
    - int, bin함수 모두 O(n)의 시간복잡도를 가지며, 이는 내가 작성한 코드와 동일함.
    - 그러나, 진수 변환 과정에서 불필요한 반복문이나 리스트 사용, 문자열 조작 등을 통해 코드의 비효율성이 증가

### 2. solution

---

- 기존코드
    
    ```python
    # 입력값 받기
    octal_input = input().strip()
    octal_digits = list(map(int, octal_input))
    
    # 8진수를 10진수로 변환.
    decimal_value = 0
    for index in range(len(octal_digits)):
        decimal_value += octal_digits[index] * (8 ** (len(octal_digits) - index - 1))
    print(decimal_value)
    
    # 10진수를 2진수로 변환하기 위해 이진수 리스트를 초기화.
    binary_digits = []
    
    while decimal_value > 0:
        remainder = decimal_value % 2
        decimal_value = decimal_value // 2
        binary_digits.append(remainder)
    print(binary_digits)
    
    # 이진수 리스트를 뒤집기.
    binary_digits.reverse()
    
    # 문자열로 변환.
    binary_string = ''.join(map(str, binary_digits))
    print(binary_string)
    ```
  
