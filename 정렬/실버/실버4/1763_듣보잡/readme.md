page link : [https://www.acmicpc.net/problem/1764](https://www.acmicpc.net/problem/1764)

---

# 풀이전략

1. 듣도못한 이름을 Set형태로 받는다.
2. 보도못한 이름을 듣도못한이름과 바로 비교하여 동일한 이름에 대해서 List형태로 받는다.
3. List를 오름차순으로 소팅 후 sb에 담아 반환한다.

## 사용된 알고리즘

해시셋

---

# code

## Java

```java
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        Set<String> unheard = new HashSet<>();
        List<String> unseenUnHeard = new ArrayList<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            unheard.add(br.readLine());
        }

        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            if (unheard.contains(name)) {
                unseenUnHeard.add(name);
            }
        }
        unseenUnHeard.sort(Comparator.naturalOrder());

        sb.append(unseenUnHeard.size() + "\n");
        for (String name : unseenUnHeard) {
            sb.append(name + "\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
```

## 해결한 오류

### 1. hashSet으로 바로 검증하자.

`hashMap`을 통해 `if`문을 2번 사용하는 것 보다 `hashSet`의 `contains` 메서드를 이용하여 바로 비교 검증하자.

**기존코드**

- 입력과 비교를 나눔

```java
for (int i = 0; i < M; i++) {
    String name = br.readLine();
    haveNotListen.put(name, name);
}

if (haveNotSeen.size() < haveNotListen.size()) {
    for (String name : haveNotSeen.keySet()) {
        if (!haveNotListen.getOrDefault(name, "-1").equals("-1")) {
            list.add(name);
        }
    }
} else {
    for (String name : haveNotListen.keySet()) {
        if (!haveNotSeen.getOrDefault(name, "-1").equals("-1")) {
            list.add(name);
        }
    }
}
```

**수정된 코드**

- 입력과 비교를 동시에 처리

```java
for (int i = 0; i < M; i++) {
    String name = br.readLine();
    if (unheard.contains(name)) {
        unseenUnHeard.add(name);
    }
}
```

---

- 기존코드
    
    ```java
    package class3;
    
    import java.io.*;
    import java.util.*;
    
    public class P1764 {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            Map<String, String> haveNotSeen = new HashMap<>();
            Map<String, String> haveNotListen = new HashMap<>();
            List<String> list = new ArrayList<>();
    
            for (int i = 0; i < N; i++) {
                String name = br.readLine();
                haveNotSeen.put(name, name);
            }
    
            for (int i = 0; i < M; i++) {
                String name = br.readLine();
                haveNotListen.put(name, name);
            }
    
            if (haveNotSeen.size() < haveNotListen.size()) {
                for (String name : haveNotSeen.keySet()) {
                    if (!haveNotListen.getOrDefault(name, "-1").equals("-1")) {
                        list.add(name);
                    }
                }
            } else {
                for (String name : haveNotListen.keySet()) {
                    if (!haveNotSeen.getOrDefault(name, "-1").equals("-1")) {
                        list.add(name);
                    }
                }
            }
            list.sort(Comparator.naturalOrder());
    
            bw.write(String.valueOf(list.size()));
            bw.newLine();
            list.stream().forEach(i -> {
                try {
                    bw.write(i);
                    bw.newLine();
                } catch (IOException e) {
                }
            });
    
            br.close();
            bw.close();
        }
    }
    
    ```
