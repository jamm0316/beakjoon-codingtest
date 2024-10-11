import sys
from itertools import permutations

def make_permutation(numbers):
    return permutations(numbers)

def print_max_value():
    permutations_list = make_permutation(numbers)
    max_value = calculate_permutation(N, permutations_list)
    print(max_value)

def calculate_permutation(N, permutations_list):
    max_value = 0
    for perm in permutations_list:
        total = 0
        for i in range(N - 1):
            total += abs(perm[i] - perm[i + 1])
        max_value = max(max_value, total)
    return max_value

def read_data(input_data):
    lines = [list(map(int, line.split())) for line in input_data.splitlines()]
    N = lines[0][0]
    numbers = sorted(lines[1], reverse = True)
    return N, numbers

if __name__ == '__main__':
    input_data = sys.stdin.read()
    N, numbers = read_data(input_data)
    print_max_value()
