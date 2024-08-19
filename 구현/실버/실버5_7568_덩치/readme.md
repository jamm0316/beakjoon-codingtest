page link : [https://www.acmicpc.net/problem/7568](https://www.acmicpc.net/problem/7568)

---

# 💡 풀이전략

1. 아이디어
    1. and를 통해 키와 몸무게 모두 큰 사람의 갯수를 세고 거기에 1을 더 수를 rank에 append한다.

## 🎨 사용된 알고리즘

> [!tip]
> 구현(implement) <br>
> 완전탐색(Brute Force)

---

## pseudo code

```python
def main():
return

class BigGuy:
    def __init__(self):
    self.N = 0
    self.people = []
    
    def parse_data(self):
    self.N = 5
    self.people = [[wieght, tall]]
    
    def count_bigger(self):
        rank = []
        for person in self.people:
            count = 0
            for i in range(self.people):
                if person[0] < self.people[i][0] and person[1] < self.people[i][1]
                    count += 1
            rank.append(count + 1)

        return rank

    def print_rank(self):
        rank = self.count_bigger()
        print(' '.join(map(str, rank)))
```

---

# code

## Python

```python
import sys

def main():
    input = sys.stdin.read()
    bigman = BigMan()
    bigman.parse_data(input)
    bigman.print_rank()

class BigMan:
    def __init__(self):
        self.N = 0
        self.people = []

    def parse_data(self, input):
        data = input.split('\n')
        self.N = int(data[0])
        self.people = [(list(map(int, data[i].split()))) for i in range(1, self.N + 1)]
    
    def count_bigger(self):
        rank = []
        for person in self.people:
            count = 0
            for i in range(len(self.people)):
                if person[0] < self.people[i][0] and person[1] < self.people[i][1]:
                    count += 1
            rank.append(count + 1)
        
        return rank
        
    def print_rank(self):
        rank = self.count_bigger()
        print(' '.join(map(str, rank)))

if __name__ == '__main__':
    main()
```
