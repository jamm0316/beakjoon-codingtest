page link : [https://www.acmicpc.net/problem/15686](https://www.acmicpc.net/problem/15686)

---

# 💡 풀이전략

- 구하고자 하는 값: 치킨 거리의 최솟값

---

- 아이디어: 최소값들의 합은 최소값이다.
- 조건1: 치킨거리의 최솟값을 구하라
- 조건2: 단, 치킨집은 M개이다.
    1. 치킨집 중 M개를 무작위로 고른다(combination을 사용해 모든 경우의 수 구함)
    2. 각집을 기준점으로 두고 무작위 치킨집 M개의 거리를 구한다.
        1. graph를 순회하다 1을 만나면 2와의 거리를 산출한다.
    3. total_distance가 가장 작은 것을 비교하고 반환한다.
        1. min_distance를 연속적으로 더하는 total_distance를 이용
    4. graph를 순회하다 1을 만나면 2와의 거리를 산출한다.
- 주의할점!!!
    - 기준은 집 → 치킨집 거리여야한다.
    - 치킨집 → 집으로 가까운 거리의 합은 같은 집이 중복되서 계산될 수 있다.

## 🎨 사용된 알고리즘

> [!tip]
> Brute-Force: 완전 탐색

---

# code
## Java

```java
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // 입력 처리 부분
        Scanner sc = new Scanner(System.in);
        StringBuilder input_data = new StringBuilder();
        while (sc.hasNextLine()) {
            input_data.append(sc.nextLine()).append("\n");
        }
        sc.close();

        // 입력 데이터를 ParseData에 전달
        ParseData parseData = new ParseData(input_data.toString());
        MinOfChickenStreet converter = new MinOfChickenStreet(parseData.N, parseData.M, parseData.graph);
        System.out.println(converter.isMinStreet());
    }
}

class ParseData {
    int N, M;
    int[][] graph;

    public ParseData(String inputData) {
        String[] lines = inputData.split("\n");
        String[] firstLine = lines[0].split(" ");
        this.N = Integer.parseInt(firstLine[0]);
        this.M = Integer.parseInt(firstLine[1]);

        this.graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] row = lines[i + 1].split(" ");
            for (int j = 0; j < N; j++) {
                this.graph[i][j] = Integer.parseInt(row[j]);
            }
        }
    }
}

class MinOfChickenStreet {
    private int N, M;
    private int[][] graph;
    private List<int[]> houses;
    private List<int[]> chickenStores;

    public MinOfChickenStreet(int N, int M, int[][] graph) {
        this.N = N;
        this.M = M;
        this.graph = graph;
        this.houses = findHouses();
        this.chickenStores = findChickenRestaurants();
    }

    public int isMinStreet() {
        int minDistance = Integer.MAX_VALUE;
        // 치킨집 중 M개 선택한 조합 중 최소 거리를 찾음
        List<List<int[]>> chickenCombinations = combinations(chickenStores, M);
        for (List<int[]> chickenComb : chickenCombinations) {
            int totalDistance = calculateTotalDistance(chickenComb);
            minDistance = Math.min(minDistance, totalDistance);
        }
        return minDistance;
    }

    private int calculateTotalDistance(List<int[]> chickenComb) {
        int totalDistance = 0;
        // 각 집에 대해 가장 가까운 치킨집과의 거리 계산
        for (int[] house : houses) {
            int hx = house[0];
            int hy = house[1];
            int minDist = Integer.MAX_VALUE;
            for (int[] chicken : chickenComb) {
                int cx = chicken[0];
                int cy = chicken[1];
                int dist = Math.abs(hx - cx) + Math.abs(hy - cy);
                minDist = Math.min(minDist, dist);
            }
            totalDistance += minDist;
        }
        return totalDistance;
    }

    private List<int[]> findHouses() {
        List<int[]> houses = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 1) {
                    houses.add(new int[]{i, j});
                }
            }
        }
        return houses;
    }

    private List<int[]> findChickenRestaurants() {
        List<int[]> chickenStores = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 2) {
                    chickenStores.add(new int[]{i, j});
                }
            }
        }
        return chickenStores;
    }

    private List<List<int[]>> combinations(List<int[]> list, int M) {
        List<List<int[]>> result = new ArrayList<>();
        combineHelper(list, new ArrayList<>(), result, 0, M);
        return result;
    }

    private void combineHelper(List<int[]> list, List<int[]> temp, List<List<int[]>> result, int start, int M) {
        if (temp.size() == M) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i < list.size(); i++) {
            temp.add(list.get(i));
            combineHelper(list, temp, result, i + 1, M);
            temp.remove(temp.size() - 1);
        }
    }
}
```

## Python

```python
from itertools import combinations

# 치킨 거리 계산 함수
def calculate_chicken_distance(houses, chicken_stores):
    total_distance = 0
    for hx, hy in houses:
        # 각 집에서 가장 가까운 치킨집과의 거리 구하기
        min_distance = float('inf')
        for cx, cy in chicken_stores:
            distance = abs(hx - cx) + abs(hy - cy)
            min_distance = min(min_distance, distance)
        total_distance += min_distance
    return total_distance

# 입력 처리
N, M = map(int, input().split())
city_map = [list(map(int, input().split())) for _ in range(N)]

houses = []
chicken_stores = []

# 집과 치킨집 위치 저장
for r in range(N):
    for c in range(N):
        if city_map[r][c] == 1:
            houses.append((r, c))
        elif city_map[r][c] == 2:
            chicken_stores.append((r, c))

# M개의 치킨집을 선택하는 모든 조합을 탐색
min_chicken_distance = float('inf')
for chicken_comb in combinations(chicken_stores, M):
    # 선택된 치킨집 조합에 대한 치킨 거리 계산
    city_chicken_distance = calculate_chicken_distance(houses, chicken_comb)
    # 최소 치킨 거리 갱신
    min_chicken_distance = min(min_chicken_distance, city_chicken_distance)

# 결과 출력
print(min_chicken_distance)
```

---

- 기존코드
    
    ```python
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
    ```
