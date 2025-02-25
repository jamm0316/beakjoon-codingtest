page link : [https://www.acmicpc.net/problem/2108](https://www.acmicpc.net/problem/2108)

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    public static void main(String[] args) throws IOException {
        /**
         * 산술평균: sum(N) / N
         * 중앙값: N 중 중앙값
         * 최빈값: N 중 많이 나타나는 값
         * 범위: N 중 최대 - 최소
        */

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int[] sequence = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sequence[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sequence);

        writeBuffer(findAverage(sequence));
        writeBuffer(findMid(sequence));
        writeBuffer(findPopular(sequence));
        writeBuffer(findRange(sequence));

        bw.close();
        br.close();

    }
    private static int findRange(int[] sequence) {
        return sequence[sequence.length - 1] - sequence[0];
    }
    private static int findPopular(int[] sequence) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : sequence) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> keySet = new ArrayList<>(map.keySet());
        keySet.sort((a, b) -> {
            int compareNum = map.get(b) - map.get(a);
            if (compareNum == 0) {
                return a - b;
            }
            return compareNum;
        });

        if (keySet.size() > 1 && map.get(keySet.get(0)).equals(map.get(keySet.get(1)))) {
            return keySet.get(1);
        } else {
            return keySet.get(0);
        }
    }
    private static int findMid(int[] sequence) {
        int mid = N / 2;
        return sequence[mid];
    }
    private static int findAverage(int[] sequence) {
        int sum = Arrays.stream(sequence).sum();
        double avg = (double) sum / N;
        return (int) Math.round(avg);

    }
    private static void writeBuffer(int x) throws IOException {
        bw.write(String.valueOf(x));
        bw.newLine();
    }
}

```

## 해결한 오류

### 1. 최빈값 계산 오류

- **map 초기화 낭비**
    - `map.put(i, map.getOrDefault(i, 0) + 1);` 사용 시, 해당 키값이 없으면 자동으로 0이 셋팅 되고 +1을 하고 있다면 그 값에 +1을 한다.
    - 따라서 `keycontains`로 키값을 확인하는 작업이 불필요하다.
    
    **기존코드**
    
    ```java
    for (int i = 0; i < N; i++) {
        if (!map.containsKey(sequence[i])) {
            map.put(sequence[i], map.getOrDefault(sequence[i], 0) + 1);
        } else {
            map.put(sequence[i], map.get(sequence[i]) + 1);
        }
    }
    ```
    
    **수정된 코드**
    
    ```java
    for (int num : sequence) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }
    ```
    
- **keySet sort 방법 오류**
    - 내림차순으로 정렬하려 하는 익명함수 구현 오류
    
    **기존코드**
    
    ```java
    List<Integer> keySet = new ArrayList<>(map.keySet());
    keySet.sort((a, b) -> {
        int i = map.get(a);
        int j = map.get(b);
        return i - j;
    });
    ```
    
    **수정된 코드**
    
    ```java
    List<Integer> keySet = new ArrayList<>(map.keySet());
    keySet.sort((a, b) -> {
        int compareNum = map.get(b) - map.get(a);
        if (compareNum == 0) {
            return a - b;
        }
        return compareNum;
    });
    ```
    
- **최빈값이 무조건 두번째가 아니라 중복값이 여러개일 때 2번째로 작은값 출력**
    
    **기존코드**
    
    ```java
    if (N > 1) {
        return keySet.get(1);
    } else {
        return keySet.get(0);
    }
    ```
    
    **수정된 코드**
    
    ```java
    if (keySet.size() > 1 && map.get(keySet.get(0)).equals(map.get(keySet.get(1)))) {
        return keySet.get(1);
    } else {
        return keySet.get(0);
    }
    ```
    

---

- 기존코드
    
    ```java
    package class2;
    
    import java.io.*;
    import java.util.*;
    
    public class P2108 {
        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        static int N;
        public static void main(String[] args) throws IOException {
            /**
             * 산술평균: sum(N) / N
             * 중앙값: N 중 중앙값
             * 최빈값: N 중 많이 나타나는 값
             * 범위: N 중 최대 - 최소
            */
    
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int[] sequence = new int[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                sequence[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(sequence);
    
            writeBuffer(findAverage(sequence));
            writeBuffer(findMid(sequence));
            writeBuffer(findPopular(sequence));
            writeBuffer(findRange(sequence));
    
            bw.close();
            br.close();
    
        }
        private static int findRange(int[] sequence) {
            return sequence[N - 1] - sequence[0];
        }
        private static int findPopular(int[] sequence) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < N; i++) {
                if (!map.containsKey(sequence[i])) {
                    map.put(sequence[i], map.getOrDefault(sequence[i], 0) + 1);
                } else {
                    map.put(sequence[i], map.get(sequence[i]) + 1);
                }
            }
            List<Integer> keySet = new ArrayList<>(map.keySet());
            keySet.sort((a, b) -> {
                int i = map.get(a);
                int j = map.get(b);
                return i - j;
            });
    
            if (N > 1) {
                return keySet.get(1);
            } else {
                return keySet.get(0);
            }
        }
        private static int findMid(int[] sequence) {
            int mid = N / 2;
            return sequence[mid];
        }
        private static int findAverage(int[] sequence) {
            int sum = Arrays.stream(sequence).sum();
            double avg = (double) sum / N;
            return (int) Math.round(avg);
    
        }
        private static void writeBuffer(int x) throws IOException {
            bw.write(String.valueOf(x));
            bw.newLine();
        }
    }
    
    ```
