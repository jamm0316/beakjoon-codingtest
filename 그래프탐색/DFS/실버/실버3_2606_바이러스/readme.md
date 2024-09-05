page link : [https://www.acmicpc.net/problem/2606](https://www.acmicpc.net/problem/2606)

# 💡 풀이전략

1. DFS를 이용하여 모든 경우의 수 구하기.
    1. (int)infected_coumputer 를 static변수로 저장해두고, dfs의 조건이 충족될 때마다 infected_coumputer += 1 을 해준 후 마지막에 static 변수의 값 출력.
2. 양방향 연결이므로, key, value값을 한번씩 번갈아가며 추가해주기.

## 🎨 사용된 알고리즘

> [!tip]
> DFS(Depth-First Search): 깊이 우선 탐색

---

# code

## Python

```python
def main():
    input_data = '7\n6\n1 2\n2 3\n1 5\n5 2\n5 6\n4 7'
    virus = Virus(input_data)
    virus.spread_virus()

class Virus:
    def __init__(self, input_data):
        self.parse_data(input_data)
    
    def parse_data(self, input_data):
        lines = input_data.splitlines()
        
        computer = int(lines[0])  #컴퓨터 대수
        self.linked_network = int(lines[1])  #연결된 경로 수
        self.network = self.initialized_network(lines, computer)  #연결 관계
        self.visited = self.initialized_visited(computer)
        self.infected_computer = 0

    def initialized_network(self, lines, computer):
        network = {}
        for i in range(1, computer + 1):
            network[i] = []
        for line in lines[2:]:
            key, value = map(int, line.split())
            network[key].append(value)
            network[value].append(key)  #양방향 연결이므로
        return network

    def initialized_visited(self, computer):
        return [False] * (computer + 1)
    
    def spread_virus(self):
        self.dfs(1)
        print(self.infected_computer - 1)
        
    def dfs(self, node):
        self.visited[node] = True
        self.infected_computer += 1
        for neighbor in self.network[node]:
            if not self.visited[neighbor]:
                self.dfs(neighbor)

if __name__ == '__main__':
    main()
```
