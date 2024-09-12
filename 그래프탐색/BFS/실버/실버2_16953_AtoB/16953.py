import sys
from collections import deque

def main():
    input_data = sys.stdin.read()
    converter = AtoBConverter(input_data)
    converter.print_min_operations()

class AtoBConverter:
    def __init__(self, input_data):
        self.parse_data(input_data)
        
    def parse_data(self, input_data):
        self.start, self.goal = map(int, input_data.split())
    
    def get_min_operations(self) -> int:
        queue = deque([(self.start, 1)])
        visited = set([self.start])

        while queue:
            current, steps = queue.popleft()

            if current == self.goal:
                return steps

            for next_num in [current * 2, current * 10 + 1]:
                if next_num <= self.goal and next_num not in visited:
                    visited.add(next_num)
                    queue.append((next_num, steps + 1))
        
        return -1
    
    def print_min_operations(self) -> int:
        print(self.get_min_operations())

if __name__ == "__main__":
    main()
