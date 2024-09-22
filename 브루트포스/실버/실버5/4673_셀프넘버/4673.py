# 전체 수 정의
all_number = set(range(1, 10001))

# 생성자가 있는 수 정의
def d(n):
    sum_number = sum(map(int, str(n)))
    return sum_number + n

constructor_number = set()
for n in range(1, 10001):
    constructor_number.add(d(n))

# 전체 수 - 생성자가 있는 수 = 셀프넘버
self_number = all_number - constructor_number

# 셀프넘버 출력
for number in sorted(self_number):
    print(number)
