# 백트래킹을 이용한 부분수열의 합 계산
def backtrack(idx, current_sum, count):
    global result
    
  
    if idx == N:
        if count > 0 and current_sum == S:
            result += 1
        return

    backtrack(idx + 1, current_sum + sequence[idx], count + 1)

    backtrack(idx + 1, current_sum, count)

N, S = map(int, input().split())
sequence = list(map(int, input().split()))
result = 0
backtrack(0, 0, 0)
print(result)
