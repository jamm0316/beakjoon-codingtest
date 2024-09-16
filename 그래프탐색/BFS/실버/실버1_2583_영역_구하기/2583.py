from collections import deque

class AreaFinder:
    def __init__(self, M, N, rectangles):
        self.M = M
        self.N = N
        self.grid = [[0] * N for _ in range(M)]
        self.rectangles = rectangles
        self.dx = [0, 0, -1, 1]
        self.dy = [-1, 1, 0, 0]
    
    def fill_rectangles(self):
        # 직사각형을 그리드에 표시
        for x1, y1, x2, y2 in self.rectangles:
            for i in range(y1, y2):
                for j in range(x1, x2):
                    self.grid[i][j] = 1  # 직사각형이 차지하는 부분은 1로 채움
    
    def bfs(self, x, y):
        # BFS를 통해 영역의 넓이를 계산
        queue = deque([(x, y)])
        self.grid[x][y] = 1  # 방문 표시
        area = 1
        while queue:
            cx, cy = queue.popleft()
            for i in range(4):
                nx, ny = cx + self.dx[i], cy + self.dy[i]
                if 0 <= nx < self.M and 0 <= ny < self.N and self.grid[nx][ny] == 0:
                    self.grid[nx][ny] = 1  # 방문 표시
                    queue.append((nx, ny))
                    area += 1
        return area
    
    def find_areas(self):
        # 모든 영역을 찾고 넓이를 계산
        areas = []
        for i in range(self.M):
            for j in range(self.N):
                if self.grid[i][j] == 0:  # 아직 방문하지 않은 영역
                    areas.append(self.bfs(i, j))
        return areas
    
    def solve(self):
        # 문제를 해결하는 메인 메서드
        self.fill_rectangles()
        areas = self.find_areas()
        areas.sort()
        return len(areas), areas

# 입력 처리 함수
def read_input():
    M, N, K = map(int, input().split())
    rectangles = [tuple(map(int, input().split())) for _ in range(K)]
    return M, N, rectangles

# 결과 출력 함수
def print_result(area_count, areas):
    print(area_count)
    print(" ".join(map(str, areas)))

if __name__ == "__main__":
    M, N, rectangles = read_input()
    area_finder = AreaFinder(M, N, rectangles)
    area_count, areas = area_finder.solve()
    print_result(area_count, areas)
