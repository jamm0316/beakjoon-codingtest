
page link : [https://www.acmicpc.net/problem/2559](https://www.acmicpc.net/problem/2559)

# 풀이전략

💡 풀이전략

1. 입력읽기
2. 현재 합, 최대 값 초기화
3. 슬라이딩 윈도우 이용하여 순회

## 사용된 알고리즘

🎨 algoritm

- 슬라이딩 윈도우

---

## pseudo code

```python
1. 입력값 읽기
    map(int, input().split()) 으로 입력 값 읽기
    N: 배열의 길이, K: 윈도우 갯수, A: 배열
2. 현재 합, 최대 값 초기화
    - currnet_sum은 K이전까지의 합
    - max_sum = current_sum
3. 슬라이딩 윈도우 이용하여 순회
    - K ~ N까지 순회
	    - current_sum = A[i] - A[i - K]
	    - current_sum > max_sum 이면 max_sum 교체
	    - return max_sum
```

---

# code

## Python

```python
# 입력 받기
N, K = map(int, input().split())
temperatures = list(map(int, input().split()))

def max_temperature_sum(N, K, temperatures):
    # 처음 K일의 합을 계산
    current_sum = sum(temperatures[:K])
    max_sum = current_sum
    
    # 슬라이딩 윈도우 기법 적용
    for i in range(K, N):
        current_sum += temperatures[i] - temperatures[i - K]
        if current_sum > max_sum:
            max_sum = current_sum
    
    return max_sum

# 결과 출력
print(max_temperature_sum(N, K, temperatures))
```

## 해결한 오류

### 1. 공간복잡도 최적화

### 첫 번째 코드 (프리픽스 합과 슬라이딩 윈도우)

```python
# 입력 받기
N, K = map(int, input().split())
A = list(map(int, input().split()))

def sliding_window(N, K, A):
    # prefix_sum 배열 생성 및 초기화
    prefix_sum = [0] * (N + 1)
    for i in range(1, N + 1):
        prefix_sum[i] = A[i - 1] + prefix_sum[i - 1]
      
    # 연속적인 K일의 합을 저장할 리스트
    cumulative_temperature = []
    start = 0
    
    # 슬라이딩 윈도우 적용
    for i in range(N - K + 1):
        current_sum = prefix_sum[start + K] - prefix_sum[start]
        cumulative_temperature.append(current_sum)
        start += 1
      
    # 최대 합 반환
    return max(cumulative_temperature)
  
# 결과 출력
print(sliding_window(N, K, A))
```

### 두 번째 코드 (슬라이딩 윈도우 기법)

```python
# 입력 받기
N, K = map(int, input().split())
temperatures = list(map(int, input().split()))

def max_temperature_sum(N, K, temperatures):
    # 처음 K일의 합을 계산
    current_sum = sum(temperatures[:K])
    max_sum = current_sum
    
    # 슬라이딩 윈도우 기법 적용
    for i in range(K, N):
        current_sum += temperatures[i] - temperatures[i - K]
        if current_sum > max_sum:
            max_sum = current_sum
    
    return max_sum

# 결과 출력
print(max_temperature_sum(N, K, temperatures))
```

### 비교

1. **시간 복잡도**: 두 코드 모두 시간 복잡도 O(N)으로 동일
    - **첫 번째 코드**:
        - prefix_sum 계산: O(N)
        - prefix_sum을 이용한 K일 합 계산: O(N - K + 1)
        - 전체 시간 복잡도: O(N)
    - **두 번째 코드**:
        - 초기 K일의 합 계산: O(K)
        - 슬라이딩 윈도우 적용: O(N - K)
        - 전체 시간 복잡도: O(N)
2. **공간 복잡도**:
    - **첫 번째 코드**:
        - prefix_sum 배열 사용: O(N)
        - 누적 온도 리스트 사용: O(N - K + 1)
        - 전체 공간 복잡도: O(N)
    - **두 번째 코드**:
        - 입력 리스트를 제외한 추가적인 공간 사용 없음: O(1)
    
    두 번째 코드는 추가적인 공간을 거의 사용하지 않는 반면,
    첫 번째 코드는 prefix_sum 배열과 누적 온도 리스트를 사용하여 추가 공간을 사용.
    

### 결론

1. **공간 복잡도**: 첫 번째 코드는 입력 리스트 외에 추가 공간을 거의 사용하지 않아 메모리 효율적
2. **간결성**: 첫 번째 코드는 코드가 더 간결하고 이해하기 쉬우며, 구현이 간단

따라서 동일한 시간 복잡도를 가지지만, 공간 사용 면에서 첫 번째 코드가 더 효율적

---

- 기존코드
    
    ```python
    # 입력 받기
    N, K = map(int, input().split())
    A = list(map(int, input().split()))
    
    def sliding_window(N, K, A):
        # prefix_sum 배열 생성 및 초기화
        prefix_sum = [0] * (N + 1)
        for i in range(1, N + 1):
            prefix_sum[i] = A[i - 1] + prefix_sum[i - 1]
          
        # 연속적인 K일의 합을 저장할 리스트
        cumulative_temperature = []
        start = 0
        
        # 슬라이딩 윈도우 적용
        for i in range(N - K + 1):
            current_sum = prefix_sum[start + K] - prefix_sum[start]
            cumulative_temperature.append(current_sum)
            start += 1
          
        # 최대 합 반환
        return max(cumulative_temperature)
      
    # 결과 출력
    print(sliding_window(N, K, A))
    ```
    

---

[질문저장소](https://www.notion.so/b5d60a6ebb504546b81729c07595e7aa?pvs=21)
