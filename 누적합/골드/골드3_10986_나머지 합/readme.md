page link : [https://www.acmicpc.net/problem/10986](https://www.acmicpc.net/problem/10986)

---

# 💡 풀이전략
- 정답 : 시간복잡도: O(N)
    1. 입력 받기
        1. N: 배열의 길이, M: 나누는 수, A: 배열
    2. 변수 초기화
        - prefix_sum = [0] * N
        - remainder_count = [0] * M
        - count = 0
    3. prefix_sum 구축
        
        prefix_sum[0] = A[0]
        for i → 1 ~ n - 1:
        prefix_sum[i] = A[i] + prefix_sum[i - 1]
        
    4. remainder_count 구축
        
        for i → 0 ~ n - 1:
        remainder = prefix_sum[i] % M
        if(remainder == 0) count += 1
        
    5. remainder_count 순회
        
        for i → 0 ~ m-1:
        remainder_count[i]에서 2가지를 뽑는 경우의 수 count에 더하기

- 오답 : 시간복잡도: O(N^2)
    1. 인자 받기
        1. N: 배열의 길이 K: 나누는 수 A: 배열
    2. 변수 초기화
        1. start = 0
        2. current_sum = 0
        3. count = 0
    3. for in, while 순환
        1. current_sum += A[end]
        2. while end ≤ N
            1. current_sum - A[start]
        3. if current_sum % K == 0
            1. count += 1

## 🎨 사용된 알고리즘
누적합(prefix_sum)

---

# code

## Python

```python
import sys
input = sys.stdin.read()
data = list(map(int, input.split()))

#입력값 읽기
N = data[0]
M = data[1]
A = data[2:]

#변수 초기화
prefix_sum = [0] * N
remainder_count = [0] * M
count = 0

#누적합 배열 구하기
prefix_sum[0] = A[0]
for i in range(1, N):
    prefix_sum[i] = A[i] + prefix_sum[i - 1]

#나머지 배열 구하기
for i in range(N):
    remainder = prefix_sum[i] % M
    if remainder == 0:
        count += 1
    remainder_count[remainder] += 1

for j in range(M):
    if remainder_count[j] > 1:
        count += (remainder_count[j] * (remainder_count[j] - 1)) // 2
    
print(count)
```

## 해결한 오류

### 1. 시간초과(시간복잡도 O(N^2))

#### Step 1. 컴퓨터는 초당 10^8 ~ 10^9 명령어 처리 가능

- 컴퓨터가 1초안에 처리할 수 있는 경우의 수
    - 컴퓨터 성능에 따라 다르지만 대략 **초당 10^8 ~ 10^9 명령어 처리가능**
- 위 문제의 경우 **배열의 최대 크기는 10^6**이며, **시간제한은 1초**이다.
    - 즉, 선형 탐색 : O(N)만 1초에 처리 가능하며,
    이중루프 탐색: O(N^2)시 10^12이 되므로 1초를 넘어간다.

```python
# 반복문 순환 (시간복잡도 O(N^2))
while start <= N:
    for end in range(start + 1, N + 1):
        if (prefix_sum[end] - prefix_sum[start]) % K == 0:
            count += 1
    start += 1

print(count)
```

따라서, **시간복잡도를 O(N)으로 줄일 아이디어 필요**

---

#### Step 2. 시간복잡도를 O(N)으로 줄일 아이디어

> **누적합의 나머지의 값이 같을 때, 그 구간의 합의 나머지는 0이다.**
> 
- 이게 무슨말인지 도식으로 알아보자.
    
    ```python
    # 문제에서 주어진 인자
    **주어진 배열 = [1, 2, 3, 1, 2]
    나누려는 수 = 3**
    
    # 새로 생성한 배열
    누적합 = [1, 3, 6, 7, 9]
    누적합의 나머지 = [1, 0, 0, 1, 0]
    
    # 예시를 통한 확인
    **누적합의 나머지의 값이 같을 때,**
    '누적합의 나머지[0]'과 '누적합의 나머지[4]'는 서로 1로 같을 때,
    
    **그 구간의 합의 나머지는 0이다.**
    **그 구간의 합** = (주어진 배열[1] + 주어진 배열[2] + 주어진 배열[3])
    **나머지** = **그 구간의 합** % 3
         = (2 + 3 + 1) % 3 = 6 % 3
         = 0
    ```
    
- 위를 통해 **누적합의 나머지만 순회**하더라도, 나머지가 0인 경우의 수를 구할 수 있다.
    - 즉, 시간복잡도를 O(N)으로 줄일 수 있다.

---

#### Step 3. 수학 개념으로 경우의 수 구하기

> **누적합의 나머지를 순회하며 0이 되는 경우의 수 구하기(콤비네이션_조합)**
> 
1. 누적합 자체로 0이 되는 경우의 수 세기
2. 누적합의 나머지 중 같은 값이 2개 이상이면, 두 개의 인덱스 **구간의 합 % 나누려는 수 = 0**이므로, 2개 이상의 인덱스로 만들 수 있는 구간의 경우의 수 세기
    1. 콤비네이션(조합) nCm ⇒ n개의 숫자 중 m개가 되는 숫자의 경우의 수를 구하는 방법
        
        $$
        nCr = \frac {n!}{(n-r)!r!}
        $$
        
    2. 예시를 보면 이해가 빠를 거다.
        
        ex) `remainder = [1, 0, 0, 1, 0]`의 경우 3C2 ⇒ ‘0’이란 숫자 3개 중 2개가 되는 경우의 수
        1번째: `remainder[1]~remainder[2]`
        2번째: `remainder[2]~remainder[4]`
        3번째: `remainder[1]~remainder[4]`
        
        $$
        _3C_2 = \frac {3*2}{2*1}=3
        $$
        
        > tip. `r!`을 분모에, `n!`을 `r의 갯수`만큼 분자에 배치하면 된다.
        > 

---

#### Step 4. 구현하기

1. 누적합 배열 구하기
2. 나머지 배열 구하기

```python
#누적합 배열 구하기
prefix_sum[0] = A[0]
for i in range(1, N):
    prefix_sum[i] = A[i] + prefix_sum[i - 1]

#나머지 배열 구하기
for i in range(N):
    remainder = prefix_sum[i] % M
    # 누적합 자체로 0이 되는 것은 바로 count를 센다.
    if remainder == 0:
        count += 1
    # 인덱스를 % 값으로 사용하고, 인덱스에 따른 값을 갯수로 센다.
    remainder_count[remainder] += 1
    
for j in range(M):
    if remainder_count[j] > 1:
		    # n개중 2개의 경우의 수를 구하는 콤비네이션 구현.
        count += (remainder_count[j] * (remainder_count[j] - 1)) // 2
    
print(count)
```

---

- 기존코드
    
    ```python
    import sys
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    
    # 인자 정의
    N = data[0]
    K = data[1]
    A = data[2:]
    
    # 변수 초기화
    start = 0
    current_sum = 0
    count = 0
    
    # 누적합 배열 만들기
    prefix_sum = [0] * (N + 1)
    for i in range(1, N + 1):
        prefix_sum[i] = A[i - 1] + prefix_sum[i - 1]
    
    # 반복문 순환
    while start <= N:
        for end in range(start + 1, N + 1):
            if (prefix_sum[end] - prefix_sum[start]) % K == 0:
                count += 1
        start += 1
    
    print(count)
    ```
    

---

[질문저장소](https://www.notion.so/de769bb6aee0459e84c355dc6c08c675?pvs=21)
