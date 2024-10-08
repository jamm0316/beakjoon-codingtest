import sys

def backtrack(N, M, sequence, numbers, visited, start):
    if len(sequence) == M:
        print(' '.join(map(str, sequence)))
        return
    
    for i in range(start, N):
        sequence.append(numbers[i])
        backtrack(N, M, sequence, numbers, visited, i)
        sequence.pop()

def parse_data(input_data):
    lines = input_data.splitlines()
    N, M = map(int, lines[0].split())
    numbers = sorted(list(map(int, lines[1].split())))
    return N, M, numbers

if __name__ == '__main__':
    input_data = sys.stdin.read()
    N, M, numbers = parse_data(input_data)
    visited = [False] * N
    backtrack(N, M, [], numbers, visited, 0)
