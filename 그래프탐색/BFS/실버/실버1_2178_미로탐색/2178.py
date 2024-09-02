import sys
from collections import deque

def main():
    input_data = sys.stdin.read()
    maze = Maze(input_data)
    maze.find_exit()

class Maze:
    def __init__(self, input_data):
        self.parse_data(input_data)
    
    def parse_data(self, input_data):
        lines = input_data.splitlines()
        self.N, self.M = map(int, lines[0].split())
        self.maze = [list(map(int, line)) for line in lines[1:]]
        self.visited = [[False] * self.M for _ in range(self.N)]

    def find_exit(self):
        directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
        queue = deque([((0, 0), 1)])
        self.visited[0][0] = True

        while queue:
            (x, y), count = queue.popleft()

            if (x, y) == (self.N - 1, self.M - 1):
                print(count)
                return

            for dx, dy in directions:
                nx, ny = x + dx, y + dy

                if 0 <= nx < self.N and 0 <= ny < self.M and self.maze[nx][ny] == 1 and not self.visited[nx][ny]:
                    self.visited[nx][ny] = True
                    queue.append(((nx, ny), count + 1))

if __name__ == '__main__':
    main()
