page link : [https://www.acmicpc.net/problem/24480](https://www.acmicpc.net/problem/24480)

---

# 풀이전략

1. `node`, `links`, `startNode` 순으로 입력값 받음
    1. `for links` 만큼 `List<> graph` 채움
2. `int[] seqeunce` 변수를 통해 `index`별 방문 숫자 저장
3. `count`를 사용하여 전체적으로 방문 회차 관리
4. 각 `node`별 `links`의 배열을 내림차순으로 정렬
    1. `Comparator.reverseOrder()`
5. `startNode`에서 각 `node`를 순회
    1. 해당 `node`가 `!visited`일 경우, `return count`
    2. `depth`가 `+1` 될때마다 `count`를 `+1`시킨다.

## 사용된 알고리즘

그래프 이론: dfs

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited;
    static int[] sequence;
    static int depth = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodes = Integer.parseInt(st.nextToken());
        int links = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());
        List<List<Integer>> graph = new ArrayList<>();
        visited = new boolean[nodes + 1];
        sequence = new int[nodes + 1];

        //initialize graph nodes
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }

        //initialize graph links
        for (int i = 0; i < links; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int start = input[0];
            int end = input[1];
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        //sorting desc
        for (int i = 0; i <= nodes; i++) {
            graph.get(i).sort(Comparator.reverseOrder());
        }

        //dfs
        dfs(graph, startNode);
        for (int i = 1; i < sequence.length; i++) {
            bw.write(String.valueOf(sequence[i]));
            bw.newLine();
        }
        bw.close();
        br.close();
    }

    private static void dfs(List<List<Integer>> graph, int node) {
        visited[node] = true;
        sequence[node] = depth++;

        for (int next : graph.get(node)) {
            if (!visited[next]) {
                dfs(graph, next);
            }
        }
    }
}

```

## 해결한 오류

### 1. 각 desc sort가 필요한 node의 범위

기존에는 `i < node` 만큼 순회하며 sort실행

그러나, `i <= node`만큼 순회해야 마지막 node까지 sort완료됨.

**기존코드**

```java
//sorting desc
**for (int i = 0; i < nodes; i++) {**
    graph.get(i).sort(Comparator.reverseOrder());
}
```

**수정코드**

```java
//sorting desc
**for (int i = 0; i <= nodes; i++) {**
    graph.get(i).sort(Comparator.reverseOrder());
}
```

### 2. dfs 수정

`dfs`는 1번 호출되며, `visited`를 `true`로 만들고, `sequence[node]`에 `depth`를 바인딩 한 후, `depth`를 `+1` 해야함.

**기존코드**

```java
private static void dfs(List<List<Integer>> graph, boolean[] visited, int startNode, int depth) {
    if (!visited[startNode]) {
        visited[startNode] = true;
        sequence[startNode] = depth;
    } else {
        return;
    }

    if (graph.get(startNode).size() != 0) {
        depth++;
    } else {
        depth = 0;
    }

    for (int i : graph.get(startNode)) {
        dfs(graph, visited, i, depth);
    }
}
```

**수정코드**

```java
private static void dfs(List<List<Integer>> graph, int node) {
    visited[node] = true;
    sequence[node] = depth++;

    for (int next : graph.get(node)) {
        if (!visited[next]) {
            dfs(graph, next);
        }
    }
}
```

---

- 기존코드
    
    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static boolean[] visited;
        static int[] sequence;
    
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            int nodes = Integer.parseInt(st.nextToken());
            int links = Integer.parseInt(st.nextToken());
            int startNode = Integer.parseInt(st.nextToken());
            List<List<Integer>> graph = new ArrayList<>();
            visited = new boolean[nodes + 1];
            sequence = new int[nodes + 1];
    
            //initialize graph nodes
            for (int i = 0; i <= nodes; i++) {
                graph.add(new ArrayList<>());
            }
    
            //initialize graph links
            for (int i = 0; i < links; i++) {
                int[] input = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int start = input[0];
                int end = input[1];
                graph.get(start).add(end);
                graph.get(end).add(start);
            }
    
            //sorting desc
            for (int i = 0; i < nodes; i++) {
                graph.get(i).sort(Comparator.reverseOrder());
            }
    
            //dfs
            dfs(graph, visited, startNode, 1);
            
            //output
            for (int i = 1; i < sequence.length; i++) {
                bw.write(String.valueOf(sequence[i]));
                bw.newLine();
            }
            bw.close();
            br.close();
        }
    
        private static void dfs(List<List<Integer>> graph, boolean[] visited, int startNode, int depth) {
            if (!visited[startNode]) {
                visited[startNode] = true;
                sequence[startNode] = depth;
            } else {
                return;
            }
    
            if (graph.get(startNode).size() != 0) {
                depth++;
            } else {
                depth = 0;
            }
    
            for (int i : graph.get(startNode)) {
                dfs(graph, visited, i, depth);
            }
        }
    }
    
    ```
