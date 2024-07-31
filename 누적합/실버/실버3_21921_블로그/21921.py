import sys

input = sys.stdin.read()
data = list(map(int, input.split()))

# 입력값 받기
N = data[0]  # 블로그 운영일수
X = data[1]  # 윈도우 범위 
A = data[2:]  # 날짜별 유입수

# 변수 선언
result = 0  # 최대 유입 수
count = {}  # 유입 수 날짜

# prefix_sum 구축
prefix_sum = [0] * N
prefix_sum[0] = A[0]
for i in range(1, N):
    prefix_sum[i] = A[i] + prefix_sum[i - 1]

# 슬라이딩 윈도우
for end in range(X - 1, N):
    # current_sum 초기화 및 업데이트
    if end == X - 1:
        current_sum = prefix_sum[end]
    else:
        current_sum = prefix_sum[end] - prefix_sum[end - X]
    # count_dictionary 업데이트    
    if current_sum not in count:
        count[current_sum] = 1
    else:
        count[current_sum] += 1
    # result 업데이트    
    if current_sum > result:
        result = current_sum

# 결과 출력
if result != 0 and count != 0:
    print(result)
    print(count[result])
else:
    print("SAD")

