import sys

input = sys.stdin.read
lines = input().split('\n')

# 입력값 받기
Q = int(lines[0])  # 쿼리 개수
A = []
for i in range(0, Q * 2, 2):
    A.append(list(map(int, lines[2 + i].split())))

# 최소, 최대값 구하기
results = []
for case in A:
    min_value = min(case)
    max_value = max(case)
    results.append([min_value, max_value])

# 결과 출력
for result in results:
    print(' '.join(map(str, result)))
