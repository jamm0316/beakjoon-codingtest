page link : https://www.acmicpc.net/problem/1913

# 💡 풀이전략
1. 실행 함수 정의(달팽이 배열 구현, 숫자 좌표 찾기)
2. 달팽이 배열 구축 함수 정의
3. 특정 숫자 좌표 찾는 함수 정의
</aside>

## 🎨 사용된 알고리즘
1. 달팽이 배열 채우기
    1. 시뮬레이션: 각 칸을 방문하여 숫자를 채워나가는 시뮬레이션 방식
    2. 방향 전환(방향 벡터): 방향 배열을 이용하여 현재 방향을 기준으로 다음칸으로 이동. 이동불가의 경우 방향 전환
2. 좌표 찾기
    1. 완전 탐색(Brute Force): 배열을 순회하며 숫자를 찾는다.
---

# code

## Python

```python
input = '7\n35'
data = list(map(int, input.split()))

#입력값 받기
n = data[0]
number = data[1]

# main함수 정의
def main():
    # snail_matrix 구현
    snail_matrix = create_snail_matrix(n)
    for row in snail_matrix:
        print(' '.join(map(str, row)))
    
    # number의 좌표값 찾기
    x, y = find_number_coordinator(snail_matrix, number)
    print(y, x)

# create_snail_matrix 함수 정의
def create_snail_matrix(n):
    matrix = [[0] * n for _ in range(n)]  # n*n의 2차원 배열 초기화
    direction = [(1, 0), (0, 1), (-1, 0), (0, -1)]
    current_direction = 0
    current_num = n * n
    x, y = 0, 0  #좌표 초기화

    for i in range(n * n):
        matrix[x][y] = current_num
        current_num -= 1

        next_x = x + direction[current_direction][0]
        next_y = y + direction[current_direction][1]

        if not (0 <= next_x < n and 0 <= next_y < n and matrix[next_x][next_y] == 0):
            current_direction = (current_direction + 1) % 4
            next_x = x + direction[current_direction][0]
            next_y = y + direction[current_direction][1]

        x = next_x
        y = next_y

    return matrix

# find_number_coordinator 로직 구축
def find_number_coordinator(matrix, number):
    for i in range(len(matrix)):
        for j in range(len(matrix[i])):
            if matrix[i][j] == number:
                return i + 1, j + 1

if __name__ == "__main__":
    main()
```
