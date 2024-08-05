import sys

input = sys.stdin.read()
lines = input.split('\n')

# 입력값 읽기
N = int(lines[0])
A = list(map(int, lines[1].split()))
K = int(lines[2])
queries = []
for line in range(3, 3 + K):
    query = list(map(int, lines[line].split()))
    queries.append(query)

# 변수 정의
mistake_array = [0] * (N + 1)
prefix_sum = [0] * (N + 1)

# mistake_array 구축
for i in range(1, N):
    if A[i - 1] > A[i]:
        mistake_array[i] += 1

# prefix_sum 구축
for i in range(1, N + 1):
    prefix_sum[i] = mistake_array[i] + prefix_sum[i - 1]

# query별 결과값 도출
results = []
for i, j in queries:
    result = prefix_sum[j - 1] - prefix_sum[i - 1]
    results.append(result)

for result in results:
    print(result)
