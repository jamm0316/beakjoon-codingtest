import sys

def backtrack(N, M, sequence, numbers):
    if len(sequence) == M:
        print(' '.join(map(str, sequence)))
        return
    
    for i in range(N):
        sequence.append(numbers[i])
        backtrack(N, M, sequence, numbers)

        sequence.pop()

input_data = sys.stdin.read()
lines = [list(map(int, line.split())) for line in input_data.splitlines()]
N, M = lines[0]
numbers = sorted(lines[1])
backtrack(N, M, [], numbers)
