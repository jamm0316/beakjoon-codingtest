page link : [https://www.acmicpc.net/problem/2583](https://www.acmicpc.net/problem/2583)

---

# 💡 풀이전략

- 구하고자 하는 값
    - 영역의 갯수와, 너비의 오름차순 출력

---

- 절차
    1. for - 모눈 종이에 직사각형 그리기
        1. 0으 초기화 된 grid에 1로 채우기
    2. 전체를 순회하며 0을 만나면 bfs 실행
        1. areas = []
        2. bfs를 순회하며 계산된 area 갯수를 areas에 append
    3. len(areas)과 ‘\n’.join(map(str, areas))) 반환.

## 🎨 사용된 알고리즘

> [!tip]
> BFS(Breadth-First Search): 너비 우선 탐색

---

# code

## Python

```python
import sys
from collections import deque

def main():
    input_data = sys.stdin.read()
    area_finder = AreaFinder(input_data)
    area_finder.print_all()

class AreaFinder:
    DIRECTION = [(0, -1), (0, 1), (1, 0), (-1, 0)]
    
    def __init__(self, input_data):
        self.parse_data(input_data)

    def parse_data(self, input_data):
        lines = input_data.splitlines()
        self.N, self.M, K = map(int, lines[0].split())
        self.rectangles = [tuple(map(int, line.split())) for line in lines[1:]]
        self.grid = [[0] * self.M for _ in range(self.N)]
        self.visited = [[False] * self.M for _ in range(self.N)]  # 방문 여부를 위한 배열 추가

    def fill_rectangles(self):
        # 직사각형을 그리드에 채우기 (1로 표시)
        for x1, y1, x2, y2 in self.rectangles:
            for y in range(y1, y2):
                for x in range(x1, x2):
                    self.grid[y][x] = 1
    
    def count_all_areas(self):
        self.fill_rectangles()  # 직사각형 내부를 그리드에 표시
        areas = []
        for y in range(self.N):
            for x in range(self.M):
                if self.grid[y][x] == 0 and not self.visited[y][x]:  # 아직 방문하지 않았고 빈 공간일 때
                    areas.append(self.count_area(y, x))  # 빈 공간의 넓이 계산
        return areas

    def count_area(self, y, x):
        queue = deque([(x, y)])
        self.visited[y][x] = True  # 방문 처리
        area_size = 1

        while queue:
            x, y = queue.popleft()

            for dx, dy in AreaFinder.DIRECTION:
                nx, ny = x + dx, y + dy
                if 0 <= nx < self.M and 0 <= ny < self.N and self.grid[ny][nx] == 0 and not self.visited[ny][nx]:
                    self.visited[ny][nx] = True  # 방문 처리
                    area_size += 1
                    queue.append((nx, ny))
        return area_size

    def print_all(self):
        areas = self.count_all_areas()
        print(len(areas))  # 총 영역 개수 출력
        print(' '.join(map(str, sorted(areas))))  # 각 영역의 넓이를 오름차순으로 출력

if __name__ == '__main__':
    main()
```
