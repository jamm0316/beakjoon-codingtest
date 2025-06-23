import sys
from itertools import combinations

def main(input_data):
    parse_data = ParseData(input_data)
    converter = MinOfChickenStreet(parse_data.N, parse_data.M, parse_data.graph)
    print(converter.is_min_street())

class ParseData:
    def __init__(self, input_data):
        lines = input_data.splitlines()
        self.N, self.M = map(int, lines[0].split())
        self.graph = [list(map(int, line.split())) for line in lines[1:]]

class MinOfChickenStreet:
    def __init__(self, N, M, graph):
       self.N = N
       self.M = M
       self.graph = graph
       self.houses = self.__find_houses()
       self.chicken_stores = self.__find_chicken_restaurant()

    def is_min_street(self):
        # 치킨집 중에서 M개 선택한 조합 중 최소 거리를 찾음
        min_distance = float('inf')
        for chicken_comb in combinations(self.chicken_stores, self.M):
            total_distance = self.__calculate_total_distance(chicken_comb)
            min_distance = min(min_distance, total_distance)
        return min_distance

    def __calculate_total_distance(self, chicken_comb):
        total_distance = 0
        # 각 집에 대해 가장 가까운 치킨집과의 거리 계산
        for hx, hy in self.houses:
            min_dist = float('inf')
            for cx, cy in chicken_comb:
                dist = abs(hx - cx) + abs(hy - cy)
                min_dist = min(min_dist, dist)
            total_distance += min_dist
        return total_distance
    
    def __find_chicken_restaurant(self):
        adjacent = []
        for i in range(self.N):
            for j in range(self.N):
                if self.graph[i][j] == 2:
                    adjacent.append((i, j))
        return adjacent

    def __find_houses(self):
        houses = []
        for i in range(self.N):
            for j in range(self.N):
                if self.graph[i][j] == 1:
                    houses.append((i, j))
        return houses
       
if __name__ == '__main__':
    input_data = sys.stdin.read()
    main(input_data)