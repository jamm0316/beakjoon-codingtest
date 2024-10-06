def backtrack(N, M, sequence, start):
    if len(sequence) == M:
        print(' '.join(map(str, sequence)))
        return

    for i in range(start, N + 1):
        sequence.append(i)
        backtrack(N, M, sequence, i)

        sequence.pop()

input_data = '3 3'
N, M = map(int, input_data.split())
backtrack(N, M, [], 1)
