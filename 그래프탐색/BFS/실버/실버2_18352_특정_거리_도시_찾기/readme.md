page link : [https://www.acmicpc.net/problem/18352](https://www.acmicpc.net/problem/18352)

---

# 💡 풀이전략

- 구하고자하는 값: x거리에 있는 모든 도시(한줄에 하나씩, 오름차순)
- 하나도 존재하지 않으면 -1 출력

---

- 해당 조건을 만족하는 모든 그래프의 노드값을 반환하는 문제로 BFS를 사용해 답을 구하는 것이 좋음.
- queue값에 distacne도 함께 포함시켜, definition과 distance가 같은 node값 출력

## 🎨 사용된 알고리즘

> [!tip]
> BFS(Breathd-First Search): 너비 우선 탐색

---

# code

## Python

```python
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
```

## 해결한 오류

### 1. 리펙터링

- **메모리 최적화**
1. **그래프 생성 (defaultdict → list)**
    
    > **고정된 인덱스를 사용하는 상황**에서는 키와 값을 쌍으로 저장하는 해시테이블 구조를 띈 defaultdict보다 인덱스를 기반으로 메모리를 관리하는 list 자료구조가 더욱 효율적임.
    > 
    - 기존 코드
        - defaultdict(list)는 큰 규모 데이터에서 오버헤드를 발생시킬 수 있음.
    - 수정된 코드
        - 따라서 list를 사용하면 (index → 도시번호, value → 연결된 도시)로 같은 효과를 누릴 수 있음.

- **메모리, 시간복잡도 최적화**
1. **find_cities 함수 개선**
    - **탐색 중단**
        - 기존 코드
            - 최단 거리를 찾는 문제이므로 move_count 가 definition_count보다 큰 경우는 불필요한 메모리를 차지한다.
        - 수정된 코드
            - 따라서, next_city 방문 조건에
            `if **move_count < definition_count** and not visited[next_city]`
                
                를 추가하여, 추가 탐색을 막아 메모리를 최적화 한다.
                
    
    - **방문 처리 최적화**
        - 기존 코드
            - visited 자료구조는 set 형태이다.
        - 수정된 코드
            - visited 자료구조는 list 형태이다.
        - set 대신 list를 사용하는 이유
            - 메모리 효율성
                1. set은 내부적으로 해시테이블 구조이므로 list보다 메모리 사용량이 크다.
                2. 특히 도시 번호가 매우 큰 경우 메모리 오버헤드가 발생한다.
                    
                    > 해시테이블의 경우 해시 충돌을 방지하기 위해 빈공간인 load_factor를 할당하하므로, 데이터 사용량이 많다
                    > 
            - 접근 속도 향상
                1. 특정 도시 방문 확인 작업이 O(1)로 고정되어 있지만 set은 최악의 경우 해시 충돌로 인해 O(n)까지 걸릴 수 있음.
                    
                    > 해시 테이블은 각 키마다 정해진 해시값을 사용하는데, 크기가 커지면 여러 키가 동일한 해시 인덱스에 할당될 수 있고, 이는 해시 충돌로 이어질 수 있다.
                    > 
    
2. **pint_cities 함수 개선**
    
    city_list의 데이터가 커지면 for문을 통해 하나씩 출력하는 것 보다 한번에 출력하는 것이 더욱 효율적임.
    
    - 기존 코드
        - for 문을 통해 하나씩 출력
    - 수정된 코드
        - `‘\n’.join(map(str, citiy_list))`를 통해 한번에 출력

---

- 기존코드
    
    ```python
    import sys
    from collections import defaultdict, deque
    
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
            self.graph = defaultdict(list)
            for line in lines[1:]:
                key, value = map(int, line.split())
                self.graph[key].append(value)
        
        def find_cities(self, start_city, definition_count):
            queue = deque([(start_city, 0)])
            visited = set([start_city])
            city_list = []
    
            while queue:
                city, move_count = queue.popleft()
    
                if move_count == definition_count:
                    city_list.append(city)
                
                for next_city in self.graph[city]:
                    if next_city not in visited:
                        queue.append((next_city, move_count + 1))
                        visited.add(next_city)
            return sorted(city_list)
    
        def print_cities(self):
            city_list = self.find_cities(self.start_city, self.definition_count)
            if len(city_list) != 0:
                for city in city_list:
                    print(city)
            else:
                print(-1)
    
    if __name__ == '__main__':
        main()
    ```
    

---
