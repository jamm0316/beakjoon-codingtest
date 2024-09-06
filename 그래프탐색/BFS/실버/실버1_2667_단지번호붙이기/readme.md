page link : [https://www.acmicpc.net/problem/2667](https://www.acmicpc.net/problem/2667)


# 💡 풀이전략

- 조건1. 인접한 부분은 같은 단지로 묶으며, 대각선은 인접한 부분이 아님.

---

절차 1. 모든 곳을 탐색하며 1을 만났을 때, BFS 호출.

절차 2. BFS 호출 조건으로 해당 숫자가 1이고, vistied가 False여야함.

절차 2-1. BFS에 인접 부분의 visited를 모두 true로 반환.

절차 2-2. 이 때, visited를 바꾼 갯수 세기(단지별 집 수)

절차 2-3. BFS에서 집수를 return

절차 3. 해당 절차가 끝날 때마다 count 세기 → 단지 수

절차 4. 단지수, 집 순으로 출력


## 🎨 사용된 알고리즘

> [!tip]
> BFS(Breathd-First Search): 너비 우선 탐색

---

# code

## Python

```python
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
```

## 해결한 오류

### 1. 리펙토링

- **방향의 상수화**
    - `def bfs`에서 방향을 초기화 해주었지만 이는 재사용 될 수 있기 때문에 상수화 해주는 것이 좋다.
        
        **기존코드**
        
        ```java
        def bfs(self, x, y, visited, field):
            direction = [(0, 1), (0, -1), (1, 0), (-1, 0)]  #방향 초기화
            visited[x][y] = True  #visited 초기화
            queue = deque([(x, y)])  #deque 초기화
            count_houses = 1
        ```
        
        **수정된 코드**
        
        ```java
        class HousingComplex:
            # 방향을 상수로 저장
            DIRECTIONS = [(0, 1), (0, -1), (1, 0), (-1, 0)]
        ```
        
- **매개변수 전달 간소화**
    - `bfs`에서 `visited`와 `field`는 이미 클래스 속성으로 존재하므로, 직접 전달하는 것이 더 깔끔하다.
        
        **기존코드**
        
        ```java
        def bfs(self, x, y, visited, field):
        		...
            visited[x][y] = True  #visited 초기화
            ...
        ```
        
        **수정된 코드**
        
        ```java
        def bfs(self, x, y):
            self.visited[x][y] = True
        ```
        
- **불필요한 부가 연산 제거**
    - `self.visitied[nx][ny] == False` 
    → `not self.visitied[nx][ny]` (파이썬 권장 방식)

---

- 기존코드
    
    ```python
    import sys
    from collections import deque
    
    def main():
        input_data = sys.stdin.read()
        housing_complex = HousingComplex(input_data)
        housing_complex.process_all_cases()
    
    class HousingComplex:
        def __init__(self, input_data):
            self.parse_data(input_data)
        
        def parse_data(self, input_data):
            lines = input_data.splitlines()
            self.N = int(lines[0])
            self.field = [list(map(int, line)) for line in lines[1:]]
            self.visited = [[False] * self.N for _ in range(self.N)]
    
        def process_all_cases(self):
            print(self.count_complex())
            for count in sorted(self.count_housing):
                print(count)
        
        def count_complex(self):
            self.count_housing = []
            count_complex = 0
            for x in range(self.N):
                for y in range(self.N):
                    if self.field[x][y] == 1 and self.visited[x][y] == False:
                        result = self.bfs(x, y, self.visited, self.field)
                        self.count_housing.append(result)
                        count_complex += 1
            
            return count_complex
        
        def bfs(self, x, y, visited, field):
            direction = [(0, 1), (0, -1), (1, 0), (-1, 0)]  #방향 초기화
            visited[x][y] = True  #visited 초기화
            queue = deque([(x, y)])  #deque 초기화
            count_houses = 1
    
            while queue:
                x, y = queue.popleft()
                
                for dx, dy in direction:
                    nx, ny = x + dx, y + dy
                    if 0 <= nx < self.N and 0 <= ny < self.N and field[nx][ny] == 1 and not visited[nx][ny]:
                        visited[nx][ny] = True
                        queue.append((nx, ny))
                        count_houses += 1
            return count_houses
    
    if __name__ == '__main__':
        main()
    ```
