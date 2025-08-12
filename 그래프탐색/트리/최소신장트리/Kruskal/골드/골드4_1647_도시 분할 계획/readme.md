page link : [https://www.acmicpc.net/problem/1647](https://www.acmicpc.net/problem/1647)

---

# 💡 풀이전략
- 구하고자 하는 값
    - 2마을로 나눌 때 최소 도로 유지비용
- 핵심 아이디어
    - 그래프를 두 개의 마을로 분할하되, 각 마을 내부 연결은 유지하면서 유지비 합이 최소
    - MST(최소 신장 트리)에서 가장 비싼 간선 하나를 제거하면, 최적의 두 컴포넌트가 된다.
        
        ⇒ 정답 = MST 총비용 - MST에서 선택된 간선 중 최대값
        
- 풀이전략
    1. MST를 만든다 (간선 오름차순 + 사이클 방지)
    2. 선택된 간선들의 총합과 그 중 최댓값을 기록
    3. 정답 = 총합 - 최댓값(가장 비싼 간선을 끊어 두 마을로 분리)
        - MST 알고리즘 Kruskal + Union-Find 구현

## 🎨 사용된 알고리즘

Kruskal MST, Union-Find, Counting Sort

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class P1647 {
    static class Edge {
        int start, end, cost;

        Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    static int N, M;
    static Edge[] edges;
    static int[] parent, size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new Edge[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(start, end, cost);
        }

        Arrays.sort(edges, Comparator.comparing(e -> e.cost));

        parent = new int[N + 1];
        size = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        long sum = 0;
        int maxEdge = 0;
        int picked = 0;

        for (Edge e : edges) {
            if (union(e.start, e.end)) {
                sum += e.cost;
                if (e.cost > maxEdge) maxEdge = e.cost;
                if (++picked == N -1) break;
            }
        }

        System.out.println(sum - maxEdge);
    }

    static boolean union(int start, int end) {
        start = find(start);
        end = find(end);

        if (start == end) return false;
        if (size[start] < size[end]) {
            int t = start;
            start = end;
            end = t;
        }
        parent[end] = start;
        size[start] += size[end];
        return true;
    }

    static int find(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}
```

# 🪄 해결한 오류

## 1. 최소 신장 트리(MST, Minimum Spanning Tree)

- 정의
    
    가중치가 있는 무방향 연결 그래프에서, 모든 정점을 사이클 없이 연결하는 부분 그래프 중 간선 가중치의 합이 최소가 되는 트리
    
- 특징
    - 정점 수 = N이면, MST 간선 수 = N -1
    - 사이클이 존재하지 않음
    - 모든 정점이 하나의 연결 컴포넌트 안에 포함됨
    - 최소 비용으로 전체 네트워크를 연결할 수 있는 구조 제공

## 2. MST를 구현하는 대표 알고리즘

### 1. 크루스칼(Kruskal) 알고리즘

- 개념 : “가장 저렴한 간선부터 차례로 연결하되, 사이클이 생기지 않게한다.”
- 절차
    1. 모든 간선을 비용 오름차순으로 정렬
    2. 간선 하나씩 선택하면서, 서로소 집합(Union-Find)을 이용해 두 정점이 이미 연결되어 있는지 확인
    3. 연결되지 않았다면 간선 채택(사이클 방지)
    4. 간선이 N-1개 선택되면 종료
- 시간 복잡도 O(Mlog M)
- 장점: 희소 그래프(sparse graph)에 유리

### 2. 프림(Prim) 알고리즘

- 개념: “하나의 정점에서 시작해, 가장 싼 간선을 하나씩 선택해 트리를 확장”
- 절차
    1. 임의의 시작 정점 선택
    2. 현재 연결된 정점에서 나가는 가장 싼 간선 선택
    3. 해당 간선의 다른 끝 정점을 트리에 포함
    4. 모든 정점이 포함될 때까지 반복
- 구현방식
    - 인접 리스트 + 우선순위 큐 → O(M log N)
    - 인접행렬 → O(N^2)
- 장점: 밀집 그래프(dense graph)에 유리, 간선 정렬 필요 없음.

## 3. MST로 해결하려는 문제

목적

- 모든 정점을 최소비용으로 연결하되, 사이클 없이 연결 상태를 유지

활용예시

- 전력망 건설 시 최소 비용으로 모든 도시 건설
- 통신 네트워크(광케이블, 랜선) 최소 비용 설치
- 도로·철도·수도관 최소 유지비 설계

대표 문제 형태

- 그래프 전체를 연결하는 최소 비용 구하기
- MST에서 간선 일부를 제거해 네트워크 분할하기

[▲ top](https://www.notion.so/4_1647_-24ccda6b86ff80c4b8d0c8de5272750c?pvs=21)

[< back](https://www.notion.so/25239624ade64d8c86a9398a8d33a409?pvs=21)
