import sys
input = sys.stdin.read()
data = list(map(int, input.split()))

# 입력값 정의
idx = 0
N = data[0]  #배열의 길이
K = data[1]  #쿼리 갯수
idx += 2

# 배열 생성
A = []
for i in range(N):
    row = data[idx : idx + N + 1]
    A.append(row)
    idx += N

# prefix_sum 구축
prefix_sum = [[0] * (N + 1) for _ in range(N + 1)]
for i in range(1, N + 1):
    for j in range(1, N + 1):
        prefix_sum[i][j] = A[i - 1][j - 1] + prefix_sum[i - 1][j] + prefix_sum[i][j - 1] - prefix_sum[i - 1][j - 1]

# 쿼리 정의
queries = []
for _ in range(K):
    query = data[idx : idx + 4]
    queries.append(query)
    idx += 4

# 계산 함수
def array_sum(prefix_sum, queries):
    results = []
    for i, j, x, y in queries:
        result = prefix_sum[x][y] - prefix_sum[i - 1][y] - prefix_sum[x][j - 1] + prefix_sum[i - 1][j - 1]
        results.append(result)
    return results

results = array_sum(prefix_sum, queries)
for result in results:
    print(result)
