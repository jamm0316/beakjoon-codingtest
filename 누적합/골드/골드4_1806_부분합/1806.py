import sys
input = sys.stdin.read()

#데이터 받기
data = list(map(int, input.split()))
N = data[0]
TARGET = data[1]
A = data[2:]

#각 인자 초기화
start = 0
current_sum = 0
array_length = 0
results = []

#배열 탐색
for end in range(N):
    current_sum += A[end]

    while current_sum >= TARGET:
        array_length = end - start + 1
        results.append(array_length)
        current_sum -= A[start]
        start += 1

#배열의 최소길이 반환
if results:
    print(min(results))
else:
    print(0)
