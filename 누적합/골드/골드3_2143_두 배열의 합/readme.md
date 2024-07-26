page link : [https://www.acmicpc.net/problem/2143](https://www.acmicpc.net/problem/2143)

---

# 💡 풀이전략
1. **두 배열의 모든 부 배열 합 계산**:
    1. 배열 A와 B 각각에 대해 모든 부 배열의 합을 계산하고 그 빈도를 저장.
    2. 시간복잡도: O(N^2) + O(M^2)
2. **부 배열 합의 빈도 계산:**
    1. 두 배열의 부 배열 합 리스트에서 각 합이 나타나는 빈도를 계산.
    2. 시간복잡도: O(N^2) + O(M^2)
3. **두 배열의 부 배열 합 빈도를 사용하여 합이 T가 되는 쌍의 수 찾기:**
    1. A배열의 각 부 배열 합에 대해 B 배열에서 (T - 해당 부 배열 합)이 존재하는 지 학인
    2. 시간복잡도: O(N^2) + O(M^2)

## 🎨 사용된 알고리즘
- **누적합(prefix_sum):** 각 배열의 모든 부 배열의 합을 계산하여 저장
- **해시맵(hash map)**: 각 부 배열 합의 빈도를 기록하여 빠르게 검색

---

## pseudo code

---

```python
1. 입력 받기
T = 두 배열의 합
N = A배열의 길이
A = A배열
M = B배열의 길이
B = B배열

2. 변수 초기화
count = 0

3. A와 B 배열의 모든 부 배열 합 계산하기
함수 find_subarray_sums(arr):
    n = 배열의 길이(arr)
    subarray_sums = 빈 리스트
    반복 (i = 0 to n-1):
        current_sum = 0
        반복 (j = i to n-1):
            current_sum += arr[j]
            subarray_sums에 current_sum 추가
    반환 subarray_sums

A_sums = find_subarray_sums(A)
B_sums = find_subarray_sums(B)

4. A_sums와 B_sums의 빈도 계산
A_count = Counter(A_sums)
B_count = Counter(B_sums)

5. 합이 T가 되는 쌍의 수 찾기
반복 (a_sum in A_count):
    만약 (T - a_sum) in B_count:
        count += A_count[a_sum] * B_count[T - a_sum]

결과 출력(count)
```

# code

## Python

```python
from collections import Counter

# 1. 입력 받기
T = int(input())
N = int(input())
A = list(map(int, input().split()))
M = int(input())
B = list(map(int, input().split()))

# 2. 변수 초기화
count = 0

# 3. A와 B 배열의 모든 부 배열 합 계산하기
def find_subarray_sums(arr):
    n = len(arr)
    subarray_sums = []
    for i in range(n):
        current_sum = 0
        for j in range(i, n):
            current_sum += arr[j]
            subarray_sums.append(current_sum)
    return subarray_sums

# A와 B의 모든 부 배열 합 계산
A_sums = find_subarray_sums(A)
B_sums = find_subarray_sums(B)

# 4. A_sums와 B_sums의 빈도 계산
A_count = Counter(A_sums)
B_count = Counter(B_sums)

# 5. 합이 T가 되는 쌍의 수 찾기
for a_sum in A_count:
    if (T - a_sum) in B_count:
        count += A_count[a_sum] * B_count[T - a_sum]

# 결과 출력
print(count)
```
---

[질문저장소](https://www.notion.so/f2e8dd1dc8214ee7920a31049c8b3da6?pvs=21)
