import sys

def backtrack(N, M, sequence):
    if len(sequence) == M:
        print(' '.join(map(str, sequence)))
        return
    
    for i in range(1, N + 1):
        sequence.append(i)
        backtrack(N, M, sequence)

        sequence.pop()

input_data = sys.stdin.read()
N, M = map(int, input_data.split())
backtrack(N, M, [])
