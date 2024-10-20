page link : [https://www.acmicpc.net/problem/1094](https://www.acmicpc.net/problem/1094)

---

# 💡 풀이전략

2^6 = 64
- 막대의 갯수는 이진 수의 각 자릿수에 1이 있는 갯수.
- x를 변수로 받는다.
- `x > 0` 동안 반복, `x = x & x-1` 를 통해 각 2진수의 1을 제거.
- 위 제거가 실행되면 count++ 실행.
- count 갯수 반환

## 🎨 사용된 알고리즘


> [!tip]
> Bitmask: 비트마스크

---

# code

## Java

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        int count = 0;
        while (x > 0) {
            x = x & (x - 1);
            count++;
        }
        System.out.println(count);
    }
}
```
