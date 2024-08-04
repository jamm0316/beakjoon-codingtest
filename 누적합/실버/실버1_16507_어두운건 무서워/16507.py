import sys

input = sys.stdin.read()
lines = input.split('\n')

# 입력값 받기
R, C, K = list(map(int, lines[0].split()))
A = []
for line in range(1, R + 1):
    row = list(map(int, lines[line].split()))
    A.append(row)

queries = []
for line in range(R + 1, R + 1 + K):
    query = list(map(int, lines[line].split()))
    queries.append(query)

# prefix_sum 초기화
prefix_sum = [0] * (R + 1)
for i in range(R + 1):
    prefix_sum[i] = [0] * (C + 1)

# prefix_sum 구축
for i in range(1, R + 1):
    for j in range(1, C + 1):
        prefix_sum[i][j] = A[i - 1][j - 1] + prefix_sum[i - 1][j] + prefix_sum[i][j - 1] - prefix_sum[i - 1][j - 1]

# 쿼리별 부분합 구하기
results = []
for i, j, x, y in queries:
    partial_sum = prefix_sum[x][y] - prefix_sum[x][j - 1] - prefix_sum[i - 1][y] + prefix_sum[i - 1][j - 1]
    count = (x - i + 1) * (y - j + 1)
    result = partial_sum // count
    results.append(result)

# 결과값 반환
for result in results:
    print(result)
