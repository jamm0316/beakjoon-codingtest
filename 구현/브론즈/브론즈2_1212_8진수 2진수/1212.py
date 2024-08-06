# 입력값 받기
octal_number = input().strip()

# 8진수를 10진수로 변환
decimal_number = int(octal_number, 8)

# 10진수를 2진수로 변환
binary_number = bin(decimal_number)[2:]

# 변환된 2진수를 출력
print(binary_number)
