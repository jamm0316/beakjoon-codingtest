import sys

def main():
    input = sys.stdin.read()
    non_linear = Non_Linear()
    non_linear.run_program(input)

class Non_Linear:
    def __init__(self):
        self.N = 0  #정점개수
        self.M = 0  #간선개수
        self.V = 0  #시작정점
        self.graph = {}
  
    def parse_data(self, input):
        lines = input.splitlines()
        self.N, self.M, self.V = map(int, lines[0].split())
        for i in range(1, self.N + 1):  #정점추가
            self.graph[i] = []
        for i in range(1, self.M + 1):  #간선추가
            start, end = map(int, lines[i].split())
            self.graph[start].append(end)
            self.graph[end].append(start)

            # 인접 정점 정렬
            for key in self.graph:
                self.graph[key].sort()

    def run_program(self, input):
        self.parse_data(input)
        print(self.dfs(self.V))
        print(self.bfs(self.V))
    
    def dfs(self, n: int, discovered = []):
        discovered.append(n)
        for w in self.graph[n]:
            if w not in discovered:
                self.dfs(w)
        return ' '.join(map(str, discovered))
    
    def bfs(self, n: int):
        discovered = []
        from collections import deque
        discovered.append(n)
        queue = deque([n])
        while queue:
            i = queue.popleft()
            for w in self.graph[i]:
                if w not in discovered:
                    discovered.append(w)
                    queue.append(w)
        return ' '.join(map(str, discovered))
    
if __name__ == '__main__':
    main()
