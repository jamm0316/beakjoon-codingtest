page link : [https://www.acmicpc.net/problem/1012](https://www.acmicpc.net/problem/1012)

---

# 💡 풀이전략

풀이전략

조건 1. 하나의 지렁이는 인접한 모든 배추를 커버할 수 있다.

---

절차 1. 모든 필드 탐색
모든 필드에서 배추가 심어져있고, 방문하지 않았던 곳 탐색

절차 2. 절차 1에서 탐색한 필드에서 bfs 실행

절차 3. bfs로 4방향에 배추가 존재하는 인접 visited의 좌표 true로 전환

절차 4. 위 bfs로 인해 절차 1에서 배추가 심어져있지만 인접 배추여서 절차 3에서 이미 visited된 곳은 false처리되어 지렁이 갯수를 세지 않음(즉, 인접 배추는 1번만 지렁이 개수를 셈)


## 🎨 사용된 알고리즘

> [!tip]
> BFS(Breadth-Frist Search) : 너비 우선 탐색

---

# code

## Python

```python
import sys
from collections import deque

def main():
    input_data = sys.stdin.read()
    cabbage = OrganicCabbage(input_data)
    cabbage.process_all_case()

class OrganicCabbage:
    def __init__(self, input_data):
        self.parse_data(input_data)
    
    def parse_data(self, input_data):
        lines = input_data.splitlines()
        index = 0
        self. test_case_count = int(lines[index])
        index += 1  #1
        
        self.test_case = []

        for _ in range(self.test_case_count):
            column, row, cabbage_count = map(int, lines[index].split())
            index += 1  #2
            field = [[0] * column for _ in range(row)]
            for _ in range(cabbage_count):
                x, y = map(int, lines[index].split())
                field[y][x] = 1
                index += 1
            self.test_case.append((column, row, cabbage_count, field))

    def process_all_case(self):
        for column, row, cabbage_count, field in self.test_case:
            visited = self.initialized_visited(column, row)
            count = self.count_earthworms(field, visited, column, row)
            print(count)

    def initialized_visited(self, column, row):
        return [[False] * column for _ in range(row)]
    
    def count_earthworms(self, field, visited, column, row):
        def bfs(start_x, start_y):
            queue = deque([(start_x, start_y)])
            visited[start_y][start_x] = True
            direction = [(-1, 0), (1, 0), (0, -1), (0, 1)]

            while queue:
                current_x, current_y = queue.popleft()

                for dx, dy in direction:
                    nx, ny = current_x + dx, current_y + dy
                    if 0 <= nx < column and 0 <= ny < row and field[ny][nx] == 1 and not visited[ny][nx]:
                        visited[ny][nx] = True
                        queue.append([nx, ny])
        
        worm_count = 0
        for y in range(row):
            for x in range(column):
                if field[y][x] == 1 and not visited[y][x]:
                    bfs(x, y)
                    worm_count += 1
        return worm_count

if __name__ == "__main__":
    main()
```
