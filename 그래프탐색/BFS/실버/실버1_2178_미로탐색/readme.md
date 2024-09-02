page link : [https://www.acmicpc.net/problem/2178](https://www.acmicpc.net/problem/2178)

---

# 💡 풀이전략
- 좌표가 N,M에 도달하면 종료
- for 4방향 방향키 사용 반복
    - 1이 있는 좌표값을 덱에 넣기
    - 해당 좌표값으로 이동할 때 count 함께 세기(튜플이용)
    - 왔던곳은 되돌아가지 않게 true표시해두기
        → 왔던 곳을 되돌아가지 않는다면 최단 경로로 갈 수 있음.
        
## 사용된 알고리즘


> [!tip]
> [BFS(Breadth-First Search)](https://www.notion.so/DFS-BFS-530e0c8b66da4f0090555edcb738061f?pvs=21) 너비 우선 탐색 : 최단 경로 찾기

---

# code

## Python

```python
import sys
from collections import deque

def main():
    input_data = sys.stdin.read()
    maze = Maze(input_data)
    maze.find_exit()

class Maze:
    def __init__(self, input_data):
        self.parse_data(input_data)
    
    def parse_data(self, input_data):
        lines = input_data.splitlines()
        self.N, self.M = map(int, lines[0].split())
        self.maze = [list(map(int, line)) for line in lines[1:]]
        self.visited = [[False] * self.M for _ in range(self.N)]

    def find_exit(self):
        directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
        queue = deque([((0, 0), 1)])
        self.visited[0][0] = True

        while queue:
            (x, y), count = queue.popleft()

            if (x, y) == (self.N - 1, self.M - 1):
                print(count)
                return

            for dx, dy in directions:
                nx, ny = x + dx, y + dy

                if 0 <= nx < self.N and 0 <= ny < self.M and self.maze[nx][ny] == 1 and not self.visited[nx][ny]:
                    self.visited[nx][ny] = True
                    queue.append(((nx, ny), count + 1))

if __name__ == '__main__':
    main()
```

## 해결한 오류

### 1. 코드 중복 제거 - `def __init__(self)` 시 parsing 메서드 호출로

- `parse_data`와 `__init__` 함수를 나누어 사용
    
    **기존코드**
    
    ```python
    def main():
        input_data = sys.stdin.read()
        **maze = Maze(input_data)**
        maze.find_exit()
    
    def __init__(self):
        self.N = 0  #종료지점 좌표(x)
        self.M = 0  #종료지점 좌표(y)
        self.maze = []
    
    def parse_data(self, input):
        lines = input.splitlines()
        self.N, self.M = map(int,lines[0].split())
        self.maze = [list(map(int, " ".join(lines[i]).split())) for i in range(1, len(lines))]
    ```
    
- `parse_data`에서 데이터 값을 정의하고 `__init__` 함수에서 `self.parse_data`를 호출하여, 클래스가 `main`메서드에서 호출될 때, 파싱이 바로 되도록 설정하여 코드의 중복 줄이기.
    
    **수정된 코드**
    
    ```python
    def main():
        input = sys.stdin.read()
        maze = Maze()
        maze.find_exit(input)
    
    def __init__(self, input_data):
        self.parse_data(input_data)
    
    def parse_data(self, input_data):
        lines = input_data.splitlines()
        self.N, self.M = map(int, lines[0].split())
        self.maze = [list(map(int, line)) for line in lines[1:]]
        self.visited = [[False] * self.M for _ in range(self.N)]
    ```
    

### 2. 불필요한 변수 제거

- x, y, count를 각각 변수 선언해준 후 queue를 초기화 함.
    
    **기존코드**
    
    ```python
    x = 0
    y = 0
    count = 1
    queue = deque([((x, y), count)])  #좌표(x, y), 이동횟수
    ```
    
- queue를 먼저 초기화 한 후, while 구문에서 leftpop()으로 받을 queue리스트에 대해 x, y, count를 설정하도록 함.
    
    **수정된 코드**
    
    ```python
    queue = deque([((0, 0), 1)])
    
    while queue:
        (x, y), count = queue.popleft()
        ...
    ```
    

### 3. 구문 간략화

1. **list comprehension 구문 간략화**
    
    **기존코드**
    
    ```python
    self.maze = [list(map(int, " ".join(lines[i]).split())) for i in range(1, len(lines))]
    ```
    
    **수정된 코드**
    
    ```python
    self.maze = [list(map(int, line)) for line in lines[1:]]
    ```
    
2. **next_x, y 구문 간략화**
    - `,` 를 이용한 간략화
        
        **기존코드**
        
        ```python
        for add_x, add_y in direction:
            next_x = current_coordinate[0] + add_x
            next_y = current_coordinate[1] + add_y
        ```
        
        **수정된 코드**
        
        ```python
        for dx, dy in directions:
            nx, ny = x + dx, y + dy
        ```
        
3. **boolean 구문 간략화**
    - boolean 구문이 if문과 만나면
     `if boolean:`  ⇒  `if boolean == True:` 를 뜻함.
    따라서, `if not boolean:`  ⇒  `if boolaean == False:` 와 같음
        
        **기존코드**
        
        ```python
        if 0 <= next_x < self.N and 0 <= next_y < self.M and self.maze[next_x][next_y] == 1 and **true_map[next_x][next_y] == False:**
        ```
        
        **수정된 코드**
        
        ```python
        if 0 <= nx < self.N and 0 <= ny < self.M and self.maze[nx][ny] == 1 and **not self.visited[nx][ny]:**
        ```
        

---

- 기존코드
    
    ```python
    import sys
    
    def main():
        input = sys.stdin.read()
        maze = Maze()
        # maze.parse_data(input)
        maze.find_exit(input)
    
    class Maze:
        def __init__(self):
            self.N = 0  #종료지점 좌표(x)
            self.M = 0  #종료지점 좌표(y)
            self.maze = []
        
        def parse_data(self, input):
            lines = input.splitlines()
            self.N, self.M = map(int,lines[0].split())
            self.maze = [list(map(int, " ".join(lines[i]).split())) for i in range(1, len(lines))]
        
        def find_exit(self, input):
            from collections import deque
            self.parse_data(input)
    
            #방향키 정의
            direction = [(0, 1), (1, 0), (0, -1), (-1, 0)]  #좌, 하, 우, 상
            
            #deque 정의
            x = 0
            y = 0
            count = 1
            queue = deque([((x, y), count)])  #좌표(x, y), 이동횟수
    
            #중복 제거
            true_map = [[False] * self.M for _ in range(self.N)]
            true_map[0][0] = True
    
            while queue:
                current_coordinate, current_count = queue.popleft()
    
                if current_coordinate == (self.N - 1, self.M - 1):
                    print(current_count)
                    return
    
                for add_x, add_y in direction:
                    next_x = current_coordinate[0] + add_x
                    next_y = current_coordinate[1] + add_y
    
                    if 0 <= next_x < self.N and 0 <= next_y < self.M and self.maze[next_x][next_y] == 1 and true_map[next_x][next_y] == False:
                        true_map[next_x][next_y] = True
                        queue.append(((next_x, next_y), current_count + 1))
                        
    if __name__ == '__main__':
        main()
    ```
