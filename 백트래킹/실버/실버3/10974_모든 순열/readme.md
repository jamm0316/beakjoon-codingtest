page link : [https://www.acmicpc.net/problem/10974](https://www.acmicpc.net/problem/10974)

---

# 💡 풀이전략

- 모든 순열을 추출하는 문제
    - 방법1. 백트래킹
    - 방법2. itertools permutations 이용
- 방법1. 백트래킹
    - 1 ~ N 까지 순회
        - visited를 활용하여, 각 세그먼트 마다 한번 방문한 숫자는 방문에서 제외하여 중복 방문 순열 제외

## 🎨 사용된 알고리즘

> [!tip]
> Backtrack: 백트래킹

---

# code

## Python(Backtrack)

```python
def backtrack(N, sequence, visited):
    if len(sequence) == N:
        print(' '.join(map(str, sequence)))
        return
    
    for i in range(N):
        if not visited[i]:
            visited[i] = True
            sequence.append(i + 1)
            backtrack(N, sequence, visited)

            sequence.pop()
            visited[i] = False

if __name__ == '__main__':
    N = int(input())
    visited = [False] * N
    backtrack(N, [], visited)
```

## Python(Permutation)

```python
from itertools import permutations

def main(N):
    numbers = permutations(N)
    for perm in numbers:
        print(' '.join(map(str, perm)))

if __name__ == '__main__':
    N = [i + 1 for i in range(int(input()))]
    main(N)
```

## Java(Backtrack)

```python
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        ArrayList<Integer> sequence = new ArrayList<Integer>();
        boolean[] visited = new boolean[N];

        backtrack(N, sequence, visited);
    }

    private static void backtrack(int N, ArrayList<Integer> sequence, boolean[] visited) {
        if (sequence.size() == N) {
            printSequence(sequence);
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                sequence.add(i + 1);
                visited[i] = true;
                backtrack(N, sequence, visited);

                sequence.remove(sequence.size() - 1);
                visited[i] = false;
            }
        }
    }
    
    private static void printSequence(ArrayList<Integer> sequence) {
        String result = sequence.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(" "));
        System.out.println(result);
    }
}
```

## 해결한 오류

### pop() → ArrayList.remove()

1. python: pop() → java: ArrayList.remove()
    
    **ArrayList.remove(int index or Object obj)**
    
    - 개요
        
        ArrayList의 특정 인덱스에 위치한 요소 제거. 제거된 요소는 반환됨.
        
        기본형 : `ArrayList(int index or Object obj)`
        
    - 매개변수
        
        index: 제거할 요소의 인텍스(0부터 시작하는 정수)
        
        obj: 리스트에서 제거하고자 하는 객체
        
    - 반환값
        - int index의 경우
            - 제거된 요소를 반환.
            - 만약 인덱스 범위를 벗어나면 IndexOutOfBoundsException 예외발생.
        - Object obj의 경우
            - 제거된 경우 true, 그렇지 않은 경우 false 반환.
            - 객체가 리스트에 없으면 변화 일어나지 않음.
    - 주의사항
        - int index의 경우
            - IndexOutOfBoundsException: 주어진 인덱스 배열의 크기보다 크거나 음수일 경우 발생
            - 인덱스를 통해 제거하면 그 인덱스 이후 모든 요소는 한 칸씩 앞으로 이동하게 됨.
        - Object obj의 경우
            - equals(): 객체 제거 시, 리스트의 각 요소와 주어진 객체의 equals() 메서드를 사용하여 비교.
            - 객체가 null일 경우, null을 가진 첫 번째 요소 제거.
            
            ```java
            ArrayList<String> list = new ArrayList<>(Arrays.asList("apple", "banana", "cherry"));
            System.out.println(list);  // [apple, banana, cherry]
            
            boolean isRemoved = list.remove("banana");  // "banana" 제거
            System.out.println(isRemoved);  // true
            System.out.println(list);  // [apple, cherry]
            
            isRemoved = list.remove("grape");  // 리스트에 없는 요소를 제거하려고 함
            System.out.println(isRemoved);  // false
            System.out.println(list);  // [apple, cherry]
            ```
            

### 2. print(’ ‘.join(map(str, sequence))) → Stream API

- Stream API 활용
    - 컬렉션(List, set 등)의 요소들을 스트림으로 변환하여 특정 형식으로 처리한 뒤, 문자열로 결합하는 기능.
    - 이 과정에서 map()을 사용하여 각 요소를 원하는 형식으로 변환
    - Collectors.joinin()을 사용하여 변환된 요소들을 특정 구분자로 연결.
1. **Stream(): 컬렉션을 스트림으로 변환**
    
    **개요**
    
    - `stream()` 메서드는 Collection 인터페이스(List, Set, etc.)의 메서드로, 컬렉션을 스트림으로 변환.
    
    **반환값**
    
    - 스트림(`Stream<T>` 객체) 반환.
    - 스트림은 파이프라인 방식으로 다양한 메서드를 연결하여 처리

1. **map(): 요소 반환**
    
    **개요**
    
    - `map()` 메서드는 스트림의 각 요소를 특정 방식으로 변환
    - 이때 람다 표현식 또는 메서드 참조를 통해 각 요소가 어떻게 변환될지 정의 가능
    
    **매개변수**
    
    - mapper: 스트림의 각 요소를 변환하는 함수(람다식 또는 메서드 참조). 이 함수는 각 요소를 다른 형태로 변환.
    
    **반환값**
    
    - 변환된 요소로 이루어진 새로운 스트림 반환
    
    **주의사항**
    
    - `map()`은 요소의 개수는 변경하지 않고, 각 요소를 변환하는데 사용.

1. **collect(Collectors.joinin()): 스트림의 요소를 문자열로 결합**
    
    **개요**
    
    - `collect(Collectors.joining())`는 스트림의 요소들을 문자열로 결합하는 기능.
    - 요소 사이 구분자를 넣을 수 있으며, 앞뒤에 추가할 접두사와 접미사 지정 가능.
    
    **매개변수**
    
    - 기본적으로 세가지 오버로드 버전 있음.
        - `Collectors.joining()`: 구분자 없이 요소 이어붙힘
        - `Collectors.joining(CharSequence delimiter)`: 각 요소 사이에 구분자르 삽입하여 연결
        - `Collectors.joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)`: 구분자 뿐만 아니라 접두사, 접미사 함께 지정
    
    **반환값**
    
    - 모든 요소가 결합된 하나의 문자열 반환
    
    **주의사항**
    
    - 스트림의 요소들이 문자열이어야 하므로, 스트림의 요소가 문자열이 아닐 경우 map()을 사용하여 먼저 문자열로 변환해야함.

---
