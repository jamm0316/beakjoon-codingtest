page link : [https://www.acmicpc.net/problem/1759](https://www.acmicpc.net/problem/1759)

---

# 💡 풀이전략
- 구하고자 하는 값
    - 암호의 모든 경우의 수
- 풀이 전략
    1. 알파벳을 오름차순 정렬 후 백트래킹으로 조합 생성
    2. 조건 검사 추가
        1. 문자열 길이가 L일 때 모음/자음 개수 체크 (최소 1개의 모음, 2개의 자음)
    3. 중복 방지
        1. start 인덱스를 i+1로 넘겨주기
        2. visited 배열 없이 사용가능

## 🎨 사용된 알고리즘
벡트래킹

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static int L, C;
    static char[] charArr;
    static StringBuilder sb = new StringBuilder();
    static final String vowels = "aeiou";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        charArr = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            charArr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(charArr); // 오름차순 정렬

        backtrack(0, 0, 0, new StringBuilder());

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void backtrack(int start, int vowelCnt, int consonantCnt, StringBuilder result) {
        if (result.length() == L) {
            // 최소 1개의 모음과 최소 2개의 자음 체크
            if (vowelCnt >= 1 && consonantCnt >= 2) {
                sb.append(result).append('\n');
            }
            return;
        }

        for (int i = start; i < C; i++) {
            char c = charArr[i];
            result.append(c);
            if (vowels.indexOf(c) >= 0) {
                // 모음
                backtrack(i + 1, vowelCnt + 1, consonantCnt, result);
            } else {
                // 자음
                backtrack(i + 1, vowelCnt, consonantCnt + 1, result);
            }
            result.deleteCharAt(result.length() - 1); // 백트래킹
        }
    }
}
```

# 🪄 해결한 오류

## 1. 자음 확인용 String.indexOf()

### 📌 개요

- String.indexOf()는 문자열 안에서 특정 문자(char) 또는 문자열(String)이 처음 등장하는 위치(인덱스)를 반환하는 메서드
- 찾는 대상이 없으면 -1 반환

### 📌 기본형

```java
int index = str.indexOf(int ch);
int index = str.indexOf(String str);
int index = str.indexOf(int ch, int fromIndex);
int index = str.indexOf(String str, int fromIndex);
```

| 매개변수 | 설명 |
| --- | --- |
| ch | 찾을 문자(ASCII 값 또는 char 가능) |
| str | 찾을 문자열 |
| fromIndex | 검색을 시작할 위치(인덱스) |
| 반환값 | 찾은 문자/문자열의 첫 번째 인덱스(없으면 -1) |

### 📌 API 내부 로직

- 문자열의 0번 인덱스 부터 한 글자씩 순회하며 찾는다.
- 찾는 대상(ch 또는 str)이 일치하면 해당 인덱스를 반환한다.
- 끝까지 못 찾으면 -1 반환

**간단한 내부 알고리즘**

```java
for (int i = fromIndex; i < this.length(); i++) {
    if (this.charAt(i) == ch) {
        return i;
    }   
}
return -1;
```

### 📌 사용법

```java
String text = "banana"

//문자 찾기
System.out.println(text.indexOf('a'));  // 1
System.out.println(text.indexOf('x'));  // -1

System.out.println(text.indexOf('na'));  // 2

System.out.println(text.indexOf('a', 2));  // 3
```

## 2. StringBuilder의 deleteCharAt()

### 📌 개요

- StringBuilder.deleteCharAt()는 지정한 위치의 문자를 삭제하는 메서드
- 삭제 후 나머지 문자들이 한 칸 씩 앞으로 이동하여 공백 없이 붙음
- StringBuilder, StringBuffer에만 존재하고, String에는 없음

### 📌 기본형

```java
StringBuilder sb = new StringBuilder("abcde");
sb.deleteCharAt(int index);
```

| 매개변수 | 설명 |
| --- | --- |
| index | 삭제할 문자 위치(0부터 시작하는 인덱스) |
| 반환값 | 현재 StringBuilder 객체 반환 |

### 📌 API 내부 로직

- 지정한 인덱스 이후 모든 문자를 한 칸씩 앞으로 복사함으로써 삭제 효과를 만듦
- 마지막 문자 이후 비워둠

**간단한 내부 알고리즘**

```java
for (int i = index; i < count - 1; i++) {
    value[i] = value[i + 1];  //뒤의 문자를 앞으로 당김
}
count--;  //총 문자 개수 감소
```

### 📌 사용법

```java
StringBuilder sb = new StringBuilder("hello");

sb.deleteCharAt(1);  //'e'삭제
System.out.println(sb);  //"hllo"

sb.deleteCharAt(3);  //'o' 삭제
System.out.println(sb);  //"hll"
```
