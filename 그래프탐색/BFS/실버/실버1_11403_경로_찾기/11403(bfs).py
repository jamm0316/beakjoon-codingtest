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
