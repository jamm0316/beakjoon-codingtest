import sys
from collections import deque

def main():
    input_data = sys.stdin.read()
    housing_complex = HousingComplex(input_data)
    housing_complex.process_all_cases()

class HousingComplex:
    # 방향을 상수로 저장
    DIRECTIONS = [(0, 1), (0, -1), (1, 0), (-1, 0)]
    
    def __init__(self, input_data):
        self.parse_data(input_data)
    
    def parse_data(self, input_data):
        lines = input_data.splitlines()
        self.N = int(lines[0])
        self.field = [list(map(int, line)) for line in lines[1:]]
        self.visited = [[False] * self.N for _ in range(self.N)]

    def process_all_cases(self):
        complex_count = self.count_complex()
        print(complex_count)
        for count in sorted(self.count_housing):
            print(count)
    
    def count_complex(self):
        self.count_housing = []
        count_complex = 0
        for x in range(self.N):
            for y in range(self.N):
                if self.field[x][y] == 1 and not self.visited[x][y]:
                    result = self.bfs(x, y)
                    self.count_housing.append(result)
                    count_complex += 1
                    
        return count_complex
    
    def bfs(self, x, y):
        self.visited[x][y] = True
        queue = deque([(x, y)])
        count_houses = 1

        while queue:
            x, y = queue.popleft()
            
            for dx, dy in self.DIRECTIONS:
                nx, ny = x + dx, y + dy
                if 0 <= nx < self.N and 0 <= ny < self.N and self.field[nx][ny] == 1 and not self.visited[nx][ny]:
                    self.visited[nx][ny] = True
                    queue.append((nx, ny))
                    count_houses += 1
        
        return count_houses

if __name__ == '__main__':
    main()
