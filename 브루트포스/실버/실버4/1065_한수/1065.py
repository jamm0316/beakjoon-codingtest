def count_hansu(N):
    if N <= 99:
        return N
        
    count = 99
    for i in range(100, N + 1):
        digit = list(map(int, str(i)))

        if digit[1] - digit[0] == digit[2] - digit[1]:
            count += 1
    return count

N = int(input())
print(count_hansu(N))
