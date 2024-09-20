page link : [https://www.acmicpc.net/problem/2309](https://www.acmicpc.net/problem/2309)

---

# 💡 풀이전략

- 전체 키 합 구하기
- 두 난쟁이를 제외하기: 두 난쟁이를 제외했을 때 일곱명의 키가 100이 되는 조합 찾기
- 출력: 7 난쟁이 키 오름차순 출력
</aside>

## 🎨 사용된 알고리즘

> [!tip]
> Brute-Force: 완전 탐색

---

# code

## Python

```python
import sys

input_data = sys.stdin.read()
dwarfs_height = list(map(int, input_data.splitlines()))

def find_origin_dwarfs(dwarfs_height):
    SUM_DWARFS_HEIGHT = 100
    total_sum = sum(dwarfs_height)
    number_of_dwarfs = len(dwarfs_height)

    found = False
    for i in range(number_of_dwarfs):
        for j in range(i + 1, number_of_dwarfs):
            if total_sum - dwarfs_height[i] - dwarfs_height[j] == SUM_DWARFS_HEIGHT:
                dwarfs_height = [dwarfs_height[x] for x in range(number_of_dwarfs) if x is not i and x is not j]
                dwarfs_height.sort()
                for height in dwarfs_height:
                    print(height)
                found = True
                break
        if found:
            break

find_origin_dwarfs(dwarfs_height)
```
