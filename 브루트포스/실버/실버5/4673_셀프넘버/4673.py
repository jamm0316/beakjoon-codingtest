def d(n):
    result = n
    while n > 0:
        result += n % 10  # 마지막 자리수 더하기
        n //= 10 # 한자리수 씩 왼쪽으로 옮기기
    return result

# 전체 숫자초기화와 generated_number 정의
self_number = set(range(1, 10001))
generated_number = set()
for i in range(1, 10001):
    generated_number.add(d(i))    

# self_number 정의 = 전체숫자 - generated_number
self_number -= generated_number

# 출력
for number in sorted(self_number):
    print(number)
