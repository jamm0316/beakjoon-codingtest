page link : [https://www.acmicpc.net/problem/1926](https://www.acmicpc.net/problem/1926)

---

# 💡 풀이전략

- 구하고자 하는 값
    - 전체 그림 수, 최대 그림 수

---

- 절차1. 전체 순환
- 절차2. 1을 만나면 BFS 이용
- 절차3. BFS로 인접 1의 갯수 세기(최대 그림 수 비교)
- 절차4. 1을 만날 때마다 전체 그림 수 세기

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
    converter = DrawingCounter(input_data)
    converter.print_count()

class DrawingCounter:
    DIRECTION = [(1, 0), (-1, 0), (0, 1), (0, -1)]
    def __init__(self, input_data):
        lines = input_data.splitlines()
        self.row, self.column = map(int, lines[0].split())
        self.drawing_paper = [list(map(int, line.split())) for line in lines[1:]]
        self.visited = [[False] * self.column for _ in range(self.row)]

    def print_count(self):
        count_all, count_max = self.count_all_cases()
        print(count_all)
        print(count_max)

    def count_all_cases(self):
        count_all = 0
        count_max = 0
        for x in range(self.row):
            for y in range(self.column):
                if self.drawing_paper[x][y] == 1 and not self.visited[x][y]:
                    count = self.bfs(x, y)
                    count_all += 1
                    count_max = max(count_max, count)
        return count_all, count_max

    def bfs(self, start_x, start_y):
        queue = deque([(start_x, start_y)])
        self.visited[start_x][start_y] = True
        count = 1
        
        while queue:
            x, y = queue.popleft()
            for dx, dy in DrawingCounter.DIRECTION:
                nx, ny = x + dx, y + dy
                if 0 <= nx < self.row and 0 <= ny < self.column and self.drawing_paper[nx][ny] == 1 and not self.visited[nx][ny]:
                    queue.append((nx, ny))
                    self.visited[nx][ny] = True
                    count += 1
        return count

if __name__ == '__main__':
    main()
```

## 해결한 오류

### 1. 최대값 구하기(if → max())

- **이점: 가독성과 간결성을 높힘**
- 기존코드
    
    ```python
    if count > count_max:
        count_max = count
    ```
    

- 수정된 코드
    
    ```python
    count_max = max(count_max, count)
    ```
    

### 2. solution

---

- 기존코드
    
    ```python
    import sys
    from collections import deque
    
    def main():
        input_data = sys.stdin.read()
        converter = DrawingCounter(input_data)
        converter.print_count()
    
    class DrawingCounter:
        DIRECTION = [(1, 0), (-1, 0), (0, 1), (0, -1)]
        def __init__(self, input_data):
            lines = input_data.splitlines()
            self.row, self.column = map(int, lines[0].split())
            self.drawing_paper = [list(map(int, line.split())) for line in lines[1:]]
            self.visited = [[False] * self.column for _ in range(self.row)]
    
        def print_count(self):
            count_all, count_max = self.count_all_cases()
            print(count_all)
            print(count_max)
    
        def count_all_cases(self):
            count_all = 0
            count_max = 0
            for x in range(self.row):
                for y in range(self.column):
                    if self.drawing_paper[x][y] == 1 and not self.visited[x][y]:
                        count = self.bfs(x, y)
                        count_all += 1
                        if count > count_max:
                            count_max = count
            return count_all, count_max
    
        def bfs(self, start_x, start_y):
            queue = deque([(start_x, start_y)])
            self.visited[start_x][start_y] = True
            count = 1
            
            while queue:
                x, y = queue.popleft()
                for dx, dy in DrawingCounter.DIRECTION:
                    nx, ny = x + dx, y + dy
                    if 0 <= nx < self.row and 0 <= ny < self.column and self.drawing_paper[nx][ny] == 1 and not self.visited[nx][ny]:
                        queue.append((nx, ny))
                        self.visited[nx][ny] = True
                        count += 1
            return count
    
    if __name__ == '__main__':
        main()
    ```
