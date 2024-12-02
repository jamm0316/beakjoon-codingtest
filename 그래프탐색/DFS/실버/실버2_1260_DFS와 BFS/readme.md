page link : [https://www.acmicpc.net/problem/1260](https://www.acmicpc.net/problem/1260)

---

# 💡 풀이전략

- 입력값 받기
    - N: 정점 개수, M: 간선 개수, V: 시작 정점
    - 각 쿼리들은 정점의 간선 관계를 해쉬맵 형태로 나타낼 수 있음.
    - 각 간선은 양방향으로 정의되며, 작은 숫자 순서로 정렬
- DFS, BFS 로직을 작성하고 순서대로 프린트 한다.
    - DFS
        
        ```python
            def dfs(self, n: int, discovered = []):
                discovered.append(n)
                for w in self.graph[n]:
                    if w not in discovered:
                        self.dfs(w)
                return ' '.join(map(str, discovered))
        ```
        
    - BFS
        
        ```python
            def bfs(self, n: int):
                discovered = []
                from collections import deque
                discovered.append(n)
                queue = deque([n])
                while queue:
                    i = queue.popleft()
                    for w in self.graph[i]:
                        if w not in discovered:
                            discovered.append(w)
                            queue.append(w)
                return ' '.join(map(str, discovered))
        ```
        
## 🎨 사용된 알고리즘

> [!tip]
> DFS(Depth-First Search): 깊이 우선 탐색 <br>
> BFS(Breadth-First Search): 너비 우선 탐색

---

# code
## Java

```java
import java.util.*;
import java.io.*;

public class Main {
    static int nodes;
    static int links;
    static int startNode;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nodes = Integer.parseInt(st.nextToken());
        links = Integer.parseInt(st.nextToken());
        startNode = Integer.parseInt(st.nextToken());
        graph = new ArrayList[nodes + 1];  //입력받는 수와 index맞추기 위함
        for (int i = 1; i <= nodes; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < links; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a); // 양방향 그래프
        }

        for (int i = 1; i <= nodes; i++) {
            graph[i].sort(Comparator.naturalOrder());
        }

        boolean[] visited = new boolean[nodes + 1];  //node 실제 숫자
        display(dfs(startNode, new ArrayList<>(), visited));

        visited = new boolean[nodes + 1];
        display(bfs(startNode, visited));

    }

    private static List<Integer> dfs(int nextNode, List<Integer> route, boolean[] visited) {
        visited[nextNode] = true;
        route.add(nextNode);

        for (int neighbor : graph[nextNode]) {
            if (!visited[neighbor]) {
                dfs(neighbor, route, visited);
            }
        }
        return route;
    }

    private static List<Integer> bfs(int nextNode, boolean[] visited) {
        List<Integer> route = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

				// 시작 노드 처리
        queue.add(nextNode);
        visited[nextNode] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            route.add(currentNode);

            for (int neighbor : graph[currentNode]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        return route;
    }

        private static void display(List<Integer> finalGraph) {
        String[] array = finalGraph.stream()
                .map(String::valueOf)
                .toArray(String[]::new);

        System.out.println(String.join(" ", array));
    }
}
```
## Python

```python
import sys

def main():
    input = sys.stdin.read()
    non_linear = Non_Linear()
    non_linear.run_program(input)

class Non_Linear:
    def __init__(self):
        self.N = 0  #정점개수
        self.M = 0  #간선개수
        self.V = 0  #시작정점
        self.graph = {}
  
    def parse_data(self, input):
        lines = input.splitlines()
        self.N, self.M, self.V = map(int, lines[0].split())
        for i in range(1, self.N + 1):  #정점추가
            self.graph[i] = []
        for i in range(1, self.M + 1):  #간선추가
            start, end = map(int, lines[i].split())
            self.graph[start].append(end)
            self.graph[end].append(start)

            # 인접 정점 정렬
            for key in self.graph:
                self.graph[key].sort()

    def run_program(self, input):
        self.parse_data(input)
        print(self.dfs(self.V))
        print(self.bfs(self.V))
    
    def dfs(self, n: int, discovered = []):
        discovered.append(n)
        for w in self.graph[n]:
            if w not in discovered:
                self.dfs(w)
        return ' '.join(map(str, discovered))
    
    def bfs(self, n: int):
        discovered = []
        from collections import deque
        discovered.append(n)
        queue = deque([n])
        while queue:
            i = queue.popleft()
            for w in self.graph[i]:
                if w not in discovered:
                    discovered.append(w)
                    queue.append(w)
        return ' '.join(map(str, discovered))
    
if __name__ == '__main__':
    main()
```

## 해결한 오류

### 1. sort()함수의 3가지 구현방법(Java)

`ArrayList.sort(Comparator)`

- 여기서 Comparator는 Functional Interface로 Comparator를 구현해야함.
- 구현방법 3가지
    - 람다식 → 정수 출력
        
        ```java
        arr.sort((a, b) -> a - b);
        ```
        
    - 람다식 → 비교
        
        ```java
        arr.sort((a, b) -> Integer.Compare(a, b));
        ```
        
    - API사용
        
        ```java
        arr.sort(Comparator.naturalOrder());
        ```
        

**람다식(정수 반환) 사용시 문제점**

- 두 수의 차가 `Integer`의 범위 이상으로 넘어가면, 오버플로우가 일어난다
    
    **예시**
    
    ```java
    import java.util.*;
    
    public class test {
        public static void main(String[] args) {
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(-3);
            arr.add(Integer.MAX_VALUE);
            arr.sort((a, b) -> a - b);  // 계산: -3 - Integer.MAX_VALUE
            System.out.println(arr);
        }
    }
    ```
    
    **실행결과**
    
    ```java
    [2147483647, -3]
    ```
    
    - 이 경우 원한 결과는 오름차순(작은수 먼저)인데, a - b의 값이 오버플로우로 인해 음수를 반환하고, 이로인해 내림차순(큰수 먼저)으로 출력된 것을 확인 할 수 있다.

**Integer.Compare() or Comparator.naturalOrder()의 비교방법**

```java
public static int compare(int x, int y) {
    return (x < y) ? -1 : ((x == y) ? 0 : 1);
}
```

- 실제 값을 비교하여 -1, 0, 1을 반환하기 때문에 오버플로우가 발생할 위험이 없다.

**결론**

1. **람다식 (a, b) -> a - b**는 간단한 방식이지만, 오버플로우로 인해 의도치 않은 정렬 결과가 발생할 수 있다.
2. **안전한 대안**:
    1. `Integer.compare(a, b)`
    2. `Comparator.naturalOrder()`
3. 표준 API를 사용하면 안정성과 가독성이 높아지므로 권장됨.
4. 

### 2. set() 자료구조의 특징 - Python

`set` 자료 구조는 파이썬에서 **중복을 허용하지 않고**, **순서가 없는** 자료 구조. 

### `set`의 주요 특징:

1. **중복된 요소를 허용하지 않음**:
    - `set`은 같은 값을 여러 번 추가해도 한 번만 저장.
    - 예를 들어, `my_set = {1, 2, 2, 3}`이라고 하면, `my_set`에는 `{1, 2, 3}`만 저장.
2. **요소의 순서가 없음**:
    - `set`에 저장된 요소들은 특정 순서가 없음. 이는 삽입 순서와 무관하며, 순회할 때도 순서가 보장 안됨.
    - 예를 들어, `my_set = {3, 1, 2}`이라고 할 때, `my_set`의 요소를 순회(`for item in my_set:`)하면 `1, 2, 3` 순서가 아닌 다른 순서로 나옴.
3. **빠른 검색, 추가, 삭제**:
    - `set`은 내부적으로 해시 테이블을 사용하여 구현되기 때문에, 요소의 **검색**, **추가**, **삭제** 작업이 평균적으로 매우 빠르게 수행.
    - 하지만, 요소가 정렬된 상태를 유지하지 않기 때문에, 순서가 중요한 경우에는 `list`나 `sorted` 등을 사용해야 함.

### `set` 사용의 주된 목적:

- **중복을 제거**하기 위해 사용. 예를 들어, 어떤 리스트에서 중복된 요소를 제거하고자 할 때 `set`으로 변환하면 쉽게 중복을 제거할 수 있음.

```python
my_list = [1, 2, 2, 3, 4, 4, 5]
my_set = set(my_list)  # 중복이 제거된 {1, 2, 3, 4, 5}

```

- **중복된 요소를 방지**하려고 할 때, 추가하려는 데이터가 이미 존재하는지 여부를 신경 쓰지 않고 쉽게 요소를 추가할 수 있음.

### 예시:

```python
my_set = {1, 2, 3}
my_set.add(2)  # 이미 2가 있으므로 추가되지 않음
my_set.add(4)  # 4가 추가됨
print(my_set)  # {1, 2, 3, 4} (순서는 보장되지 않음)

```

### 결론:

- `set`은 **중복을 제거**하고 **빠른 검색**이 필요할 때 유용하지만, **요소의 순서가 중요할 때는 적절하지 않은** 자료 구조.
- 정점 번호와 같은 순서가 중요한 문제에서는 `set`을 사용한 후, 필요한 경우 `sorted()`를 통해 정렬.
