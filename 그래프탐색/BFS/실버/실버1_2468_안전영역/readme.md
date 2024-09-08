page link : [https://www.acmicpc.net/problem/2468](https://www.acmicpc.net/problem/2468)

---

# 💡 풀이전략

- 조건 1. 안전영역의 최대 개수

- 모든 높이를 탐색해야함.
    - hightest_feild가 0 ~ N일 때 모두 count값 출력
- BFS를 이용해 visited를 설정.
    - 해당 조건(안전지대)이 만족하는 곳에서 BFS를 실행하고, count += 1
    - def process_all_cases
    - def count_safe_zone
        - def bfs

## 🎨 사용된 알고리즘
> [!tip]
> **BFS(Breathd-Frist Search): 너비 우선 탐색**


# code

## Python

```python
import sys
from collections import deque

def main():
    input_data = sys.stdin.read()
    safe_zone = SafeZone(input_data)
    safe_zone.process_all_case()

class SafeZone:
    def __init__(self, input_data):
        self.parse_data(input_data)
    
    def parse_data(self, input_data):
        lines = input_data.splitlines()
        self.N = int(lines[0])
        self.field = [list(map(int, line.split())) for line in lines[1:]]
        self.highest_field = max(map(max, self.field))
    
    def process_all_case(self):
        max_safe_zone_count = 1
        for water_level in range(1, self.highest_field + 1):
            visited = [[False] * self.N for _ in range(self.N)]
            safe_zone_count = self.count_safe_zone(water_level, visited)
            max_safe_zone_count = max(max_safe_zone_count, safe_zone_count)
        print(max_safe_zone_count)
    
    def count_safe_zone(self, water_level, visited):
        def bfs(x, y):
            direction = [(1, 0), (-1, 0), (0, 1), (0, -1)]
            queue = deque([(x, y)])
            visited[x][y] = True

            while queue:
                x, y = queue.popleft()
                for dx, dy in direction:
                    nx, ny = x + dx, y + dy
                    if 0 <= nx < self.N and 0 <= ny < self.N and self.field[nx][ny] > water_level and not visited[nx][ny]:
                        visited[nx][ny] = True
                        queue.append((nx, ny))
            
        count = 0
        for x in range(self.N):
            for y in range(self.N):
                if self.field[x][y] > water_level and not visited[x][y]:
                    bfs(x, y)
                    count += 1
        return count

if __name__ == '__main__':
    main()
```

## 해결한 오류

### 리펙토링

1. **중복된 로직 간소화**
    
    `self.highest_field` 계산 시, 중첩 반복 문을 `max`, `map`을 통해 간소화
    
    **기존 코드**
    
    ```python
    self.highest_field = 0
    for x in range(self.N):
        for y in range(self.N):
            if self.highest_field < self.field[x][y]:
                self.highest_field = self.field[x][y]
    ```
    
    **수정된 코드**
    
    - `map(max, self.field)`를 통해 `field`의 각 리스트들을 순회하면서 `max`값을 추출하고, 그 `max`으로 이루어진 `list`에서 다시 `max` 값 추출.
    
    ```python
    self.highest_field = max(map(max, self.field))
    ```
    
2. **가독성**
    - `current_height` 를 `water_level`로 변경하여 가독성 향상
    
    **기존코드**
    
    ```python
    for current_height in range(1, self.highest_field + 1):
    ```
    
    **수정된 코드**
    
    ```python
    for water_level in range(1, self.highest_field + 1):
    ```
    
3. **불필요한 인자 전달**
    - `self.field` 와 같은 함수들은 굳이 매개변수로 전달할 필요 없음
    - `bfs` 함수 인자 중 `(currnet_height, field, visited)`는 상위 클래스의 속성으로 사용하면 되므로 `bfs(x, y)`만 매개변수로 받으면 간결해짐.
    
    **기존코드**
    
    ```python
    def process_all_case(self):
          count_list = [1]
          for current_height in range(1, self.highest_field + 1):
              visited = [[False] * self.N for _ in range(self.N)]
              current_count = **self.count_safe_zone(current_height, self.field, visited)**
              count_list.append(current_count)
          print(max(count_list))
      
    **def count_safe_zone(self, current_height, field, visited):
        def bfs(x, y):**
    ```
    
    **수정된코드**
    
    ```python
    def process_all_case(self):
            max_safe_zone_count = 1
            for water_level in range(1, self.highest_field + 1):
                visited = [[False] * self.N for _ in range(self.N)]
                **safe_zone_count = self.count_safe_zones(water_level, visited)**
                max_safe_zone_count = max(max_safe_zone_count, safe_zone_count)
            print(max_safe_zone_count)
        
    **def count_safe_zones(self, water_level, visited):
        def bfs(x, y, current_height, field, visited):**
    ```
    
4. **성능 최적화 → 메모리 사용량 최적화**
    - max_safe_zone_count 변수를 최소 1로 초기화 한 후, 각 물 높이에서 구한 안전 구역의 개수와 비교하여 최대값을 업데이트하는 방식으로 변경.
    - list를 사용할 때 보다 메모리 사용량이 줄어듦.
    
    **기존 코드**
    
    ```python
    def process_all_case(self):
        **count_list = [1]**
        for current_height in range(1, self.highest_field + 1):
            visited = [[False] * self.N for _ in range(self.N)]
            current_count = self.count_safe_zone(current_height, self.field, visited)
            **count_list.append(current_count)
        print(max(count_list))**
    ```
    
    **수정된 코드**
    
    ```python
    def process_all_case(self):
        **max_safe_zone_count = 1**
        for water_level in range(1, self.highest_field + 1):
            visited = [[False] * self.N for _ in range(self.N)]
            safe_zone_count = self.count_safe_zones(water_level, visited)
            **max_safe_zone_count = max(max_safe_zone_count, safe_zone_count)
        print(max_safe_zone_count)**
    ```
    

---

- 기존코드
    
    ```python
    import sys
    from collections import deque
    
    def main():
        input_data = sys.stdin.read()
        safe_zone = SafeZone(input_data)
        safe_zone.process_all_case()
    
    class SafeZone:
        def __init__(self, input_data):
            self.parse_data(input_data)
        
        def parse_data(self, input_data):
            lines = input_data.splitlines()
            self.N = int(lines[0])
            self.field = [list(map(int, line.split())) for line in lines[1:]]
            self.highest_field = 0
            for x in range(self.N):
                for y in range(self.N):
                    if self.highest_field < self.field[x][y]:
                        self.highest_field = self.field[x][y]
        
        def process_all_case(self):
            count_list = [1]
            for current_height in range(1, self.highest_field + 1):
                visited = [[False] * self.N for _ in range(self.N)]
                current_count = self.count_safe_zone(current_height, self.field, visited)
                count_list.append(current_count)
            print(max(count_list))
        
        def count_safe_zone(self, current_height, field, visited):
            def bfs(x, y, current_height, field, visited):
                direction = [(1, 0), (-1, 0), (0, 1), (0, -1)]
                queue = deque([(x, y)])
                visited[x][y] = True
    
                while queue:
                    x, y = queue.popleft()
                    for dx, dy in direction:
                        nx, ny = x + dx, y + dy
                        if 0 <= nx < self.N and 0 <= ny < self.N and field[nx][ny] > current_height and not visited[nx][ny]:
                            visited[nx][ny] = True
                            queue.append((nx, ny))
                
            count = 0
            for x in range(self.N):
                for y in range(self.N):
                    if field[x][y] > current_height and not visited[x][y]:
                        bfs(x, y, current_height, field, visited)
                        count += 1
            return count
    
    if __name__ == '__main__':
        main()
    ```
