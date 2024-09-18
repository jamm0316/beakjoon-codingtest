from collections import deque

def main():
    input_data = '5 5\n1 3\n1 4\n4 5\n4 3\n3 2'
    converter = LawOfSix(input_data)
    converter.find_min_bacon_number()

class LawOfSix:
    def __init__(self, input_data):
        lines = input_data.splitlines()
        self.person, self.relation = map(int, lines[0].split())
        self.graph = [[] for _ in range(self.person + 1)]
        self.initialize_graph(lines)

    def initialize_graph(self, lines):
        for line in lines[1:]:
            key, value = map(int, line.split())
            self.graph[key].append(value)
            self.graph[value].append(key)

    def find_min_bacon_number(self):
        min_bacon = float('inf')
        min_person = 0
        
        for person in range(1, self.person + 1):
            total_distance = self.bfs(person)
            if total_distance < min_bacon:
                min_bacon = total_distance
                min_person = person
        print(min_person)
    
    def bfs(self, start_person):
        queue = deque([start_person])
        distance = [-1] * (self.person + 1)
        distance[start_person] = 0

        while queue:
            person = queue.popleft()
            for friends in self.graph[person]:
                if distance[friends] == -1:
                    queue.append(friends)
                    distance[friends] = distance[person] + 1
        return sum(distance[1:])

if __name__ == '__main__':
    main()
