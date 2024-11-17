page link : [https://www.acmicpc.net/problem/1026](https://www.acmicpc.net/problem/1026)

---

# 💡 풀이전략

- B배열의 가장 큰 수와 A배열의 가장 작은 의 곱들의 합을 구한다.

---

1. A를 작은 수 부터 오름차순으로 정렬한다.
2. B의 인덱스를 저장한 후 B배열의 내림차순으로 인덱스를 재정렬.
    1. B를 재정렬하는 것이 아닌 인덱스를 재정렬
3. N만큼 순회하면서 A[i] * B[index[i]]를 더한값을 반환한다.
4. 이ㄷ 때, index[i]는 실제 B배열의 크기 순으로 들어가 있으므로, 각각 작은 수와 큰 수의 순서대로 매칭됨.

## 🎨 사용된 알고리즘

> [!tip]
> Greedy: 매 단계에서 A의 가장 작은 값과 B의 가장 큰 값을 매칭하여 result의 값을 최소화하려는 것이 탐욕적 선택.

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        Integer[] A = new Integer[N];
        Integer[] B = new Integer[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        Integer[] BIndex = new Integer[B.length];
        for (int i = 0; i < B.length; i++) {
            BIndex[i] = i;
        }
        Arrays.sort(BIndex, (a, b) -> B[b] - B[a]);

        int result = 0;
        for (int i = 0; i < N; i++) {
            result += A[i] * B[BIndex[i]];
        }

        System.out.println(result);
    }
}
```

## 해결한 오류

### 1. Wrapper Class의 람다식 sort방법

1. java에서의 자료구조
    1. 기본형 자료구조, Object형태의 자료구조의 특징
        
        
        | 특성 | 기본형 (Primitive) | 객체형 (Object) |
        | --- | --- | --- |
        | **메모리 위치** | 스택(Stack) | 힙(Heap) |
        | **속도** | 빠름 | 상대적으로 느림 |
        | **메모리 사용량** | 적음 | 더 많음 |
        | **기능** | 단순한 값 | 추가 메서드와 유틸리티 제공 |
        | **제네릭 지원** | 지원하지 않음 | 지원 가능 |
        | **사용 예** | 반복문, 연산 집중 작업 | 데이터 구조, 제네릭 사용 |
2. 정렬방법
    1. java의 `Arrays.sort`는 2가지 방식으로 작동함.
        1. 기본형 자료구조: `ComparableTimSort.sort()` 방식.
            <img width="685" alt="image1" src="https://github.com/user-attachments/assets/bf077858-407e-4c49-b76e-1547db70d9e3">

        2. Object형 자료구조: `TimSort.sort()`방식(`Comparator`이용)
            <img width="659" alt="image2" src="https://github.com/user-attachments/assets/1a539f8b-0526-42a3-839a-5f441e8e2ef7">
            
    2. 가장 큰 차이는 `Comparator<Generic>`인 `c`의 존재 여부를 따진다.
        1. 존재하면 `ComparableTimSort.sort()`함수를 호출하고, 존재하지 않으면 `LegacyMergeSort`를 사용할 것인지 `TimSort`를 사용할 것인지 정한다.
            - 여기서 `LegacyMergeSort`는 `java7` 이전 버전에서 사용되었으며 `TimSort`는 `java7` 버전 이후부터 사용되었다.
            - 둘의 가장큰 차이는 정렬방식이며 `LegacyMergeSort`는 병합정렬을 사용하며, `Timsort`는 병합정렬과 삽입정렬을 조합한 방식을 사용한다.
3. Comparator 사용여부에 따른 차이
    1. 따라서, `Comparator`를 사용하지 않으면 `ComparableTimsort.sort()`가 호출되어, 자연스러운 순서(오름차순)으로 정렬되고, `Compatator`를 사용하면, 사용자가 순서를 지정할 수 있다.
    2. `Comparator`의 경우 `interface`형태로 여러가지 메서드를 가지고 있지만 추상메서드는 1개 뿐이다.
        
        <img width="684" alt="image3" src="https://github.com/user-attachments/assets/9fdb3fe9-afdf-4aa8-9909-8dca3f66b16a">
        
        즉, `FunctionalInterface`(함수형 인터페이스)로 추상메서드를 1개만 갖는 인터페이스의 경우, 익명함수 선언이 가능하다.
        익명함수는 람다식으로 표현인 가능하다.
        
4. 따라서, 람다식을 통한 사용자 지정 sort를 이용하려면?
    1. 이러한 익명함수를 쓸 수 있는 `Comparator`의 경우 `GenericWildCard`를 이용해 `Generic`에서 타입의 범위를 지정하였다.
5. 결론
    1. sort의 2가지 방식 중 사용자 임의의 정렬방식을 사용하고 싶다면 `TimSort.sort()`방식을 사용해야한다.
    2. 이 경우 `Comparator<Generic>` 의 존재 여부를 판단한다.
    3. Comparator는 `FunctionalInterface` 로 추상함수 `int Compare(T o1, T o2)` 를 가지고 있다.
    4. 따라서, 이는 익명함수 중 람다식으로 구현이 가능하다.
    5. 즉, 사용자 임의의 정렬방식을 사용하고 싶다면 배열은 제네릭으로 선언되어야하고, `Compare`함수를 람다식으로 구현해야한다.
