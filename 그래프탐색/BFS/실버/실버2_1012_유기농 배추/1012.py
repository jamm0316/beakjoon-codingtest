import sys
from collections import deque

def main():
    input_data = sys.stdin.read()
    cabbage = OrganicCabbage(input_data)
    cabbage.process_all_case()

class OrganicCabbage:
    def __init__(self, input_data):
        self.parse_data(input_data)
    
    def parse_data(self, input_data):
        lines = input_data.splitlines()
        index = 0
        self. test_case_count = int(lines[index])
        index += 1  #1
        
        self.test_case = []

        for _ in range(self.test_case_count):
            column, row, cabbage_count = map(int, lines[index].split())
            index += 1  #2
            field = [[0] * column for _ in range(row)]
            for _ in range(cabbage_count):
                x, y = map(int, lines[index].split())
                field[y][x] = 1
                index += 1
            self.test_case.append((column, row, cabbage_count, field))

    def process_all_case(self):
        for column, row, cabbage_count, field in self.test_case:
            visited = self.initialized_visited(column, row)
            count = self.count_earthworms(field, visited, column, row)
            print(count)

    def initialized_visited(self, column, row):
        return [[False] * column for _ in range(row)]
    
    def count_earthworms(self, field, visited, column, row):
        def bfs(start_x, start_y):
            queue = deque([(start_x, start_y)])
            visited[start_y][start_x] = True
            direction = [(-1, 0), (1, 0), (0, -1), (0, 1)]

            while queue:
                current_x, current_y = queue.popleft()

                for dx, dy in direction:
                    nx, ny = current_x + dx, current_y + dy
                    if 0 <= nx < column and 0 <= ny < row and field[ny][nx] == 1 and not visited[ny][nx]:
                        visited[ny][nx] = True
                        queue.append([nx, ny])
        
        worm_count = 0
        for y in range(row):
            for x in range(column):
                if field[y][x] == 1 and not visited[y][x]:
                    bfs(x, y)
                    worm_count += 1
        return worm_count

if __name__ == "__main__":
    main()
