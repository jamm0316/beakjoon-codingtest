page link : [https://www.acmicpc.net/problem/1806](https://www.acmicpc.net/problem/1806)

---

## 💡 풀이전략
1. 인자를 받는다.
    1. import sys와 map을 통해 인자 입력 및 정수 변경
    2. N: 배열의 길이, tagret: 인자의 합, A: 배열
2. 투포인터를 이용해 합이 15이상 경우를 찾는다.
    1. 각 인자를 초기화 한다
        1. start = 0
        2. current_sum = 0
        3. array_length = 0
        4. resutls = []
    2. for in + while 순환문을 이용해 모든 경우의 수를 탐색한다.
        1. 시간복잡도 : O(n) * O(k) → 확인 필요
    3. current_sum >= 15일 때, results.append(array_length)한다.
3.  array_length에서 최소 를 반환한다.
    1. min(results)를 통해 가장 작은 수를 반환한다.
    2. 반약 results가 비어있다면 0을 반환한다.


## 🎨 사용된 알고리즘
투 포인터(two pointer)

---

# code

## Python

```python
import sys
input = sys.stdin.read()

#데이터 받기
data = list(map(int, input.split()))
N = data[0]
TARGET = data[1]
A = data[2:]

#각 인자 초기화
start = 0
current_sum = 0
array_length = 0
results = []

#배열 탐색
for end in range(N):
    current_sum += A[end]
    while current_sum >= TARGET:
        array_length = end - start + 1
        results.append(array_length)
        current_sum -= A[start]
        start += 1

#배열의 최소길이 반환
if results:
    print(min(results))
else:
    print(0)
```

<details>
<summary><b>기존코드</b></summary>
    <div markdown="1">
        
```python
import sys
input = sys.stdin.read

#데이터 받기
data = list(map(int, input.split()))
N = data[0]
TARGET = data[1]
A = data[2 : N+2]

#각 인자 초기화
start = 0
current_sum = 0
array_length = 0
results = []

#배열 탐색
for end in range(N):
    current_sum += A[end]

    while current_sum > 15 and end <= N:
        start += 1
        current_sum = 0

    if current_sum == TARGET:
        array_length = end - start + 1
        results.append(array_length)

print(results)
```

</div>
</details>

## 해결한 오류

### 1. 오류수정

- **ValueError: 함수나 연산이 잘못된 값을 받았을 때 발생**
    - 위 코드가 ValueError가 뜨는 이유는 results 리스트가 비어 있을 때 min(results)를 호출하기 때문.
    - results 리스트가 비어 있는 경우, min() 함수는 ValueError 발생
- **부분합 조건: TARGET 상수 사용**
    - current_sum > 15 조건이 잘못입력.
    - TARGET을 사용.
- **while 루프 조건: end <= N은 불필요**
- **부분합 비교: TARGET 이상일 때 가장 짧은 배열 길이**
- **배열 탐색 범위: data[2 : N+2]가 아닌 data[2:]**

### 2. 빈리스트를 조건없이 if문으로 작성한다면?
> [!tip]
> 💡 **if 구문은 다양한 타입의 값에 따라 다르게 평가한다.
> (True of False)**

## 개요

```python
results = []
if results:
```

위처럼 if 구문에 아무런 조건이 없이 변수 또는 상수만 있다면 if는 어떤 값을 반환할까?

True? or Flase?

## 타입에 따른 if구문의 반환 값

### 빈 컨테이너 (리스트, 튜플, 사전, 집합 등) : False

```python
if []:        # 빈 리스트
    print("True")  # 실행되지 않음
if {}:        # 빈 사전
    print("True")  # 실행되지 않음
if set():     # 빈 집합
    print("True")  # 실행되지 않음
if ():        # 빈 튜플
    print("True")  # 실행되지 않음

```

### 비어있지 않은 컨테이너 (리스트, 튜플, 사전, 집합 등) : True

```python
if [1, 2, 3]:  # 비어 있지 않은 리스트
    print("True")  # 실행됨
if {'a': 1}:   # 비어 있지 않은 사전
    print("True")  # 실행됨
if {1, 2, 3}:  # 비어 있지 않은 집합
    print("True")  # 실행됨
if (1, 2, 3):  # 비어 있지 않은 튜플
    print("True")  # 실행됨

```

### 숫자: 0(False), else(True)

```python
if 0:         # 숫자 0
    print("True")  # 실행되지 않음
if 1:         # 숫자 1
    print("True")  # 실행됨
if -1:        # 숫자 -1
    print("True")  # 실행됨
if 3.14:      # 숫자 3.14
    print("True")  # 실행됨

```

### 문자열: empty(False), else(True)

```python
if 0:         # 숫자 0
    print("True")  # 실행되지 않음
if 1:         # 숫자 1
    print("True")  # 실행됨
if -1:        # 숫자 -1
    print("True")  # 실행됨
if 3.14:      # 숫자 3.14
    print("True")  # 실행됨

```

### None: False

```python
if None:
    print("True")  # 실행되지 않음

```

---

- 기존코드
    
    ```python
    import sys
    input = sys.stdin.read
    
    #데이터 받기
    data = list(map(int, input.split()))
    N = data[0]
    TARGET = data[1]
    A = data[2 : N+2]
    
    #각 인자 초기화
    start = 0
    current_sum = 0
    array_length = 0
    results = []
    
    #배열 탐색
    for end in range(N):
        current_sum += A[end]
    
        while current_sum > 15 and end <= N:
            start += 1
            current_sum = 0
    
        if current_sum == TARGET:
            array_length = end - start + 1
            results.append(array_length)
    
    print(results)
    ```
    

---

[질문저장소](https://www.notion.so/ab989e89b2fc475f8c1bd65c9e1ead38?pvs=21)
