def count_set_bits(x):
    count = 0
    while x > 0:
        x = x & (x - 1)
        count += 1
    return count

x = int(input())
print(count_set_bits(x))
