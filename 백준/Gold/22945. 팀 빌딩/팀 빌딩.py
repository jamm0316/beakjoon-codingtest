n = int(input())
arr = list(map(int, input().split()))

left = 0
right = n - 1
max_power = 0

while left < right:
    gap = right - left - 1  # 사이의 개발자 수
    min_ability = min(arr[left], arr[right])
    power = gap * min_ability
    
    max_power = max(max_power, power)
    
    if arr[left] < arr[right]:
        left += 1
    else:
        right -= 1

print(max_power)