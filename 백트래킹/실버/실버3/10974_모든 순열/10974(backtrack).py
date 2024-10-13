def backtrack(N, sequence, visited):
    if len(sequence) == N:
        print(' '.join(map(str, sequence)))
        return
    
    for i in range(N):
        if not visited[i]:
            visited[i] = True
            sequence.append(i + 1)
            backtrack(N, sequence, visited)

            sequence.pop()
            visited[i] = False

if __name__ == '__main__':
    N = int(input())
    visited = [False] * N
    backtrack(N, [], visited)
