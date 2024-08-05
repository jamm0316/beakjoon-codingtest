import sys

input = sys.stdin.read()
lines = input.split('\n')

# 입력값 받기
N, Q = list(map(int, lines[0].split()))
A = list(map(int, lines[1].split()))
queries = []
for line in range(2, Q + 2):
    query = list(map(int, lines[line].split()))
    queries.append(query)

# diff 구축
diff = [0] * (N + 1)
for a, b, k in queries:
    diff[a - 1] += k
    if b < N:
        diff[b] -= k

# 배열 업데이트
update = []
current_sum = 0
for i in range(N):
    current_sum += diff[i]
    update.append(A[i] + current_sum)

# 결과값 반환
result = ' '.join(map(str, update))
print(result)
