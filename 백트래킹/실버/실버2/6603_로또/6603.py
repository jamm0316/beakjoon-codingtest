import sys

def main(queries):
    for idx, query in enumerate(queries):
        if query[0] == 0:
            return
        lotto_list = query[1:]
        print_combinations(lotto_list)
        backtrack(6, [], lotto_list, 0)
        if idx < len(queries) - 2:
            print()

def print_combinations(lotto_list):
    sequence_length = 6
    backtrack(sequence_length, [], lotto_list, 0)

def backtrack(sequence_length, sequence, lotto_list, start):
    if len(sequence) == sequence_length:
        print(" ".join(map(str, sequence)))
        return

    for i in range(start, len(lotto_list)):
        sequence.append(lotto_list[i])
        backtrack(N, sequence, lotto_list, i + 1)
        sequence.pop()
    
def read_input():
    input_data = sys.stdin.read()
    lines = input_data.splitlines()
    queries = [list(map(int, line.split())) for line in lines]

if __name__ == "__main__":
    queries = read_intput()
    main(queries)
