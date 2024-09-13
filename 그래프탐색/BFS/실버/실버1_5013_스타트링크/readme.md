page link : [https://www.acmicpc.net/problem/5014](https://www.acmicpc.net/problem/5014)

---

# 💡 풀이전략

- 구하고자 하는 값: 현재 위치에서 도달위치까지 최소경로

---

- 절차
1. BFS를 이용하여 푼다.
- BFS구조
    1. queue에 각 step을 넣어 독립적으로 실행한다.
    2. step마다 session은 2개(U, D)
    3. 조건이 성립하지 않으면 use the stairs를 default값으로 반환한다.

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
    elevator = Elevator(input_data)
    elevator.print_step()

class Elevator:
    
    def __init__(self, input_data):
        self.parse_data(input_data)
    
    def parse_data(self, input_data):
        self.full_floor, self.start_floor, self.destination, self.up, self.down = map(int, input_data.split())
        self.BUTTON_OPTIONS = [self.up, self.down * -1]

    def count_by_destination(self, start_floor, destination):
        queue = deque([(start_floor, 0)])
        visited = [False] * (self.full_floor + 1)
        visited[start_floor] = True

        while queue:
            current_floor, move_count = queue.popleft()

            if current_floor == destination:
                return move_count
            
            for move in self.BUTTON_OPTIONS:
                next_floor = current_floor + move
                if 1 <= next_floor <= self.full_floor and not visited[next_floor]:
                    queue.append((next_floor, move_count + 1))
                    visited[next_floor] = True
        
        return "use the stairs"
    
    def print_step(self):
        print(self.count_by_destination(self.start_floor, self.destination))

if __name__ == '__main__':
    main()
```
