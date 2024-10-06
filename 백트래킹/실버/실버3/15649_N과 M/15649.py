import sys

def backtrack(N, M, sequence, visited):
    if len(sequence) == M:
        print(' '.join(map(str, sequence)))
        return
    
    for i in range(1, N + 1):
        if not visited[i]:
            visited[i] = True
            sequence.append(i)
            backtrack(N, M, sequence, visited)

            sequence.pop()
            visited[i] = False

input_data = sys.stdin.read()
N, M = map(int, input_data.split())
visited = [False] * (N + 1)
backtrack(N, M, [], visited)
