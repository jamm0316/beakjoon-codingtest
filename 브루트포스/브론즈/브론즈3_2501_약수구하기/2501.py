def find_min_number(N, K):
    count = 0
    for i in range(1, N + 1):
        if N % i == 0:
            count += 1
        if count == K:
            return i
    return 0

N, K = map(int, input().split())
print(find_min_number(N, K))
