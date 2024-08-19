page link : [https://www.acmicpc.net/problem/1158](https://www.acmicpc.net/problem/1158)

---

# 💡 풀이전략


1. 초기화
    1. self.N: 배열 크기
    2. self.K: 배열 순환 간격
    3. self.A: 배열
2. 배열 순환
    1. 아이디어
        
        `index = (기준 + 떨어진거리) % 전체 배열의 수`
        
        - 전체 배열에서 K - 1 번 째 떨어진 사람이 빠진다.
        - 전체 배열이 끝나면 다시 처음부터 순환하기 떄문에 모듈러 사용
    
    ```java
    result = []
    index = 0  //초기 인덱스 0
    while self.A:  //self.A가 없어질 때 까지
        index = (index + self.K - 1) % len(self.A)
        result.append(self.A.pop(index))
    print("<" + ", ".join(map(str, result)) + ">")
    ```
    
## 🎨 사용된 알고리즘
> [!tip]
> 🎨 algoritm
> 구현(implement)

---

# code

## Python

```python
import sys

def main():
    input = sys.stdin.read()
    josephus = Josephus()
    josephus.parse_data(input)
    josephus.itinerate()

class Josephus:
    def __init__(self):
        self.N = 0
        self.K = 0
    
    def parse_data(self, input):
        data = list(map(int, input.split()))
        self.N = data[0]
        self.K = data[1]
        self.A = list(range(1, self.N + 1))

    def itinerate(self):
        index = 0
        que = []
        while self.A:
            index = (index + self.K - 1) % len(self.A)
            que.append(self.A.pop(index))
        print("<" + ", ".join(map(str, que)) + ">")

if __name__ == '__main__':
    main()
```

## 해결한 오류

### 1. pop을 사용해 배열이 없어질 때까지 일정 간격을 가지고 순회

📃 **문제점 1. 인덱스가 계속해서 바뀐다.**

- pop을 사용하면 배열의 인덱스가 틀어지므로, for문을 사용할 수 없다.
- 기존 배열의 인덱스를 기준점으로 잡으면 인덱스가 계속해서 바뀌므로 변하지 않는 인덱스 기준이 필요하다.

---

따라서, 배열 안에서 기준점을 잡고, 그 뒤로 몇번째 떨어진 인덱스인지 계산

`index = 기준 + 떨어진 거리`

`index = index + self.K - 1`



📃 **문제점 2. 배열 길이가 계속해서 바뀐다.**

- pop을 사용하면 배열의 길이가 계속해서 바뀌므로 배열을 순환할 수 없다.

---

따라서, 모듈러를 통해 인덱스가 증가해도 배열안을 계속해서 순환할 수 있게 함.

`index = (기준 + 떨어진 거리) % (현재 배열의 길이)`

`index = (index + self.K - 1) % len(self.A)`

[< back](https://www.notion.so/25239624ade64d8c86a9398a8d33a409?pvs=21)
