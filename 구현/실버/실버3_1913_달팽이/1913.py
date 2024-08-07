import sys

input = sys.stdin.read()
data = list(map(int, input.split()))

#입력값 받기
n = int(data[0])
number = int(data[1])


def main():
    # snail_matrix 구현
    snail_matrix = create_snail_matrix(n)
    for row in snail_matrix:
        print(" ".join(map(str, row)))

    # number 좌표값 찾기
    x, y = find_number_coordinates(snail_matrix, number)
    print(x, y)

# matrix 구축 로직
def create_snail_matrix(n):
    matrix = [[0] * n for _ in range(n)]  # n*n 배열 초기화
    direction = [(1, 0), (0, 1), (-1, 0), (0, -1)]  # 우, 하, 좌, 상
    current_direction = 0  # 하방향으로 가는 것이 default
    current_num = n * n  # 배열에 들어갈 숫자 초기화(마지막 부터 1까지 채워가는 전략)
    x, y = 0, 0

    for i in range(n * n):
        matrix[x][y] = current_num  # 시작 좌표에 숫자 넣기
        current_num -= 1

        next_x = x + direction[current_direction][0]  # default 방향
        next_y = y + direction[current_direction][1]  # default 방향
        
        # 만약 next_x, next_y가 0~n 범위안에 들어오지 않고, 배열의 다음 수가 0이 아닐 때, 즉 막다른 골목일 때
        if not (0 <= next_x < n and 0 <= next_y < n and matrix[next_x][next_y] == 0):  
            current_direction = (current_direction + 1) % 4 # 방향키 조정(여기서, %4는 current_direction이 4안에서 벗어나지 못하게 제약)
            next_x = x + direction[current_direction][0]  # 수정된 방향
            next_y = y + direction[current_direction][1]  # 수정된 방향
        
        x, y = next_x, next_y  # x, y를 next_x, next_y로 변경한 후 for문 재 순환

    return matrix

# number의 좌표 찾는 로직
def find_number_coordinates(matrix, number):
    for i in range(len(matrix)):
        for j in range(len(matrix[i])):  #완전탐색(탐욕법)
            if matrix[i][j] == number:
                return i + 1, j + 1

if __name__ == "__main__":
    main()
