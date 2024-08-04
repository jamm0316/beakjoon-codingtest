page link : [https://www.acmicpc.net/problem/2015](https://www.acmicpc.net/problem/2015)

---

# 💡 풀이전략
1. 배열의 누적합을 구한다. O(n)
2. 모듈러(%)를 통해 누적합과 구하고자하는 수의 나눗셈의 나머지를 구한 배열을 만든다. O(n)
3. 나머지가 0인 경우를 모두 센다(count +1)
4. 나머지가 1 이상인 경우 해시맵을 통해 2개 이상 나머지가 존재할시 +1을 센다.(count +1)

## 🎨 사용된 알고리즘
- prefix_sum(누적합): 배열을 순회하면서 현재까지의 누적합을 계산.
- 조건 만족 확인: 현재 누적합에서  K 를 뺀 값이 이전에 나타난 적이 있는지 해시맵을 통해 확인.
만약 있다면, 그 값의 빈도를 결과에 추가
- hash map(해시맵): 현재 누적합의 빈도를 해시맵에 업데이트.

---

## pseudo code

- 개선코드
    
    ```python
    1.	초기화
    	•	해시맵을 초기화하여 0의 빈도를 1로 설정 (초기 상태)
    	•	current_sum과 count_of_subarrays를 0으로 초기화
    2.	배열 순회
    	•	각 요소에 대해 현재 누적합을 업데이트
    	•	(current_sum - K)가 해시맵에 있는지 확인
    	•	있다면, 그 빈도만큼 부분합의 개수를 증가
    	•	현재 누적합을 해시맵에 업데이트
    3.	결과 반환
    	•	부분합의 개수를 반환
    ```
    
- 기존코드
    
    ```python
    1. 입력값 받기
    N = data[0]   # 배열의 수
    K = data[1]   # 찾고자 하는 수
    A = data[2:]  # 배열
    
    2. 변수 선언
    remainder = []
    remainder_count = []
    count = 0
    
    2. prefix_sum 구축
    prefix_sum[0] = A[0]
    for i 1 ~ N:
        prefix_sum[i] = A[i] - prefix_sum[i - 1]
    
    3. remainder 구축
    for i in prefix_sum:
        if K != 0:
    	      remainder = i % K
            remainder.append(remainder)
        else:
            remainder = prefix_sum
    
    4. 갯수 세기
    remainder_count = [0] * K
    for i in remainder:
        if i not in remainder_count:
            remainder_count[i] = 0
            
        if i == 0:
            count += 1
            remainder_count[i] += 1
        else:
            remainder_count[i] += 1
    
    for j 1 ~ K:
        if remainder_count[j] > 1:
        count += (reaminder_count[j] * remainder_count[j] - 1) // 2
        
    ```
    

---

# code

## Python

```python
def count_subarrays_with_sum_k(N, K, A):
    prefix_sum_count = {0: 1}  # Initialize hashmap with 0 sum occurring once
    current_sum = 0
    count_of_subarrays = 0

    for num in A:
        current_sum += num
        
        # Check if (current_sum - K) is present in the hashmap
        if (current_sum - K) in prefix_sum_count:
            count_of_subarrays += prefix_sum_count[current_sum - K]
        
        # Update the count of the current prefix sum in the hashmap
        if current_sum in prefix_sum_count:
            prefix_sum_count[current_sum] += 1
        else:
            prefix_sum_count[current_sum] = 1

    return count_of_subarrays

# Read input
import sys
input = sys.stdin.read
data = input().split()

N = int(data[0])
K = int(data[1])
A = list(map(int, data[2:]))

# Get the result and print it
result = count_subarrays_with_sum_k(N, K, A)
print(result)
```

---

- 기존코드
    
    ```python
    import sys
    
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    
    # 입력값 받기
    N = data[0]  # 배열의 수
    K = data[1]  # 찾고자 하는 수
    A = data[2:]  # 배열
    
    # 변수 선언
    remainder = []
    remainder_count = {}
    count = 0
    prefix_sum = [0] * N
    
    # prefix_sum 구축
    prefix_sum[0] = A[0]
    for i in range(1, N):
        prefix_sum[i] = A[i] + prefix_sum[i - 1]
    
    # remainder 구축
    for i in prefix_sum:
        if K != 0:
            x = i % K
            remainder.append(x)
        else:
            remainder = prefix_sum
    
    # hash_map 구축
    for i in remainder:
        if i not in remainder_count:
            remainder_count[i] = 0
            
        if i == 0:
            count += 1
    
    print(count)
    ```
