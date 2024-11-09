page link : [https://www.acmicpc.net/problem/1194](https://www.acmicpc.net/problem/1194)

---

# 💡 풀이전략

**BFS 사용 이유**
- 최단 경로 문제에 적합
- 이동가능한 최소 횟수를 찾기 위해 사용

**비트마스킹 사용 이유**
- 열쇠를 비트마스킹으로 관리하여, 각 열쇠가 있는지 없는지 상태를 bit 형태로 나타냄.
- a 열쇠를 얻었다면 해당 비트를 1로 설정하여 해당 키로 잠긴문 통과

**탐색 전략**
- 각 위치와 열쇠 상태를 큐에 저장하고, 이동하면서 획득한 열쇠와 현재 위치를 기반으로 탐색
- 만약 출구(1)에 도달하면 이동 횟수 출력하고 종료
</aside>

## 🎨 사용된 알고리즘

> [tip]
> BFS(Breadth-Frist Search): 너비 우선탐색
> Bitmarsking

---

## PsuedoCode

```
1. 현재 포지션이 중요하기 때문에 현재 포지션을 innerclass로 구현
2. 입력값 받기(BufferReader, StringTokenizer)
3. 입력값 초기화
4. bfs 구현
    - maze 경게 조건, 방문 여부, key획득, 사용 조건, visited조건 구현
```

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static class Position {
        int x;
        int y;
        int keys;
        
        public Position(int x, int y, int keys) {
            this.x = x;
            this.y = y;
            this.keys = keys;
        }
    }

    static int N;
    static int M;
    static char[][] maze;
    static boolean[][][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new char[N][M];
        visited = new boolean[N][M][(1 << 6)];
        
        Position start = null;
        
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = line.charAt(j);
                if (maze[i][j] == '0') {
                    start = new Position(i, j, 0);
                    maze[i][j] = '.';
                }
            }
        }
        
        System.out.println(bfs(start));
    }
    
    static int bfs(Position start) {
        Queue<Position> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.x][start.y][0] = true;
        int move = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Position cur = queue.poll();
                
                // 출구에 도달한 경우 이동 횟수 반환
                if (maze[cur.x][cur.y] == '1') {
                    return move;
                }

                // 4방향 탐색
                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];
                    int keys = cur.keys;
                    
                    // 경계 조건 및 방문 여부 확인
                    if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny][keys]) {
                        char cell = maze[nx][ny];
                        
                        if (cell != '#') {  // 벽이 아닌 경우
                            // 열쇠 획득
                            if ('a' <= cell && cell <= 'f') {
                                keys |= (1 << (cell - 'a'));
                            }
                            
                            // 문 통과 조건 확인
                            if (cell < 'A' || cell > 'F' || (keys & (1 << (cell - 'A'))) != 0) {
                                // 모든 조건을 만족한 경우 큐에 추가하고 방문 표시
                                visited[nx][ny][keys] = true;
                                queue.offer(new Position(nx, ny, keys));
                            }
                        }
                    }
                }
            }
            move++;
        }
        return -1;  // 출구에 도달할 수 없는 경우
    }
}
```

## 해결한 오류

### 1. StringTokenizer를 사용한 이유

**StringTokenizer 도입시기**

- 구분자가 단순하고, 정규표현식 사용 필요 없을 때
- 문자열을 매번 나누는 것보다 필요할 때 마다 토큰을 하나씩 꺼내는 방식이 더욱 효율적일 때
- 코드가 간결해지고 이해하기 쉬운 경우(작은 데이터 셋에서)

**StringTokenizer 와 String.split(” “)성능 비교**

| 구분 | StringTokenizer | String.split() |
| --- | --- | --- |
| 메모리 효율성 | 높음(배열생성 없음) | 낮음(배열 생성) |
| 속도 | 빠름(단순 토큰화) | 느림(정규 표현식) |
| 정규 표현식 지원 | 지원하지 않음 | 지원 |
| 구분자 반환 설정 | 가능(구분자도 토큰에 반환) | 불가능 |
| 코드 간결성 | 간단한 구분자 토큰화에 유리 | 복잡한 구분자, 정규식에 유리 |

### 2. `visited[N][M][keys]`를 구현한 이유

1. **BFS로 최단경로를 구하기 위해**
    - 한번 방문한 위치는 기억해 두었다가 다시 방문하는 비용을 줄이기 위해
2. **같은 위치라도 다른 열쇠를 가지고 있다면 다른 상태**
    - 그러나 일반적인 2차원배열의 위치값 뿐만아닌, 열쇠상태를 고려하여 어떤 위치를 a키를 기지고 방문한 상태와 b키를 가지고 방문한 상태를 다른 상태로 간주해야하기 때문

### 3. Queue와 LinkedList 사용이유

**Queue vs Deque**

| 특징 | Queue | Deque |
| --- | --- | --- |
| 자료구조 | FIFO(First-In-First-Out) | 양방향 큐(Double-Ended Queue), FIFO와 LIFO 모두 가능 |
| 주요기능 | 요소를 한쪽(뒤)에서 추가하고 앞에서 제거 | 양쪽(앞과 뒤)뒤에서 요소 추가 및 제거 가능 |
| 구현 클래스 | LinkedLIst, PrioriryQueue, ArrayBlockingQueue | LinkedList, ArrayDeque |
| 스택동작 | 지원하지 않음 | push(E e), pop() |

위에 따라 BFS구현은 FIFO 기능을 사용할 수 있는 Queue만으로 충분하기 때문에 Queue사용.

LinkedList는 Queue를 구현한 구현체이므로 LinkedList사용.

### 4. `offer()`, `poll()`사용 이유

- `add()`
    - queue가 가득 찬 경우 `IllegalStateException`발생 시킴.
    따라서, `offer()`를 통해 코드 안전성 유지.
- `remove()`
    - queue가 비어있을 때 호출하면 `NoSuchElementException` 발생시킴.
    따라서 `poll()`을 통해 코드 안전성 유지
- `offer()` → 성공시 `true`, 실패시 `false`
- `poll()` → 실패시 `null`

### 5. char 자료구조 이용해 문자를 유니코드로 변환

1. `char cell` 을  이용하여 다음 요소를 char형태로 저장.
2. 이를 a~f, A~F와 비교하여 문자형태로 비굫여

### 6. `visited[][][] = true;` 조건

- 문을 만나지 않았을 때
- 문을 만났다면 해당 문 열쇠를 가지고 있을 때
    
    → 열쇠가 없으면 일단 열쇠부터 찾아야하므로
    

### 7. 연산자 우선순위

- 올바른 표현
    
    ```java
    if (cell < 'A' || cell > 'F' || **(keys & (1 << (cell - 'A'))) != 0**) {...}
    ```
    
    - `(keys & 1 << (cell - ‘A’))) != 0`
        
        → 위 경우 연산자 우선순위에 따라, 의도한 대로 비교 진행
         `(keys & ((1 << (cell - ‘A’))) != 0)` 
        
- 틀린 표현
    
    ```java
    if (cell < 'A' || cell > 'F' || **keys & (1 << (cell - 'A')) != 0**) {...}
    ```
    
    - `keys & (1 << (cell - ‘A’)) != 0`
        
        → 위 경우 연산자 우선순위에 따라, 의도와 다르게 비교 진행
        `keys & ((1 << (cell - ‘A’)) != 0)` 
        
    - 에러 반환: &연산자의 피연산자 타입이 맞지 않다.
        
        ```java
        error: bad operand types for binary operator '&'
        ```
        
        → (int) & (boolean)을 bit연산자로 비교하려 했기 때문.
