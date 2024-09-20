def find_smallest_generator(target_number):
    for i in range(1, target_number + 1):
        decomposition_sum = i + sum(map(int, str(i)))

        if decomposition_sum == target_number:
            return i
    return 0

target_number = int(input())

print(find_smallest_generator(target_number))
