page link : https://www.acmicpc.net/problem/2003
---

# 풀이전략

- [Two pointer algorithm](https://www.notion.so/jamm0316/Two-pointer-9112243f0d7a4754b2069b6983039248?pvs=4)
    - 연속적인 부분 배열 합 계산
    - 큰 입력크기
        - O(N^2) < O(N)이 효율적
    - 두개의 포인터로 간단한 구현과 적은 메모리 사용

---

# pseudo code

```python
1. 변수 초기화
    1. start 를 0으로 설정(윈도우의 시작 위치)
    2. current_sum을 0으로 설저 (현재 윈도우의 합)
    3. count를 0으로 설정 (조건을 만족하는 부분 배열의 수)
2. 슬라이딩 윈도우 반복
    1. end 포인터를 배열의 시작에서 끝까지 이동.
        1. A[end]값을 currnet_sum에 더함
        2. current_sum이 M보다 큰경우:
            - current_sum이 M 이하가 될 때까지 satrt 포인터를 오른쪽으로 이동
            - 이동하면서 A[start] 값을 current_sum에서 뺌
        3. current_sum이 M과 같으면 count를 1 증가시킴
```

---

# code

## Python

```python
# 함수정의
N, M = map(int, input().split())
A = list(map(int, input().split())

def two_pointer(N, M, A):
    # 변수 초기화
    start = 0
    current_sum = 0
    count = 0
    
    # 슬라이딩 윈도우
    for end in range(N):
        current_sum += A[end]  # 마지막에 숫자 더하면서 나아가기
        
        while currnet_sum > M and start <= end:
	          currnet_sum -= A[start]
	          start += 1
	          
	      if current_sum == M:
	          count += 1
        
    return count

print(two_pointer(N, M, A))
```

## 해결한 오류

### 1. map함수 반환값 수정

- map함수의 경우 객체의 주솟값을 반환하므로, list()함수를 통해 배열의 형태로 변환해 주어야 데이터로써 사용이 가능하다.

## QnA

[질문저장소](https://www.notion.so/a4479cecccbe4a4189bace9ca9dea888?pvs=21)

---
