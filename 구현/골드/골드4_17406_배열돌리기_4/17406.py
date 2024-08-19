import sys

def main():
    input_data = sys.stdin.read()
    solve = ArrayRotate()
    solve.parse_data(input_data)
    result = solve.return_min_value()
    print(result)

class ArrayRotate:
    def __init__(self):
        self.N = 0
        self.M = 0
        self.K = 0
        self.A = []
        self.queries = []

    def parse_data(self, input):
        lines = input.split('\n')
        self.N, self.M, self.K = list(map(int, lines[0].split()))
        self.A = [list(map(int, lines[i].split())) for i in range(1, self.N + 1)]
        self.queries = [list(map(int, lines[i].split())) for i in range(self.N + 1, self.N + self.K + 1)]

    def rotate_array(self, arr, r, c, s):
        r -= 1  # 인덱스 보정
        c -= 1  # 인덱스 보정

        for layer in range(1, s + 1):  
            temp = arr[r - layer][c - layer]  # 왼쪽 상단 값을 임시 저장

            # 왼쪽 열을 위로 이동
            for i in range(r - layer, r + layer):
                arr[i][c - layer] = arr[i + 1][c - layer]
            
            # 아래쪽 행을 왼쪽으로 이동
            for i in range(c - layer, c + layer):
                arr[r + layer][i] = arr[r + layer][i + 1]
            
            # 오른쪽 열을 아래로 이동
            for i in range(r + layer, r - layer, -1):
                arr[i][c + layer] = arr[i - 1][c + layer]
            
            # 위쪽 행을 오른쪽으로 이동
            for i in range(c + layer, c - layer + 1, -1):
                arr[r - layer][i] = arr[r - layer][i - 1]

            arr[r - layer][c - layer + 1] = temp  # 임시 저장된 값 복구
    
    def process_queries(self, arr, order):
        for i in order:
            r, c, s = self.queries[i]
            self.rotate_array(arr, r, c, s)

    def return_min_value(self):
        from itertools import permutations

        min_value = float('inf')
        for order in permutations(range(self.K)):
            arr_copy = [row[:] for row in self.A]  # 원본 배열을 복사
            self.process_queries(arr_copy, order)  # 순서에 따라 배열을 회전
            min_value = min(min_value, min(sum(row) for row in arr_copy))
            
        return min_value  # 최종 최소값 반환


if __name__ == '__main__':
    main()
