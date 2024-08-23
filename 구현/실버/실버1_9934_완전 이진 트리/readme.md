page link : [https://www.acmicpc.net/problem/9934](https://www.acmicpc.net/problem/9934)


# 💡 풀이전략

1. 트리 빌드하기
    1. 노드의 중간값 찾기
    2. level에 중간값 넣고 다음 level로 넘어가기
    3. 노드의 중간을 기준으로 왼편, 오른편에서 다시 중간값 찾기
    4. level에 중간값 넣고 다음 level로 넘어가기
2. 트리 프린트하기

## 🎨 사용된 알고리즘

> [!tip]
> 구현(implement)<br>
> 재귀(recursive)

---

# code

## Python

```python
import sys

def main():
    input = sys.stdin.read()
    tree = binaryTree()
    tree.parse_data(input)
    tree.print_tree()

class binaryTree:
    def __init__(self):
        self.K = 0
        self.nodes = []
    
    def parse_data(self, input):
        data = list(map(int, input.split()))
        self.K = data[0]
        self.nodes = data[1:]
        self.levels = [[] for _ in range(self.K)]

    def build_tree(self, levels, nodes, depth, start, end):
        if start > end:
            return
        mid = (start + end) // 2
        levels[depth].append(nodes[mid])
        self.build_tree(levels, nodes, depth + 1, start, mid - 1)
        self.build_tree(levels, nodes, depth + 1, mid +1, end)
    
    def print_tree(self):
        self.build_tree(self.levels, self.nodes, 0, 0, len(self.nodes) - 1)
        for level in self.levels:
            print(" ".join(map(str, level)))

if __name__ == '__main__':
    main()
```

## 해결한 오류

### 1. 재귀에서 return의 역할

1. **실행 흐름:**
- **첫 번째 재귀 호출**
(`build_tree(levels, nodes, depth + 1, start, mid - 1)`):
    1. `start`와 `end`의 값이 특정 범위로 설정되어 재귀 호출이 발생.
    2. 재귀 호출 중 `start > end` 조건이 참이 되는 순간, 해당 호출이 종료(`return`).
    3. 이 호출이 종료되면, 함수는 `None`을 반환하고, 스택에서 현재 함수 호출의 스택 프레임이 제거.
    4. 이후 함수는 이전 호출로 돌아가서 두 번째 재귀 호출을 진행.
- **두 번째 재귀 호출**
(`build_tree(levels, nodes, depth + 1, mid + 1, end)`):
    1. 첫 번째 재귀 호출이 끝나면, **바로 다음 줄에서 두 번째 재귀 호출이 실행**.
    2. 이 호출도 동일한 방식으로 진행되며, 필요 시 추가적으로 더 많은 재귀 호출이 발생.
- **요약:**
    - `start > end` 조건에서 `return`이 호출되면 현재 재귀 함수는 종료되고, 호출되었던 이전 상태로 돌아감.
    - 이전 상태에서는 다음 실행 흐름(여기서는 두 번째 재귀 호출)을 진행.
    - `return` 시 아무것도 반환하지 않으며, 단순히 현재 실행 상태를 종료시키고 이전 상태로 돌아가는 역할.
