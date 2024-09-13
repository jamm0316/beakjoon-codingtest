import sys
from collections import deque

def main():
    input_data = sys.stdin.read()
    elevator = Elevator(input_data)
    elevator.print_step()

class Elevator:
    
    def __init__(self, input_data):
        self.parse_data(input_data)
    
    def parse_data(self, input_data):
        self.full_floor, self.start_floor, self.destination, self.up, self.down = map(int, input_data.split())
        self.BUTTON_OPTIONS = [self.up, self.down * -1]

    def count_by_destination(self, start_floor, destination):
        queue = deque([(start_floor, 0)])
        visited = [False] * (self.full_floor + 1)
        visited[start_floor] = True

        while queue:
            current_floor, move_count = queue.popleft()

            if current_floor == destination:
                return move_count
            
            for move in self.BUTTON_OPTIONS:
                next_floor = current_floor + move
                if 1 <= next_floor <= self.full_floor and not visited[next_floor]:
                    queue.append((next_floor, move_count + 1))
                    visited[next_floor] = True
        
        return "use the stairs"
    
    def print_step(self):
        print(self.count_by_destination(self.start_floor, self.destination))

if __name__ == '__main__':
    main()
