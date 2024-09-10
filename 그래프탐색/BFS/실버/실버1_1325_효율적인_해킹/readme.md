page link : [https://www.acmicpc.net/problem/1325](https://www.acmicpc.net/problem/1325)

---

# 💡 풀이전략

1. 미로 탐색과 같이 **모든 경로의 수**를 탐색하는 데에는 **DFS**가 유용하나, **각 노드에 대한 최대값**을 을 찾는데에는 **BFS**가 유용
    - **유용한 이유**
        - **효율적임**
            
            BFS는 한 노드가 도달할 수 있는 지점까지 도달 후 종료되지만, DFS는 끝 점에서 다시 돌아오면서 불필요하게 중복 탐색을 하게 됨.
            
2. 해시테이블 작성 → 역방향으로 링크된 해시테이블
3. visited그래프를 만든 후, 연결된 모든 컴퓨터 count
4. max_hack_count = 0을 사용하여 result list 작성
5. 최종적으로 *(언패킹)을 이용하여 가장 많은 해킹을 할 수 있는 컴퓨터 노드 번호 반환

## 🎨 사용된 알고리즘

> [!tip]
> BFS(Breathd-First Search): 너비 우선 탐색

---

# code

## Python

```python
import sys
from collections import defaultdict, deque

def main():
    input_data = sys.stdin.read()
    simulation = HackingSimulation(input_data)
    result = simulation.find_most_hackable_computers()
    print(*result)
    
class HackingSimulation:
    def __init__(self, input_data):
        self.parse_data(input_data)

    def parse_data(self, input_data):
        data = input_data.splitlines()

        # 첫 줄에서 n과 m을 추출
        self.n, self.m = map(int, data[0].split())

        # 신뢰 관계를 역방향 그래프로 구성
        self.graph = defaultdict(list)
        trust_relations = [tuple(map(int, line.split())) for line in data[1:self.m+1]]
        for a, b in trust_relations:
            self.graph[b].append(a)

    def bfs(self, start):
        visited = [False] * (self.n + 1)
        visited[start] = True
        queue = deque([start])
        count = 1

        while queue:
            node = queue.popleft()

            for neighbor in self.graph[node]:
                if not visited[neighbor]:
                    visited[neighbor] = True
                    queue.append(neighbor)
                    count += 1

        return count

    def find_most_hackable_computers(self):
        max_hack_count = 0
        result = []

        for i in range(1, self.n + 1):
            hack_count = self.bfs(i)

            if hack_count > max_hack_count:
                max_hack_count = hack_count
                result = [i]
            elif hack_count == max_hack_count:
                result.append(i)

        return sorted(result)

if __name__ == '__main__':
    main()
```

## 해결한 오류

### 1. 시간 초과 오류 해결

**알고리즘 변경: DFS → BFS**

- **DFS 선정 이유**
    - **최대값에 대한 정의를 잘못 내림**
        - 가장 많은 해킹을 할 수 있는 노드값을 구하는 것을 최대값이라 정의했다
        여기까지는 올바른 접근이였으나, **최댓값을 모든 경로 탐색으로 잘못 정의함.**
        - 따라서, 불필요하게 이미 방문했던 노드도 다시 방문하면서 성능을 저하시킴
- **BFS로 바꾼 이유**
    - **성능을 향상시키기 위해**
        - 불필요하게 다른 이미 방문했던 노드에 다시 접근하는 것을 피하고, 각 노드별로 접근할 수 있는 최대 컴퓨터 수를 출력하는 로직으로 변경

---

- 기존코드
    
    ```python
    import sys
    
    def main():
        input_data = sys.stdin.read()
        hacking = Hacking(input_data)
        hacking.print_most_weakness_computer()
    
    class Hacking:
        def __init__(self, input_data):
            self.parse_data(input_data)
        
        def parse_data(self, input_data):
            lines = input_data.splitlines()
            self.all_computer, self.all_linked = map(int, lines[0].split())
            self.link_map = {}
            self.construct_link_map(lines)
            self.count_hacked_computer = [0]
    
        def construct_link_map(self, lines):
            for i in range(1, self.all_computer + 1):
                self.link_map[i] = []
            for line in lines[1:]:
                key, value = map(int, line.split())
                self.link_map[value].append(key)
    
        def print_most_weakness_computer(self):
            hacked_computer = [0]
            for node in range(1, self.all_computer + 1):
                self.initialized_option()
                self.dfs(node)
                hacked_computer.append(self.count)
            weakness_computer = self.weakness_computer(hacked_computer)
            print(' '.join(map(str, weakness_computer)))
    
        def weakness_computer(self, hacked_computer):
            weakness_computer = []
            max_num = max(hacked_computer)
            for i in range(len(hacked_computer)):
                if hacked_computer[i] == max_num:
                    weakness_computer.append(i)
            return weakness_computer
    
        def initialized_option(self):
            self.count = 0
            self.visited = [False] * (self.all_computer + 1)
    
        def dfs(self, node):
            #조건
            if not self.visited[node]:
                self.count += 1
                self.visited[node] = True
            #실행
            for computer in self.link_map[node]:
                self.dfs(computer)
    
    if __name__ == '__main__':
        main()
    ```
