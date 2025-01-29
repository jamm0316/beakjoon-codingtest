page link : [https://www.acmicpc.net/problem/24444](https://www.acmicpc.net/problem/24444)

---

# 풀이전략

1. 모든 입력값을 받는다
    1. 정점개수, 링크 수, 시작 정점
2. 시작 정점 부터 탐색할 노드를 Queue에 넣어준다.
3. visited로 방문 체크를 한다.
4. 낮은 숫자부터 오름차순으로 방문한다.
5. sequence를 만들어 해당 인덱스에 숫자를 저장한다.

## 사용된 알고리즘

그래프 이론: bfs

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int links = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());
        List<List<Integer>> graph = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(i, new ArrayList<>());
        }

        for (int i = 1; i <= links; i++) {
            int[] EachLinks = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int start = EachLinks[0];
            int end = EachLinks[1];
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        for (List<Integer> each : graph) {
            each.sort(Comparator.naturalOrder());
        }

        int[] sequence = bfs(graph, visited, startNode, N);

        for (int i = 1; i < sequence.length; i++) {
            bw.write(String.valueOf(sequence[i]));
            if (i < sequence.length - 1) {
                bw.newLine();
            }
        }
        bw.close();
        br.close();
    }

    private static int[] bfs(List<List<Integer>> graph, boolean[] visited, int startNode, int N) {
        Queue<Integer> queue = new LinkedList<>();
        int count = 1;
        int[] sequence = new int[N + 1];

        //initialize
        sequence[startNode] = count;
        queue.offer(startNode);
        visited[startNode] = true;

        //bfs
        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            for (int neighbor : graph.get(curNode)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    sequence[neighbor] = ++count;
                    queue.offer(neighbor);
                }
            }
        }
        return sequence;
    }
}

```

## 해결한 오류

### 1. 3가지 오류 수정

1. **입력값을 `links` 수만큼 받아야함.**
    
    `node` 수만큼 받아서 입력에 문제가있었음.
    
    **기존코드**
    
    ```java
    for (int i = 1; i <= N; i++) {
        int[] EachLinks = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    ```
    
    **수정된 코드**
    
    ```java
    for (int i = 1; i <= links; i++) {
        int[] EachLinks = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    ```
    
2. visited[curNode] = true; 중복 체크
    
    `visited[neighbor]`에서 방문 체크 후에 `neighbor`로 넘어가면 `curNode`는 체크할 필요가 없음.
    
    **기존코드**
    
    ```java
    while (!queue.isEmpty()) {
        int curNode = queue.poll();
        for (int neighbor : graph.get(curNode)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                visited[curNode] = true;
                sequence[neighbor] = ++count;
                queue.offer(neighbor);
            }
        }
    }
    ```
    
    **수정된 코드**
    
    ```java
    while (!queue.isEmpty()) {
        int curNode = queue.poll();
        for (int neighbor : graph.get(curNode)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                sequence[neighbor] = ++count;
                queue.offer(neighbor);
            }
        }
    }
    ```
    
3. 정확한 sequence 배열 크기 설정
    
    `int[] sequence = new int[N + 1]` 로 정확한 방문순서 저장
    
    **기존코드**
    
    ```java
    int[] sequence = new int[graph.size()];
    ```
    
    **수정된 코드**
    
    ```java
    int[] sequence = new int[N + 1];
    ```
    

### 2. solution

---

- 기존코드
    
    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            int N = Integer.parseInt(st.nextToken());
            int links = Integer.parseInt(st.nextToken());
            int startNode = Integer.parseInt(st.nextToken());
            List<List<Integer>> graph = new ArrayList<>();
            boolean[] visited = new boolean[N + 1];
    
            for (int i = 0; i <= N; i++) {
                graph.add(i, new ArrayList<>());
            }
    
            for (int i = 1; i <= N; i++) {
                int[] EachLinks = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int start = EachLinks[0];
                int end = EachLinks[1];
                graph.get(start).add(end);
                graph.get(end).add(start);
            }
    
            for (List<Integer> each : graph) {
                each.sort(Comparator.naturalOrder());
            }
    
            int[] sequence = bfs(graph, visited, startNode);
    
            for (int i = 1; i < sequence.length; i++) {
                bw.write(String.valueOf(sequence[i]));
                if (i < sequence.length - 1) {
                    bw.newLine();
                }
            }
            bw.close();
            br.close();
        }
    
        private static int[] bfs(List<List<Integer>> graph, boolean[] visited, int startNode) {
            Queue<Integer> queue = new LinkedList<>();
            int count = 1;
            int[] sequence = new int[graph.size()];
    
            //initialize
            sequence[startNode] = count;
            queue.offer(startNode);
            visited[startNode] = true;
    
            //bfs
            while (!queue.isEmpty()) {
                int curNode = queue.poll();
                for (int neighbor : graph.get(curNode)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        visited[curNode] = true;
                        sequence[neighbor] = ++count;
                        queue.offer(neighbor);
                    }
                }
            }
            return sequence;
        }
    }
    
    ```
