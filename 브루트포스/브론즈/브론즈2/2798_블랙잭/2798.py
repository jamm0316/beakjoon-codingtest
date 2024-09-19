# 입력 받기
N, M = map(int, input().split())
cards = list(map(int, input().split()))

# 최대 합을 저장할 변수
max_sum = 0

# 세 장의 카드 선택하기 (3중 for문 사용)
for i in range(N):
    for j in range(i + 1, N):
        for k in range(j + 1, N):
            card_sum = cards[i] + cards[j] + cards[k]
            
            # M을 넘지 않는 카드 합 중에서 가장 큰 값을 찾는다.
            if card_sum <= M:
                max_sum = max(max_sum, card_sum)

# 결과 출력
print(max_sum)
