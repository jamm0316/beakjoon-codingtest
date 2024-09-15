import sys
from collections import deque

def main():
    input_data = sys.stdin.read()
    converter = DegreeOfKindship(input_data)
    converter.print_degree()

class DegreeOfKindship:
    def __init__(self, input_data):
        self.parse_data(input_data)
    
    def parse_data(self, input_data):
        lines = input_data.splitlines()
        self.family = int(lines[0])
        self.start_person, self.goal_person = map(int, lines[1].split())
        self.graph = [[] for _ in range(self.family + 1)]
        self.initialize_graph(lines)

    def initialize_graph(self, lines):
        for line in lines[3:]:
            parents, child = map(int, line.split())
            self.graph[parents].append(child)
            self.graph[child].append(parents)
    
    def find_degree(self,start_person, goal_person):
        queue = deque([(start_person, 0)])
        visited = [False] * (self.family + 1)
        visited[start_person] = True
        
        while queue:
            person, degree = queue.popleft()
            
            if person == goal_person:
                return degree
            
            for next_person in self.graph[person]:
                if not visited[next_person]:
                    queue.append((next_person, degree + 1))
                    visited[next_person] = True
        return -1
    
    def print_degree(self):
        print(self.find_degree(self.start_person, self.goal_person))

if __name__ == '__main__':
    main()
