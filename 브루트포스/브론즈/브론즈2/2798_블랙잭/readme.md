page link : [https://www.acmicpc.net/problem/2798](https://www.acmicpc.net/problem/2798)

---

# 💡 풀이전략

- 입력 받기
- 3중 반복문으로 카드 조합 탐색
    - N이 100이하이므로, 최대 100^3 = 1,000,000(1백만)번 연산 필요
    - 1초 연산 처리 1억(100,000,000)회를 표준으로 보기에 시간안에 처리 가능
- 합 계산 및 비교: 각 경우의 카드 세장의 합이 M을 넘지 않는지 확인하고, 넘지 않으면 그 값중 가장 큰 값 저장.
- 출력: 가장 큰 값 출력

## 🎨 사용된 알고리즘

> [!tip]
> Brute-Force: 완전 탐색

---

# code

## Python

```python
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
```
