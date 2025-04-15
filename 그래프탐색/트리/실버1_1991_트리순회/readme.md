page link : [https://www.acmicpc.net/problem/1991](https://www.acmicpc.net/problem/1991)

---

# 풀이전략
- 구하고자 하는 값
    - 전위, 중위, 후위 트리 순회

- 성공한 풀이전략
    1. 입력 파싱 및 트리 구성
        1. 노드가 A부터 Z까지 최대 26개이므로 고정 크기 배열로도 충분히 표현 가능
        2. 각 노드에서 좌우 자식 정보를 가지고 있으므로 딕셔너리 또는 배열을 써서 `left[node]`, `right[node]` 식으로 저장
        3. `.` = 자식 없음 → -1로 저장
    2. 순회 알고리즘 구현
        1. 전위: 루트 → 왼쪽 → 오른쪽
        2. 중위: 왼쪽 → 루트 → 오른쪽
        3. 후휘: 왼쪽 → 오른쪽 → 루트
        4. 재귀로 구현

## 사용된 알고리즘
재귀, 트리

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static int[] left = new int[26];
    static int[] right = new int[26];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = st.nextToken().charAt(0) - 'A';
            char leftChild = st.nextToken().charAt(0);
            char rightChild = st.nextToken().charAt(0);
            left[parent] = (leftChild == '.') ? -1 : leftChild - 'A';
            right[parent] = (rightChild == '.') ? -1 : rightChild - 'A';
        }

        preorder(0);
        sb.append('\n');
        inorder(0);
        sb.append('\n');
        postorder(0);
        System.out.println(sb);
    }

    static void preorder(int node) {
        if (node == -1) return;
        sb.append((char) (node + 'A'));
        preorder(left[node]);
        preorder(right[node]);
    }

    static void inorder(int node) {
        if (node == -1) return;
        inorder(left[node]);
        sb.append((char) (node + 'A'));
        inorder(right[node]);
    }

    static void postorder(int node) {
        if (node == -1) return;
        postorder(left[node]);
        postorder(right[node]);
        sb.append((char) (node + 'A'));
    }
}
```

## 해결한 오류

### 1. 전, 중, 후위 트리 순회 개념

| **순회 방식** | **순서** | **설명** |
| --- | --- | --- |
| **전위 순회** | 루트 → 왼쪽 → 오른쪽 | **루트 먼저 방문하고**, 자식들 재귀 탐색 |
| **중위 순회** | 왼쪽 → 루트 → 오른쪽 | **왼쪽 먼저**, 루트, 오른쪽 순으로 탐색 |
| **후위 순회** | 왼쪽 → 오른쪽 → 루트 | **자식 먼저 전부 탐색**하고 마지막에 루트 |

**트리구조**

```
        A
       / \
      B   C
     /   / \
    D   E   F
             \
              G
```

1. 전위 순회: 루트 방문 후 왼쪽 모든 자식, 오르쪽 모든 자식 탐색(node, left, right 순)
    
    ex) `A` → `B` → `C` → `D` → `E` → `F` → `G`
    
    - node: `A` → left: `B` → right: `C`
    - node: `B` → left: `D`
    - node: `C` → left: `E` → right: `F`
2. 중위 순회: 왼쪽 모든 자식 탐색 후, 루트, 오른쪽 모든 자식 탐색(left, node, right 순)
    
    ex) `D` → `B` → `A` → `E` → `C` → `F` → `G`
    
    - left: `D` → node: `B`
    - left: `B` → node: `A` → right: `.`
    - left: `E` → node: `C` → right: `.`
3. 후위 순회: 왼쪽 모든 자식 탐색 후, 오른쪽 모든 자식 탐색, 루트(left, right, node 순)
    
    ex) `D` → `B` → `E` → `G` → `F` → `C` → `A`
    
    - **A를 기준으로 보면**
        - Left: B
        - Right: C
        - Node: A
        
        → 후위 순회: **[B의 후위] → [C의 후위] → A**
        
    
    - **B를 기준으로 보면**
        - Left: D
        - Right: 없음
        - Node: B
        
        → 후위 순회: **D → B**
        
    - **C를 기준으로 보면**
        - Left: E
        - Right: F
        - Node: C
        
        → 후위 순회: **[E의 후위] → [F의 후위] → C**
        
    
    - **E는 자식 없음 → 그냥 E**
    
    - **F를 기준으로 보면**
        - Left: 없음
        - Right: G
        - Node: F
        
        → 후위 순회: **[G] → F**
        
    
    - **G도 자식 없음 → G**

### 2. 재귀 사용

트리는 계속 왼쪽, 오른쪽 자식으로 분기됨.

그래서 전통적인 for문처럼 인덱스 증가로 접근할수 없고, 자식마다 동일한 동작 반복

→ 따라서 재귀 채택

### 3. 그래프 처럼 2차원 배열이 아닌 `left[]`, `right[]` 배열을 쓴 이유

보통의 그래프를 풀이 할 떄

`List<List<Integer>> grpah = new ArrayList<>();`

와 같은 2차원 배열을 사용함.

그러나 이 문제는 “이진트리”로 자식이 최대 2개 뿐임.

따라서, 왼쪽 자식, 오른쪽 자식만 저장하면 충분하기 때문에 메모리도 아끼고, 구분하기 쉽도록 `left[]`, `right[]` 배열을 사용함.
