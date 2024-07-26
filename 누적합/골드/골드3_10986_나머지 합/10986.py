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
