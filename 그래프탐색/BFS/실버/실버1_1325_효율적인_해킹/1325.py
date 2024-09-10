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
