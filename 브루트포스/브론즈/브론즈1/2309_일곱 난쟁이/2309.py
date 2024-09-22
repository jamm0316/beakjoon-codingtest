import sys

def find_origin_dwarfs(dwarfs_height):
    all_dwarfs_height = 100
    number_of_dwarfs = len(dwarfs_height)
    total_sum = sum(dwarfs_height)
    for i in range(number_of_dwarfs):
        for j in range(i + 1, number_of_dwarfs):
            if  total_sum - dwarfs_height[i] - dwarfs_height[j] == all_dwarfs_height:
                origin_dwarfs = [dwarfs_height[x] for x in range(number_of_dwarfs) if x != i and x != j]
                return sorted(origin_dwarfs)

#입력값 파싱
input_data = sys.stdin.read()
dwarfs_height = [line for line in map(int, input_data.splitlines())]
origin_dwarf = find_origin_dwarfs(dwarfs_height)
for dwarf in origin_dwarf:
    print(dwarf)
