import sys

# 입력 속도를 위해 sys.stdin 사용
input = sys.stdin.read
data = input().splitlines()

# 비어 있는 집합을 나타낼 비트마스크
S = 0
all_set = (1 << 21) - 1  # 1부터 20까지를 모두 포함하는 비트마스크 (21번째 비트까지 모두 1로)

# 결과를 저장할 리스트
output = []

# 연산의 수
M = int(data[0])

for i in range(1, M + 1):
    command = data[i].split()
    
    if command[0] == "add":
        x = int(command[1])
        S |= (1 << x)
    
    elif command[0] == "remove":
        x = int(command[1])
        S &= ~(1 << x)
    
    elif command[0] == "check":
        x = int(command[1])
        output.append("1" if S & (1 << x) else "0")
    
    elif command[0] == "toggle":
        x = int(command[1])
        S ^= (1 << x)
    
    elif command[0] == "all":
        S = all_set
    
    elif command[0] == "empty":
        S = 0

# 결과 출력
sys.stdout.write("\n".join(output) + "\n")
