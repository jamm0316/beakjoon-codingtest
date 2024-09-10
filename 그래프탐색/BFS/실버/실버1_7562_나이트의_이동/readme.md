page link : [https://www.acmicpc.net/problem/7562](https://www.acmicpc.net/problem/7562)

---

# 💡 풀이전략

구하려는 값: 현재 위치에서 해당 위치까지 나이트가 이동할 때 움직인 횟수

- 절차 1. BFS 이용.
    - if queue.leftpop() == 해당 위치(x, y):
        return count
- 절차2. count는 4방향이 이동되는 스텝마다 count + 1
    - 따라서 queue()매개변수로 넣어, 각 스텝이 독립적으로 실행됨을 보장.

## 🎨 사용된 알고리즘

> [!tip]
> BFS(Breathd-First Search): 너비 우선 탐색<br>
> → 최소값을 찾기 위해

---

# code

## Python

```python
import sys
from collections import deque

def main():
    input_data =  sys.stdin.read()
    knight = ChessKnight(input_data)
    knight.process_all_cases()

class ChessKnight:
    # 나이트 이동 가능한 방향을 상수로 지정
    DIRECTIONS = [(-1, 2), (-2, 1), (-2, -1), (-1, -2), 
                  (1, -2), (1, 2), (2, 1), (2, -1)]

    def __init__(self, input_data):
        self.parse_data(input_data)

    def parse_data(self, input_data):
        lines = input_data.splitlines()
        self.test_cases = int(lines[0])
        self.queries = []
        for i in range(1, len(lines[1:]), 3):
            size = int(lines[i])
            start = tuple(map(int, lines[i + 1].split()))
            goal = tuple(map(int, lines[i + 2].split()))
            self.queries.append((size, start, goal))

    def process_all_cases(self):
        for field_size, (start_x, start_y), (goal_x, goal_y) in self.queries:
            result = self.find_minimum_moves(start_x, start_y, goal_x, goal_y, field_size)
            print(result)

    def find_minimum_moves(self, start_x, start_y, goal_x, goal_y, field_size):
        visited = [[False] * field_size for _ in range(field_size)]
        visited[start_x][start_y] = True
        queue = deque([[(start_x, start_y), 0]])

        while queue:
            (x, y), move_count = queue.popleft()
            if (x, y) == (goal_x, goal_y):
                return move_count
            for dx, dy in ChessKnight.DIRECTIONS:
                nx, ny = x + dx, y + dy
                if 0 <= nx < field_size and 0 <= ny < field_size and not visited[nx][ny]:
                    visited[nx][ny] = True
                    queue.append([(nx, ny), move_count + 1])
        # 만약 도달할 수 없으면, 기본적으로 이 문제가 발생하지 않지만 안전을 위해 -1 반환
        return -1

if __name__ == '__main__':
    main()
```

## 해결한 오류

### 1. 큐의 구성 변경`[[(x, y), move_count]]`

bfs() 실행 시, 큐에 나이트 위치와 해당 위치까지 도달하는 데 걸린 이동 횟수(move_count)를 함께 넣는다.
이후 popleft()로 큐에서 값을 꺼낼 때, 이 이동 횟수 정보를 이용하여 각 탐색 단계(회차)별로 정확한 이동 횟수를 추적할 수 있다.
먼저 도달한 경로가 최단 경로이므로, 각 회차는 독립적으로 실행되며 최소 이동 횟수를 보장한다.

```python
while queue:
    **(x, y), move_count = queue.popleft()**
    for dx, dy in ChessKnight.DIRECTIONS:
    nx, ny = x + dx, y + dy
    if 0 <= nx < field_size and 0 <= ny < field_size and not visited[nx][ny]:
        if (nx, ny) == (goal_x, goal_y):
            return move_count + 1
        visited[nx][ny] = True
        **queue.append(((nx, ny), move_count + 1))**
```

### 2. 리펙토링

1. **데이터 파싱 방식 수정**
    - `self.queries` 데이터를 파싱할 때, 리스트컴프리핸션 대신 각 데이터들의 이름을 명확히 기재하여 가독성 높힘
    
    **기존 코드**
    
    ```python
    def parse_data(self, input_data):
    ...
        self.queries = [[int(lines[i]), tuple(map(int, lines[i + 1].split())), tuple(map(int, lines[i + 2].split()))] for i in range(1, len(lines[1:]), 3)]
    ```
    
    **수정된 코드**
    
    ```python
    def parse_data(self, input_data):
    ...
        self.queries = []
        for i in range(1, len(lines[1:]), 3):
            size = int(lines[i])
            start = tuple(map(int, lines[i + 1].split()))
            goal = tuple(map(int, lines[i + 2].split()))
            self.queries.append((l, start, goal))
    ```
    
2. **매직 넘버 제거**
    - self.direction에서 나이트의 이동 방향을 코드 내에서 하드코딩하고 있는데, 이 부분을 클래스 상수로 관리하는 것이 가독성에 좋음.
    
    **기존코드**
    
    ```python
    def bfs(self, x, y, gx, gy, field_size, visited):
        self.direction = [(-1, 2), (-2, 1), (-2, -1), (-1, -2), (1, -2), (1, 2), (2, 1), (2, -1)]
    ```
    
    **수정된 코드**
    
    ```python
    class ChessKnight:
        # 나이트 이동 가능한 방향을 상수로 지정
        DIRECTIONS = [(-1, 2), (-2, 1), (-2, -1), (-1, -2), 
                      (1, -2), (1, 2), (2, 1), (2, -1)]
    ```
    
3. **메서드 명확화**
    - bfs라는 이름은 알고리즘 유형을 잘 나타내지만, 메서드 이름이 명확히 역할을 나타내도록 변경하여 가독성을 높힘.
        
        `bfs` → `find_minimum_moves` 
        
    
    **기존 코드**
    
    ```python
    def bfs(self, x, y, gx, gy, field_size, visited):
    ```
    
    **수정된 코드**
    
    ```python
    def find_minimum_moves(self, start_x, start_y, goal_x, goal_y, field_size):
    ```
    

---

- 기존코드
    
    ```python
    import sys
    from collections import deque
    
    def main():
        input_data = sys.stdin.read()
        knight = Chess_Knight(input_data)
        knight.process_all_case()
    
    class Chess_Knight:
        def __init__(self, input_data):
            self.parse_data(input_data)
    
        def parse_data(self, input_data):
            lines = input_data.splitlines()
            self.K = lines[0].split()
            self.queries = [[int(lines[i]), tuple(map(int, lines[i + 1].split())), tuple(map(int, lines[i + 2].split()))] for i in range(1, len(lines[1:]), 3)]
    
        def process_all_case(self):
            for field_size, (start_x, start_y), (goal_x, goal_y) in self.queries:
                visited = [[False] * field_size for _ in range(field_size)]
                move_count = self.bfs(start_x, start_y, goal_x, goal_y, field_size, visited)
                print(move_count)
    
        def bfs(self, x, y, gx, gy, field_size, visited):
            self.direction = [(-1, 2), (-2, 1), (-2, -1), (-1, -2), (1, -2), (1, 2), (2, 1), (2, -1)]
            visited[x][y] = True
            queue = deque([[(x, y), 0]])
    
            while queue:
                current_data = queue.popleft()
                current_position, current_time = current_data[0], current_data[1]
                x, y = current_position
                if (x, y) == (gx, gy):
                    return current_time
                for dx, dy in self.direction:
                    nx, ny = x + dx, y + dy
                    if 0 <= nx < field_size and 0 <= ny < field_size and not visited[nx][ny]:
                        visited[nx][ny] = True
                        next_time = current_time + 1
                        queue.append([(nx, ny), next_time])
    
    if __name__ == '__main__':
        main()
    ```
