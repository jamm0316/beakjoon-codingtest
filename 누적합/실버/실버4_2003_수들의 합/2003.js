M, N = map(int, input().split())
A = list(map(int, input().split()))

def two_pointer(M, N, A):
    start = 0
    current_sum = 0
    count = 0
    
    for end in range(M):
        current_sum += A[end]
        
        while current_sum > N and start <= end:
            current_sum -= A[start]
            start += 1
        
        if current_sum == N:
            count += 1
    return count

print(two_pointer(M, N, A))
