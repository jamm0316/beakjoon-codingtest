# 입력 받기
N, K = map(int, input().split())
temperatures = list(map(int, input().split()))

def max_temperature_sum(N, K, temperatures):
    # 처음 K일의 합을 계산
    current_sum = sum(temperatures[:K])
    max_sum = current_sum
    
    # 슬라이딩 윈도우 기법 적용
    for i in range(K, N):
        current_sum += temperatures[i] - temperatures[i - K]
        if current_sum > max_sum:
            max_sum = current_sum
    
    return max_sum

# 결과 출력
print(max_temperature_sum(N, K, temperatures))
