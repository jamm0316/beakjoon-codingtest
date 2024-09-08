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
