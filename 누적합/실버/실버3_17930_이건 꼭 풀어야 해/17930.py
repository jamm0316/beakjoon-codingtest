import sys

input = sys.stdin.read()
lines = input.split('\n')

N, Q = list(map(int, lines[0].split()))
A = list(map(int, lines[1].split()))
A.sort()

queries = []
for line in range(2, Q + 2):
    queries.append(list(map(int, lines[line].split())))

prefix_sum = [0] * (N + 1)
for i in range(1, N + 1):
    prefix_sum[i] = A[i - 1] + prefix_sum[i - 1]

results = []
for i, j in queries:
    result = prefix_sum[j] - prefix_sum[i - 1]
    results.append(result)

for result in results:
    print(result)
