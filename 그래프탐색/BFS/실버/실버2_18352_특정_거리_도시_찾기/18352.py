import sys
from collections import deque

def main():
    input_data = sys.stdin.read()
    find_cities = FindCities(input_data)
    find_cities.print_cities()

class FindCities:
    def __init__(self, input_data):
        self.parse_data(input_data)

    def parse_data(self, input_data):
        lines = input_data.splitlines()
        self.num_of_cities, self.link_of_cities, self.definition_count, self.start_city = map(int, lines[0].split())
        self.graph = [[] for _ in range(self.num_of_cities + 1)]
        for line in lines[1:]:
            key, value = map(int, line.split())
            self.graph[key].append(value)
    
    def find_cities(self, start_city, definition_count):
        queue = deque([(start_city, 0)])
        visited = [False] * (self.num_of_cities + 1)
        visited[start_city] = True
        city_list = []

        while queue:
            city, move_count = queue.popleft()

            if move_count == definition_count:
                city_list.append(city)
            
            for next_city in self.graph[city]:
                if move_count < definition_count and not visited[next_city]:
                    queue.append((next_city, move_count + 1))
                    visited[next_city] = True
        return sorted(city_list)

    def print_cities(self):
        city_list = self.find_cities(self.start_city, self.definition_count)
        if city_list:
            print('\n'.join(map(str, city_list)))
        else:
            print(-1)

if __name__ == '__main__':
    main()
