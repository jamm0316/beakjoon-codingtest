def count_subarrays_with_sum_k(N, K, A):
    prefix_sum_count = {0: 1}  # Initialize hashmap with 0 sum occurring once
    current_sum = 0
    count_of_subarrays = 0

    for num in A:
        current_sum += num
        
        # Check if (current_sum - K) is present in the hashmap
        if (current_sum - K) in prefix_sum_count:
            count_of_subarrays += prefix_sum_count[current_sum - K]
        
        # Update the count of the current prefix sum in the hashmap
        if current_sum in prefix_sum_count:
            prefix_sum_count[current_sum] += 1
        else:
            prefix_sum_count[current_sum] = 1

    return count_of_subarrays

# Read input
import sys
input = sys.stdin.read
data = input().split()

N = int(data[0])
K = int(data[1])
A = list(map(int, data[2:]))

# Get the result and print it
result = count_subarrays_with_sum_k(N, K, A)
print(result)
