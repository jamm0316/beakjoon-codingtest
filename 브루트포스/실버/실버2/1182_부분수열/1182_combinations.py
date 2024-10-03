import sys
from itertools import combinations

input_data = sys.stdin.read()
lines = input_data.splitlines()
N, S = map(int, lines[0].split())
sequence = list(map(int, lines[1].split()))

result = 0

for r in range(1, N + 1):
    for combination in combinations(sequence, r):
        if sum(combination) == S:
            result += 1

print(result)
