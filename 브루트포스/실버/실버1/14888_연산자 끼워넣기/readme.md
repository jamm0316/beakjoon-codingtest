page link : [https://www.acmicpc.net/problem/14888](https://www.acmicpc.net/problem/14888)

---

# 💡 풀이전략

1. 입력 이해
    - 주어진 연산자 개수로 모든 가능한 연산자 구하기
2. 브루트 포스 방식
    - 연산자의 모든 가능한 순열 생성
    - 각 순열에 따라 수식을 계산하고, 그 중 최대, 최소값 기록
3. 순차계산
    - 연산 우선순위는 고려하지않고 앞에서부터 차례로 계산
    - 나눗셈은 정수 나눗셈으로 수행되며, 특히 음수 나눗셈에 주의
4. 파이썬의 itertools 사용
    - 연산자의 모든 순서를 처리하기 위해 파이썬의 itertools, permutations 사용
5. 음수 나눗셈 처리
    - 파이썬에서는 기본적으로 음수를 양수로 나누면 내림을 하므로, 조건문 사용

---

- 연산자 리스트, 정수 리스트를 만든다.
- 정수리스트 사이에 연산자를 번갈아 가면서 넣는다.

## 🎨 사용된 알고리즘

> [!tip]
> Brute-Force: 완전 탐색

---

## pseudo code

```python
1. 입력을 처리하고 숫자 배열과 연산자의 개수 저장
2. 연산자의 가능한 순열 모두 생성
3. 각 순열에 따라 연산을 차례대로 수행
4. 결과 중 최대값과 최소값 저장 및 출력
```

---

# code

## Python

```python
import sys
import itertools

def main(input_data):
    parsed_data = ParseData(input_data)
    numbers, operators = parsed_data.numbers, parsed_data.operators

    calculator = ExpressionCalculator(numbers, operators)
    max_value, min_value = calculator.find_max_min()
    print(max_value)
    print(min_value)

class ParseData:
    def __init__(self, input_data):
       self.numbers, self.operators = self.parse_input(input_data)
    
    def parse_input(self, input_data):
        lines = input_data.strip().splitlines()  # 공백 제거
        N = int(lines[0])
        numbers = list(map(int, lines[1].split()))
        add, sub, mul, div = map(int, lines[2].split())

        operators = ['+'] * add + ['-'] * sub + ['*'] * mul + ['/'] * div
        return numbers, operators

class ExpressionCalculator:
    def __init__(self, numbers, operators):
        self.numbers = numbers
        self.operators = operators
        self.operations = {
            '+': lambda a, b: a + b,
            '-': lambda a, b: a - b,
            '*': lambda a, b: a * b,
            '/': self.divide
        }
    
    def divide(self, a, b):
        # a * b < 0이면 음수 나눗셈
        if a * b < 0:
            return -(-a // abs(b))
        else:
            return a // b
        
    def calculate_expression(self, operator_perm):
        result = self.numbers[0]
        for i in range(1, len(self.numbers)):
            operator = operator_perm[i - 1]
            result = self.operations[operator](result, self.numbers[i])
        return result
    
    def find_max_min(self):
        max_value = -1e9
        min_value = 1e9

        # 중복을 제거한 연산자 순열을 생성합니다.
        for operator_perm in itertools.permutations(self.operators, len(self.numbers) - 1):
            result = self.calculate_expression(operator_perm)
            max_value = int(max(max_value, result))
            min_value = int(min(min_value, result))
        return max_value, min_value
    
if __name__ == '__main__':
    input_data = sys.stdin.read()
    main(input_data)
```

## 해결한 오류

### 1. 캡슐화

- 기존 코드는 캡슐화가 되어있지 않아 각 코드의 역할이 불분명하고, 어떤 코드와 연관이 있는지 알기 어렵다. 따라서 각 코드의 역할을 분명히하고 재사용 및 유지보수 가능한 코드로 개선
    - 데이터 파싱 클래스: PaseData
    - 비즈니스 로직 처리 클래스: ExpressionCalculator
        - __init__: 초기화 함수
        - divide: 나눗셈 함수
        - calculate_expression: 순열 계산 함수
        - find_max_min: 최대, 최솟값 찾는 함수

### 2. 순차적 연산 로직 lambda로 수정

**기존코드**

```java
for operator_perm in itertools.permutations(operators, N-1):
    # 첫 번째 숫자로 시작
    result = numbers[0]

    # 순차적으로 연산을 수행
    for i in range(1, N):
        if operator_perm[i-1] == '+':
            result += numbers[i]
        elif operator_perm[i-1] == '-':
            result -= numbers[i]
        elif operator_perm[i-1] == '*':
            result *= numbers[i]
        elif operator_perm[i-1] == '/':
            # 나눗셈의 경우 음수 나눗셈 처리를 해야 함
            if result < 0:
                result = -(-result // numbers[i])
            else:
                result //= numbers[i]

    # 최댓값과 최솟값 갱신
    max_value = max(max_value, result)
    min_value = min(min_value, result)
```

**수정된 코드**

```java
def __init__(self, numbers, operators):
    self.numbers = numbers
    self.operators = operators
    self.operations = {
        '+': lambda a, b: a + b,
        '-': lambda a, b: a - b,
        '*': lambda a, b: a * b,
        '/': self.divide
    }
        
def calculate_expression(self, operator_perm):
		result = self.numbers[0]
		for i in range(1, len(self.numbers)):
		    operator = operator_perm[i - 1]
		    result = self.operations[operator](result, self.numbers[i])
		return result
```

---

- 기존코드
    
    ```python
    import itertools
    
    # 입력 처리
    N = int(input())  # 수의 개수
    numbers = list(map(int, input().split()))  # 수열
    add, sub, mul, div = map(int, input().split())  # 각 연산자의 개수
    
    # 연산자를 배열로 변환
    operators = ['+'] * add + ['-'] * sub + ['*'] * mul + ['/'] * div
    
    # 최댓값, 최솟값을 매우 큰 값과 작은 값으로 초기화
    max_value = -1e9
    min_value = 1e9
    
    # 모든 연산자의 순열을 구한다
    for operator_perm in itertools.permutations(operators, N-1):
        # 첫 번째 숫자로 시작
        result = numbers[0]
    
        # 순차적으로 연산을 수행
        for i in range(1, N):
            if operator_perm[i-1] == '+':
                result += numbers[i]
            elif operator_perm[i-1] == '-':
                result -= numbers[i]
            elif operator_perm[i-1] == '*':
                result *= numbers[i]
            elif operator_perm[i-1] == '/':
                # 나눗셈의 경우 음수 나눗셈 처리를 해야 함
                if result < 0:
                    result = -(-result // numbers[i])
                else:
                    result //= numbers[i]
    
        # 최댓값과 최솟값 갱신
        max_value = max(max_value, result)
        min_value = min(min_value, result)
    
    # 결과 출력
    print(max_value)
    print(min_value)
    ```
