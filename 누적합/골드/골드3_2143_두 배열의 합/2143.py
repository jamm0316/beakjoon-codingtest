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
