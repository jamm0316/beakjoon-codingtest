page link : [https://www.acmicpc.net/problem/1389](https://www.acmicpc.net/problem/1389)

---

# 💡 풀이전략

- 구하고자 하는 값
    - 가장 작은 관계를 가진 사람
---

절차1. 각 관계 그래프 그리기(쌍방향)
절차2. 모든 사람을 순환
절차3. 각 순환에 대해 BFS실시(리스트 index별, distance 구하기)
    절차3-1. distance 전체 합 반환
절차4. min_bacon, min_person 지정하고 min_person 반환

## 🎨 사용된 알고리즘

> [!tip]
> BFS(Breadth-First Search): 너비 우선 탐색

---

# code

## Python

```python
import sys
from collections import deque

def main():
    input_data = sys.stdin.read()
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

```

## 해결한 오류

### 1. 리펙터링

1. relationship(list) 대신 min_bacon, min_person(int) 이용
    1. 장점
        1. 리스트 순회 생략
            - min_bacon을 찾기 위해 리스트를 한번 더 순회해야함. 그러나 min_bacon과 min_person을 사용하면 bfs실행과 동시에 조건 검색이 가능.
        2. 메모리 절약
            - relationship 리스트는 각 사람에 대해 BFS를 실행한 결과를 모두 저장하기 때문에 N명의 사용가자 있을 떄 N개의 요소를 메모리에 저장.
            - 그러나 min_bacon과 min_person만 사용하면 두개의 변수만 메모리에 유지하면서 최소값 추적 가능.
        3. 간결성
    - 기존 코드
        
        ```python
        def count_min_friends(self):
        		relationship = []
        		for person in range(1, self.person + 1):
        		    count = self.bfs(person)
        		    relationship.append(count)
        		min_relationship = min(relationship)
        		for person, bacon in enumerate(relationship):
        		    if bacon == min_relationship:
        		        return (person + 1)
        ```
        
    
    - 수정된 코드
        
        ```python
        def find_min_bacon_number(self):
            min_bacon = float('inf')
        		min_person = 0
        		
        		for person in range(1, self.person + 1):
        		    total_distance = self.bfs(person)
        		    if total_distance < min_bacon:
        		        min_bacon = total_distance
        		        min_person = person
        		print(min_person)
        ```
        

---

- 기존코드
    
    ```python
    import sys
    from collections import deque
    
    def main():
        input_data = sys.stdin.read()
        converter = LawOfSix(input_data)
        converter.print_min_relationship()
    
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
        
        def print_min_relationship(self):
            print(self.count_min_friends())
    
        def count_min_friends(self):
            relationship = []
            for person in range(1, self.person + 1):
                count = self.bfs(person)
                relationship.append(count)
            min_relationship = min(relationship)
            for person, bacon in enumerate(relationship):
                if bacon == min_relationship:
                    return (person + 1)
        
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
    ```
    
