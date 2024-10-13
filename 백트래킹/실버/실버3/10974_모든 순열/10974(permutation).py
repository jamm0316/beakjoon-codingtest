from itertools import permutations

def main(N):
    numbers = permutations(N)
    for perm in numbers:
        print(' '.join(map(str, perm)))

if __name__ == '__main__':
    N = [i + 1 for i in range(int(input()))]
    main(N)
