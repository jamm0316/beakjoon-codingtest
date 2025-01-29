page link : [https://www.acmicpc.net/problem/10451](https://www.acmicpc.net/problem/10451)

---

# 풀이전략

1. 그래프를 입력받음. (단방향)
2. visited를 사용하여 방문한 곳은 재방문 안함.
3.  for를 통해 인덱스 만큼 순회
    1. bfs에 start를 넣어서 순회
    2. 순회하며, start와 현재 값이 일치하면 count++

## 사용된 알고리즘

그래프 이론: dfs
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

        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            int nodes = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
            int[] graph = new int[nodes + 1];
            boolean[] visited = new boolean[nodes + 1];

            //initialize
            st = new StringTokenizer(br.readLine());
            for (int node = 1; node <= nodes; node++) {
                graph[node] = Integer.parseInt(st.nextToken());
            }

            //dfs
            int count = 0;
            for (int node = 1; node <= nodes; node++) {
                if (!visited[node]) {
                    dfs(node, graph, visited);
                    count++;
                }
            }

            //result
            bw.write(String.valueOf(count));
            bw.newLine();
        }
        bw.close();
        br.close();
    }

    private static void dfs(int node, int[] graph, boolean[] visited) {
        visited[node] = true;
        int nextNode = graph[node];
        if (!visited[nextNode]) {
            dfs(nextNode, graph, visited);
        }
    }
}
```

## 해결한 오류

### 1. 함수 밖 visited, 함수 내부 visited

1. 함수 밖 `vistited`
    1. 전체 그래프의 방문 상태를 일관되게 추적하기 위함.
2. 함수 내부 `visited` 
    1. 현재 탐색 중인 사이클의 노드들을 정확히 표시할 수 있음.
