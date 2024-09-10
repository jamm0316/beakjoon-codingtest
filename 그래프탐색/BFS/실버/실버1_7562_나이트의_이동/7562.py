import sys
from collections import deque

def main():
    input_data =  sys.stdin.read()
    knight = ChessKnight(input_data)
    knight.process_all_cases()

class ChessKnight:
    # 나이트 이동 가능한 방향을 상수로 지정
    DIRECTIONS = [(-1, 2), (-2, 1), (-2, -1), (-1, -2), 
                  (1, -2), (1, 2), (2, 1), (2, -1)]

    def __init__(self, input_data):
        self.parse_data(input_data)

    def parse_data(self, input_data):
        lines = input_data.splitlines()
        self.test_cases = int(lines[0])
        self.queries = []
        for i in range(1, len(lines[1:]), 3):
            size = int(lines[i])
            start = tuple(map(int, lines[i + 1].split()))
            goal = tuple(map(int, lines[i + 2].split()))
            self.queries.append((size, start, goal))

    def process_all_cases(self):
        for field_size, (start_x, start_y), (goal_x, goal_y) in self.queries:
            result = self.find_minimum_moves(start_x, start_y, goal_x, goal_y, field_size)
            print(result)

    def find_minimum_moves(self, start_x, start_y, goal_x, goal_y, field_size):
        visited = [[False] * field_size for _ in range(field_size)]
        visited[start_x][start_y] = True
        queue = deque([[(start_x, start_y), 0]])

        while queue:
            (x, y), move_count = queue.popleft()
            if (x, y) == (goal_x, goal_y):
                return move_count
            for dx, dy in ChessKnight.DIRECTIONS:
                nx, ny = x + dx, y + dy
                if 0 <= nx < field_size and 0 <= ny < field_size and not visited[nx][ny]:
                    visited[nx][ny] = True
                    queue.append([(nx, ny), move_count + 1])
        # 만약 도달할 수 없으면, 기본적으로 이 문제가 발생하지 않지만 안전을 위해 -1 반환
        return -1

if __name__ == '__main__':
    main()
