
page link : [https://www.acmicpc.net/problem/21921](https://www.acmicpc.net/problem/21921)

---

# 💡 풀이전략

1. 슬라이딩 윈도우를 사용
    1. 윈도우 범위만큼 current_sum을 사용해 합을 구함
    2. max_sum에 currnet_sum값을 넣고 max_sum과 current_sum을 비교해가며 더 높은 값을 max_sum에 넣음
    3. max_count 1로 초기화(무조건 1번은 나오므로)
        1. current_sum == max_sum일 경우 max_count += 1
    4. 마지막 순환이 끝나면 조건문
        1. if max_sum == 0 → “SAD”
        else: max_sum, max_count 출력

## 🎨 사용된 알고리즘

**슬라이딩 윈도우**

---

## pseudo code

```plaintext
1. 입력 읽기:
    1. N: 블로그 시작일수
    2. X: 윈도우 범위
    3. A: 날짜별 유입량
2. 초기화:
    1. current_sum을 첫 X일의 합으로 초기화
    2. max_sum을 current_sum으로 초기화
    3. max_count를 1로 초기화
3. 슬라이딩 윈도우 계산:
    1. for i X ~ N:
        1. current_sum 갱신 (새로 추가되는 값 더하고, 제외되는 값 뺌)
        2. if current_sum > max_sum:
            • max_sum 갱신
            • max_count를 1로 설정
        3. elif current_sum == max_sum:
            • max_count 증가
        4. 결과 출력:
            1. if max_sum == 0:
                • “SAD” 출력
            2. else:
                • max_sum 출력
                • max_count 출력
```

---

# code

## Python

```python
import sys

input = sys.stdin.read
data = list(map(int, input().split()))

# 입력값 받기
N = data[0]
X = data[1]
A = data[2:]

# 초기화
current_sum = sum(A[:X])
max_sum = current_sum
max_count = 1

# 슬라이딩 윈도우
for i in range(X, N):
    current_sum = current_sum + A[i] - A[i - X]
    if current_sum > max_sum:
        max_sum = current_sum
        max_count = 1
    elif current_sum == max_sum:
        max_count += 1

# 결과 출력
if max_sum == 0:
    print("SAD")
else:
    print(max_sum)
    print(max_count)
```

## 해결한 오류

### 1. 시간복잡도 최적화

- 기존의 경우 시간복잡도 O(N) + O(N)으로, prefix_sum을 불필요하게 사용하여 코드가 복잡해짐

```python
...
# prefix_sum 구축
prefix_sum = [0] * N
prefix_sum[0] = A[0]
for i in range(1, N):
    prefix_sum[i] = A[i] + prefix_sum[i - 1]

# 슬라이딩 윈도우
for end in range(X - 1, N):
    # current_sum 초기화 및 업데이트
...
```

- **슬라이딩 윈도우 기법 사용**
    - Prefix Sum을 사용하여 구간 합을 구하는 방식 대신 슬라이딩 윈도우 기법을 사용하여 윈도우의 합을 유지하고 이동하면서 합을 갱신.
    - 매 윈도우 이동 시 새로운 요소를 추가하고, 이전 요소를 제거하여 합을 계산하는 방식으로 시간 복잡도를 O(N)으로 줄임.
    - 이 방식은 각 요소를 한 번씩만 확인하고, 합을 갱신하는 연산
    
    ```python
    ...
    # 슬라이딩 윈도우
    for i in range(X, N):
        current_sum = current_sum + A[i] - A[i - X]
        if current_sum > max_sum:
            max_sum = current_sum
            max_count = 1
        elif current_sum == max_sum:
            max_count += 1
    
    # 결과 출력
    ...
    
    ```
    

### 2. 메모리 사용 최적화

- **딕셔너리 대신 변수를 사용**: count 딕셔너리 대신 max_count로 최대 방문자 수를 저장하는 변수를 사용하여 메모리 사용을 줄이고 가독성 높임.

---

- 기존코드
    
    ```python
    import sys
    
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    
    # 입력값 받기
    N = data[0]  # 블로그 운영일수
    X = data[1]  # 윈도우 범위 
    A = data[2:]  # 날짜별 유입수
    
    # 변수 선언
    result = 0  # 최대 유입 수
    count = {}  # 유입 수 날짜
    
    # prefix_sum 구축
    prefix_sum = [0] * N
    prefix_sum[0] = A[0]
    for i in range(1, N):
        prefix_sum[i] = A[i] + prefix_sum[i - 1]
    
    # 슬라이딩 윈도우
    for end in range(X - 1, N):
        # current_sum 초기화 및 업데이트
        if end == X - 1:
            current_sum = prefix_sum[end]
        else:
            current_sum = prefix_sum[end] - prefix_sum[end - X]
        # count_dictionary 업데이트    
        if current_sum not in count:
            count[current_sum] = 1
        else:
            count[current_sum] += 1
        # result 업데이트    
        if current_sum > result:
            result = current_sum
    
    # 결과 출력
    if result != 0 and count != 0:
        print(result)
        print(count[result])
    else:
        print("SAD")
    
    ```
    

---
