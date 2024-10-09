import sys

def backtrack(N, M, sequence, numbers, visited):
    if len(sequence) == M:
        print(' '.join(map(str, sequence)))
        return
    
    last_used = None  # 마지막에 사용한 숫자 저장 변수
    for i in range(N):
        if not visited[i] and numbers[i] != last_used:
            sequence.append(numbers[i])
            visited[i] = True
            last_used = numbers[i]
            backtrack(N, M, sequence, numbers, visited)

            visited[i] = False
            sequence.pop()

def read_data(input_data):
    lines = [list(map(int, line.split())) for line in input_data.splitlines()]
    N, M = lines[0]
    numbers = sorted(lines[1])
    return N, M, numbers

if __name__ == '__main__':
    input_data = sys.stdin.read()
    N, M, numbers = read_data(input_data)
    visited = [False] * N
    backtrack(N, M, [], numbers, visited)
