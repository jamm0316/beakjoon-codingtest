# 입력 받기
N = int(input())  # 남은 날 수
T = []
P = []
for _ in range(N):
    t, p = map(int, input().split())
    T.append(t)
    P.append(p)

# DP 배열 선언, N+1번째 날에 퇴사하므로 N+1 크기로 선언
dp = [0] * (N + 1)

# 뒤에서부터 DP 계산
for i in range(N - 1, -1, -1):
    if i + T[i] <= N:  # 상담을 할 수 있는 경우
        dp[i] = max(dp[i + 1], P[i] + dp[i + T[i]])  # 상담을 하지 않는 경우 vs 상담을 하는 경우
    else:  # 상담을 할 수 없는 경우
        dp[i] = dp[i + 1]  # 상담을 하지 않는 경우만 가능

# 첫 번째 날부터 시작하는 최대 수익 출력
print(dp[0])
