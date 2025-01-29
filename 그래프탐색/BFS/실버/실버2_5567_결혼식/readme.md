page link : [https://www.acmicpc.net/problem/5567](https://www.acmicpc.net/problem/5567)

---

# 풀이전략

전제 조건: 자신의 친구와 친구의 친구를 초대한다

따라서 depth가 1인친구와 2인친구까지 초대한다는 뜻이다.

1. 모든 링크를 양방향으로 받는다.
2. visited를 정해 한번 방문한 링크는 다시 방문하지 않는다.
3. depth를 정하여, 해당 queue가 depth 2이하인 친구들만 count한다.

## 사용된 알고리즘

그래프 이론: bfs(너비 우선 탐색)

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
        int links = Integer.parseInt((new StringTokenizer(br.readLine())).nextToken());
        List<List<Integer>> graph = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(i, new ArrayList<>());
        }

        for (int i = 0; i < links; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        int result = bfs(graph, visited);
        bw.write(String.valueOf(result));
        bw.close();
        br.close();
    }

    private static int bfs (List<List<Integer>> graph, boolean[] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0});
        visited[1] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curNode = cur[0];
            int curDepth = cur[1];

            List<Integer> curList = graph.get(curNode);
            curDepth++;
            for (int i : curList) {
                if (!visited[i]) {
                    visited[i] = true;
                    if (curDepth <= 2) {
                        count++;
                    }
                    queue.offer(new int[]{i, curDepth});
                }
            }
        }
        return count;
    }
}

```
