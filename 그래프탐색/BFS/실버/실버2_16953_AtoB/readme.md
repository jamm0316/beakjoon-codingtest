page link : [https://www.acmicpc.net/problem/16953](https://www.acmicpc.net/problem/16953)

---

# 💡 풀이전략

- 구하려는 값: A가 B가 되는데 필요한 최소 연산값
- 연산 조건(A * 2, A1)

---

- 절차 1. BFS를 이용하여 각 스텝마다 2가지 경우의 수를 계산하고, 그 중 목표한 수에 도달했을 때 스텝 수를 반환한다.
    - 이 때, 스텝을 진행하는 수는 목표하는 수를 넘어서면 안된다.

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
    converter = AtoBConverter(input_data)
    converter.print_min_operations()

class AtoBConverter:
    def __init__(self, input_data):
        self.parse_data(input_data)
        
    def parse_data(self, input_data):
        self.start, self.goal = map(int, input_data.split())
    
    def get_min_operations(self) -> int:
        queue = deque([(self.start, 1)])
        visited = set([self.start])

        while queue:
            current, steps = queue.popleft()

            if current == self.goal:
                return steps

            for next_num in [current * 2, current * 10 + 1]:
                if next_num <= self.goal and next_num not in visited:
                    visited.add(next_num)
                    queue.append((next_num, steps + 1))
        
        return -1
    
    def print_min_operations(self) -> int:
        print(self.get_min_operations())

if __name__ == "__main__":
    main()
```

## 해결한 오류

### 1. 리펙터링

1. **연산 로직 및 출력 처리 메서드 이름 변경**
    - 연산로직: `count_step` → `get_min_operations`
    - 출력 처리 메서드: `print_step` → `print_min_operations`
2. **연산로직 리펙터링**
    - 기존 문자열을 사용해 연산한 후 다시 정수로 바꿨던 방식에서current * 2와 current * 10 + 1로 처리해 정수만 사용하는 방식으로 바꿈.
        
        **기존코드**
        
        ```python
        def count_step(self):
        ...
            while queue:
                (x, count_step) = queue.popleft()
                **x = str(x)
                if int(x) == self.goal:**
                    return count_step
                
                **for i in [int(x) * 2, int(x + '1')]:
                    nx = i**
                    if nx <= self.goal:
                        queue.append((nx, count_step + 1))
            return -1
        
        ```
        
        **수정된 코드**
        
        ```python
        def get_min_operations(self) -> int:
        ...
            while queue:
                current, steps = queue.popleft()
                if current == self.goal:
                    return steps
        
                **for next_num in [current * 2, current * 10 + 1]:**
                    if next_num <= self.goal and next_num not in visited:
                        visited.add(next_num)
                        queue.append((next_num, steps + 1))
            
            return -1
        ```
        

### 2. 집합(set)을 사용하여 중복 제외

- `visited = set([self.start])`
    - set을 사용하여 이미 방문한 숫자를 기록하고, 이를 통해 같은 숫자가 중복해서 큐에 들어가거나 탐색되지 않도록 함.
    - 이유: 같은 숫자가 나오면 그 뒤에 나오는 모든 경우의 수는 같기 때문에 다시 탐색할 필요가없음.
    
    **수정된 코드**
    
    ```python
    def get_min_operations(self) -> int:
        queue = deque([(self.start, 1)])
        visited = set([self.start])
    ```
    

---

- 기존코드
    
    ```python
    import sys
    from collections import deque
    
    def main():
        input_data = sys.stdin.read()
        atb = AtoB(input_data)
        atb.print_step()
    
    class AtoB:    
        def __init__(self, input_data):
            self.parse_data(input_data)
    
        def parse_data(self, input_data):
            self.start, self.goal = map(int, input_data.split())
    
        def print_step(self):
            print(self.count_step())
    
        def count_step(self):
            count_step = 1
            queue = deque([(self.start, count_step)])
    
            while queue:
                (x, count_step) = queue.popleft()
                x = str(x)
                if int(x) == self.goal:
                    return count_step
                
                for i in [int(x) * 2, int(x + '1')]:
                    nx = i
                    if nx <= self.goal:
                        queue.append((nx, count_step + 1))
            return -1
    
    if __name__ == '__main__' :
        main()
    ```
    
