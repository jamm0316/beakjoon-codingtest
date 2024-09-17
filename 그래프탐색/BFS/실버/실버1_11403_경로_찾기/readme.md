page link : [https://www.acmicpc.net/problem/11403](https://www.acmicpc.net/problem/11403)

---

# 💡 풀이전략

1. 그래프 관계도 만들기
    1. list형태로 index를 각 그래프의 node로 사용
2. 그래프 관계도를 모두 돌아다니며(for), BFS를 실행
    1. BFS실행시 node 수 만큼 [0] * count_node 초기화
    2. 그래프의 노드의 값을 탐색하면서 초기화된 리스트 인덱스를 0 → 1로 변경
    3. 해당 리스트 반환
3. 총 반환된 n개의 리스트 for과 *을 통해 반환

## 🎨 사용된 알고리즘

> [!tip]
> BFS(Breadth-First Search): 너비 우선 탐색
> Floyd-Warshall Algorithm: 플로이드-워셜 알고리즘

---

# code

## Python → BFS

```python
import sys
from collections import deque

def main():
    input_data = sys.stdin.read()
    converter = FindPathConvert(input_data)
    converter.print_graph()

class FindPathConvert:
    def __init__(self, input_data):
        lines = input_data.splitlines()
        self.N = int(lines[0])
        self.graph = [[] for _ in range(self.N)]
        self.initialize_graph(lines)

    def initialize_graph(self, lines):
        for i in range(self.N):
            link_map = list(map(int, lines[i + 1].split()))
            for key, value in enumerate(link_map):
                if value == 1:
                    self.graph[i].append(key)

    def print_graph(self):
        link_graph = self.draw_map()
        for line in link_graph:
            print(*line)

    def draw_map(self):
        final_graph = []
        for given_node in range(self.N):
            final_graph.append(self.bfs(given_node))
        return final_graph

    def bfs(self, given_node):
        queue = deque([given_node])
        visited = [False] * self.N
        each_graph = [0] * self.N

        while queue:
            node = queue.popleft()
            for next_node in self.graph[node]:
                if not visited[next_node]:
                    each_graph[next_node] = 1
                    visited[next_node] = True
                    queue.append(next_node)
        return each_graph

if __name__ == '__main__':
    main()
```

## Python → Floyd-Warshall

```python
def floyd_warshall(N, graph):
    # 모든 노드에서 다른 노드로의 경로 정보를 업데이트
    for k in range(N):
        for i in range(N):
            for j in range(N):
                if graph[i][k] == 1 and graph[k][j] == 1:
                    graph[i][j] = 1

def main():
    N = int(input())
    graph = [list(map(int, input().split())) for _ in range(N)]
    
    floyd_warshall(N, graph)
    
    # 결과 출력
    for line in graph:
        print(*line)

if __name__ == '__main__':
    main()
```
