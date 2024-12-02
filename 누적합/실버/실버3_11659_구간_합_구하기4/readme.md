page link : [https://www.acmicpc.net/problem/11659](https://www.acmicpc.net/problem/11659)

# 💡 풀이전략

- 입력값: 수열과 쿼리
- 각 쿼리별 시작과 끝 위치가 주어지고, 입력값의 합을 구함

---

**step1. 시간복잡도 최적화**

- 브루트포스
    - N = 100,000 ,  M = 100,000 이 최대일 때, 브루트포스 방식은 각 쿼리에 대해 최대  O(N) 시간 소요.
    - 따라서 전체 시간 복잡도는 O(N^2) ⇒ O(10^10)
    - 보통 1초에 10억회(10^9) 연산 가능하므로, 브루트포스 불가
- prefixSum
    - 시간복잡도를 O(N)으로 줄일 수 있음.

---

**step.2 메모리와 시간을 좀 더 극한으로!**

- 입력값을 받으면서 누적합 배열 생성
- StringBuilder를 통해 출력 최적화
</aside>

## 🎨 사용된 알고리즘

> [!tip]
> prefixSum: 누적합

---

# code

## Java

- **step1. 시간복잡도 최적화**
    
    ```java
    package prefix;
    
    import java.util.*;
    import java.io.*;
    
    public class P11659 {
        static int sequenceNum;
        static int session;
        static int[] sequence;
        static int[][] queries;
    
        public static void main(String[] args) throws IOException {
            initData();
            int[] prefixSum = makePrefixSum(sequenceNum, sequence);
            calculatePrefixSum(prefixSum, queries);
        }
    
        private static void calculatePrefixSum(int[] prefixSum, int[][] queries) {
            for (int[] query : queries) {
                int start = query[0];
                int end = query[1];
    
                int eachSum = prefixSum[end] - prefixSum[start - 1];
                System.out.println(eachSum);
            }
        }
    
        private static int[] makePrefixSum(int sequenceNum, int[] sequence) {
            int[] prefixSum = new int[sequenceNum];
            for (int i = 1; i < sequenceNum; i++) {
                prefixSum[i] = prefixSum[i - 1] + sequence[i];
            }
            return prefixSum;
        }
    
        private static void initData() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            sequenceNum = Integer.parseInt(st.nextToken()) + 1;
            session = Integer.parseInt(st.nextToken());
            sequence = new int[sequenceNum];
            queries = new int[session][2];
    
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < sequenceNum; i++) {
                sequence[i] = Integer.parseInt(st.nextToken());
            }
    
            for (int i = 0; i < session; i++) {
                st = new StringTokenizer(br.readLine());
                queries[i][0] = Integer.parseInt(st.nextToken());
                queries[i][1] = Integer.parseInt(st.nextToken());
            }
        }
    }
    ```
    
- **step2. 메모리와 성능을 좀 더 극한으로**
    
    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] prefixSum = new int[N + 1];
            int[] sequence = new int[N + 1];
    
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N + 1; i++) {
                sequence[i] = Integer.parseInt(st.nextToken());
                prefixSum[i] = prefixSum[i - 1] + sequence[i];
            }
    
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
    
                result.append(prefixSum[end] - prefixSum[start - 1]).append("\n");
            }
            System.out.print(result);
        }
    }
    ```
    

## 해결한 오류

### 1. 시간초과

- 컴퓨터의 보통 1초당 컴퓨터의 쿼리 계산 가능 갯수는 10억회(1,000,000,000)
- 위 문제의 경우 브루트포스 사용 시, 1조회(1,000,000,000,000)
- 따라서, 시간제한인 1초에 만족하지 않는다.
- 누적합을 사용하면 O(N)으로 시간복잡도를 줄여 최적화 할 수 있다.

---

- 기존코드
    
    ```java
    import java.util.*;
    import java.io.*;
    
    public class Main {
        static int sequenceNum;
        static int session;
        static int[] sequence;
        static int[][] queries;
    
        public static void main (String[] args) throws IOException {
            parseData();
    
            for (int[] query : queries) {
                int start = query[0];
                int end = query[1];
    
                List<Integer> sumArray = new ArrayList<>();
                for (int i = start; i <= end; i++) {
                    sumArray.add(sequence[i]);
                }
    
                int eachSum = sumArray.stream()
                        .mapToInt(Integer::valueOf)
                        .sum();
    
                System.out.println(eachSum);
            }
    
            }
    
            private static void parseData() throws IOException {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                StringTokenizer st = new StringTokenizer(br.readLine());
    
                sequenceNum = Integer.parseInt(st.nextToken());
                session = Integer.parseInt(st.nextToken());
                sequence = new int[sequenceNum + 1];
                queries = new int[session][2];
    
                st = new StringTokenizer(br.readLine());
                for (int i = 1; i < sequenceNum + 1; i++) {
                    sequence[i] = Integer.parseInt(st.nextToken());
                }
    
                for (int i = 0; i < session; i++) {
                    st = new StringTokenizer(br.readLine());
                    queries[i][0] = Integer.parseInt(st.nextToken());
                    queries[i][1] = Integer.parseInt(st.nextToken());
            }
        }
    }
    ```
