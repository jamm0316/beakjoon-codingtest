import sys
input = sys.stdin.read()
data = input.split('\n')

# 입력값 받기
N, M= list(map(int, data[0].split()))  # row, column
A = []
for i in range(1, N + 1):
    row = list(map(int, data[i].split()))
    A.append(row)

K = int(data[N + 1])

# prefix_sum 초기화
prefix_sum = [0] * (N + 1)  # row
for i in range(0, N + 1):  # column
    prefix_sum[i] = [0] * (M + 1)

# prefix_sum 구축
for i in range(1, N + 1):
    for j in range(1, M + 1):
        prefix_sum[i][j] = A[i - 1][j - 1] + prefix_sum[i - 1][j] + prefix_sum[i][j - 1] - prefix_sum[i - 1][j - 1]

# queries 값 정의
queries = []
for i in range(N + 2, N+ 2+ K):
    query = list(map(int, (data[i].split())))
    queries.append(query)

# query별 결과값 도출
for query in queries:
    i, j, x, y = query
    result = prefix_sum[x][y] - prefix_sum[i - 1][y] - prefix_sum[x][j - 1] + prefix_sum[i - 1][j - 1]
    print(result)
