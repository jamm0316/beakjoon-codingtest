import sys

def backtrack(N, M, sequence, numbers, start):
    if len(sequence) == M:
        print(" ".join(map(str, sequence)))
        return
    
    for i in range(start, N):
        sequence.append(numbers[i])
        start += 1
        backtrack(N, M, sequence, numbers, start)
        
        sequence.pop()
    


def read_data(input_data):
    lines = [list(map(int, line.split())) for line in input_data.splitlines()]
    N, M = lines[0]
    numbers = sorted(lines[1])
    return N, M, numbers

if __name__ == '__main__':
    input_data = sys.stdin.read()
    N, M, numbers = read_data(input_data)
    backtrack(N, M, [], numbers, 0)
