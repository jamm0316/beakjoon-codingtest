
page link : [https://www.acmicpc.net/problem/11725](https://www.acmicpc.net/problem/11725)

---

# 💡 풀이전략

1. 서로 연결된 링크 받기 2차원 배열 입력값 받기
    1. 각 숫자를 받을 때 언급이 된 수를 저장
2. BFS 이용
    1. 최초 루트를 1로 지정
    2. 1에 대한 자식 노드들 순회
        1. `int current = queue.poll();` 로 지정
        2. 순회하며 각 노드들의 부모 노드를 current로 저장
        3. 방문한 숫자는 `visited = true;` 처리
3. 각 숫자를 2부터 순회
    1. 각 숫자의 부모 출력

## 🎨 사용된 알고리즘

> [!tip]
> BFS(너비 우선 탐색)

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<Integer>[] graph = new ArrayList[N + 1];
        int[] parents = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : graph[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    parents[neighbor] = current;
                    queue.add(neighbor);
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            sb.append(parents[i]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```
