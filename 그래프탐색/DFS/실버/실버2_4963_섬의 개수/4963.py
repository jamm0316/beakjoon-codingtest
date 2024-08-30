import sys
sys.setrecursionlimit(10000)

class IslandCounter:
    # 방향 벡터: 상하좌우, 대각선 포함
    DX = [-1, 1, 0, 0, -1, -1, 1, 1]
    DY = [0, 0, -1, 1, -1, 1, -1, 1]

    def __init__(self):
        self.width = 0
        self.height = 0
        self.graph = []

    def parse_data(self, data):
        lines = data.splitlines()
        i = 0
        cases = []

        while i < len(lines):
            w, h = map(int, lines[i].split())
            if w == 0 and h == 0:
                break
            i += 1
            graph = []
            for _ in range(h):
                graph.append(list(map(int, lines[i].split())))
                i += 1
            cases.append((w, h, graph))
        return cases

    def dfs(self, x, y):
        graph = self.graph  # self.graph를 로컬 변수에 할당
        graph[y][x] = 0
        
        for i in range(8):
            nx = x + self.DX[i]
            ny = y + self.DY[i]
            
            if 0 <= nx < self.width and 0 <= ny < self.height and graph[ny][nx] == 1:
                self.dfs(nx, ny)
    
    def count_islands(self):
        graph = self.graph  # self.graph를 로컬 변수에 할당
        count = 0
        for y in range(self.height):
            for x in range(self.width):
                if graph[y][x] == 1:  # 땅을 발견하면
                    self.dfs(x, y)
                    count += 1  # 섬의 개수 증가
        print(count)

    def process(self, data):
        cases = self.parse_data(data)
        results = []
        for width, height, graph in cases:
            self.width = width
            self.height = height
            self.graph = graph
            self.count_islands()

def main():
    data = sys.stdin.read()
    island_counter = IslandCounter()
    results = island_counter.process(data)

if __name__ == "__main__":
    main()
