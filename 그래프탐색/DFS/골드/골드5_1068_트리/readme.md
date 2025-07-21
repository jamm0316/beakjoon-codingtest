page link : [https://www.acmicpc.net/problem/1068](https://www.acmicpc.net/problem/1068)

---

# 💡 풀이전략
- 구하고자 하는 값
    - 리프 노드의 갯수
- 풀이전략
    1. 단방향트리를 받는다
    2. 노드를 지우는 것은 해당 노드를 탐색하지 않는 것으로 한다.
    3. parent = -1이면 해당 노드가 root다
    4. removeNode == root이면 즉시 0을 반환한다.
    5. 그렇지 않으면 dfs를 root부터 순회한다.
        - 순회 시, removeNode를 만나면 즉시 return한다.
        - 그렇지 않으면 해당 노드의 자식노드를 dfs로 탐색하며, 자식노드가 있을 시 isLeaf를 false로 하고, 없다면 true로 놓는다.
        - isLeaf가 true면 leafNode++를 한다.
    6. 최종 leafNode의 갯수를 반환한다.

## 🎨 사용된 알고리즘
DFS, 그래프 탐색

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> tree = new ArrayList<>();
    static int leafNode, root, removeNode;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
            } else {
                tree.get(parent).add(i);
            }
        }

        st = new StringTokenizer(br.readLine());
        removeNode = Integer.parseInt(st.nextToken());

        if (removeNode == root) {
            System.out.println(0);
        } else {
            dfs(root);
            System.out.println(leafNode);
        }
    }

    private static void dfs(int current) {
        if (current == removeNode) {
            return;
        }

        boolean isLeaf = true;
        for (int each : tree.get(current)) {
            if (each != removeNode) {
                dfs(each);
                isLeaf = false;
            }
        }

        if (isLeaf) leafNode++;
    }
}

```
