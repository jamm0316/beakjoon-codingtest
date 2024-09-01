page link : [https://www.acmicpc.net/problem/1697](https://www.acmicpc.net/problem/1697)

# 💡 풀이전략

- BFS를 통해 탐색
    - visited = [flase] * max_position을 이용해 중복 방문 방비
    - deque를 통해 FIFO으로 풀이

## 사용된 알고리즘

> [!tip]
> BFS(Breadth-First Search): 너비 우선 탐색

# code

## Python

```python
import sys

def main():
    input = sys.stdin.read()
    find_sibling = FindSibling()
    find_sibling.find(input)

class FindSibling:
    def __init__(self):
        self.N = 0
        self.K = 0

    def parse_data(self, input):
        data = input.split()
        self.N = int(data[0])
        self.K = int(data[1])

    def find(self, input):
        from collections import deque
        self.parse_data(input)
        # 탐색 범위 설정
        max_position = 100000
        visited = [False] * (max_position + 1)

        # 초기 값 설정
        queue = deque([(self.N, 0)])  # (현재위치, 이동횟수)
        visited[self.N] = True

        while queue:
            current_position, current_time = queue.popleft()
    
            # 동생을 찾았을 때            
            if current_position == self.K:
                print(current_time)
                return

            # 못찾았다면
            method = (current_position - 1, current_position + 1, 2 * current_position)
            for next_position in method:
                if 0 <= next_position <= max_position and not visited[next_position]:
                    visited[next_position] = True
                    queue.append((next_position, current_time + 1))

if __name__ == '__main__':
    main()

```
