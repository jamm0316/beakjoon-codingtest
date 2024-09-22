page link : [https://www.acmicpc.net/problem/2309](https://www.acmicpc.net/problem/2309)

---

# 💡 풀이전략

- 전체 키 합 구하기
- 두 난쟁이를 제외하기: 두 난쟁이를 제외했을 때 일곱명의 키가 100이 되는 조합 찾기
- 출력: 7 난쟁이 키 오름차순 출력

## 🎨 사용된 알고리즘

> [!tip]
> Brute-Force: 완전 탐색

---

# code

## Python

```python
import sys

def find_origin_dwarfs(dwarfs_height):
    all_dwarfs_height = 100
    number_of_dwarfs = len(dwarfs_height)
    total_sum = sum(dwarfs_height)
    for i in range(number_of_dwarfs):
        for j in range(i + 1, number_of_dwarfs):
            if  total_sum - dwarfs_height[i] - dwarfs_height[j] == all_dwarfs_height:
                origin_dwarfs = [dwarfs_height[x] for x in range(number_of_dwarfs) if x != i and x != j]
                return sorted(origin_dwarfs)

#입력값 파싱
input_data = sys.stdin.read()
dwarfs_height = [line for line in map(int, input_data.splitlines())]
origin_dwarf = find_origin_dwarfs(dwarfs_height)
for dwarf in origin_dwarf:
    print(dwarf)
```

## 해결한 오류

### 1. 리펙터링

- 기존 코드에서는 `found`라는 `boolean`값을 두어, 값을 찾게되면 `found = True` 로 변경하고 `break` 명령어를 넣었다.
- 그러나 값을 찾으면 `return`으로 해당 값을 반환하게 하면, 코드를 좀 더 간결하게 작성할 수 있다.
    - 기존코드
        
        ```python
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
        ```
        
    
    - 수정된 코드
        
        ```python
        def find_origin_dwarfs(dwarfs_height):
            all_dwarfs_height = 100
            number_of_dwarfs = len(dwarfs_height)
            total_sum = sum(dwarfs_height)
            for i in range(number_of_dwarfs):
                for j in range(i + 1, number_of_dwarfs):
                    if  total_sum - dwarfs_height[i] - dwarfs_height[j] == all_dwarfs_height:
                        origin_dwarfs = [dwarfs_height[x] for x in range(number_of_dwarfs) if x != i and x != j]
                        return sorted(origin_dwarfs)
        ```
        

---

- 기존 코드
    
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
