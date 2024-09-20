page link : [https://www.acmicpc.net/problem/2231](https://www.acmicpc.net/problem/2231)

---

# 💡 풀이전략

- 출력값: 생성자 중 가장 작은 수 반환

---

- 분해합 만드는 공식
    
    ```python
    result = n  # 생성자
    while n > 0:
        result += n % 10
        n //= 10
    print(result)  # 분해합
    ```
    
- 분해합을 생성자로 역산하는 공식
    
    ```python
    list = []
    for i in range(result):
        origin_i = i
        n = i
        while n > 0:
            i += n % 10
            n //= 10
        if i == target_number:
            list.append(i)
    print(sorted(list))
    ```
    
## 🎨 사용된 알고리즘

> [!tip]
> Brute-Force: 완전 탐색

---

# code

## Python

```python
def find_smallest_generator(target_number):
    for i in range(1, target_number + 1):
        decomposition_sum = i + sum(map(int, str(i)))

        if decomposition_sum == target_number:
            return i
    return 0

target_number = int(input())

print(find_smallest_generator(target_number))
```

## 해결한 오류

### 1. 리펙토링

- 분해합을 리스트로 저장 후 이를 정렬하여, 첫번째 수를 찾는 것이 아닌, 분해합 계산을 통해 얻어지는 첫번째 수를 출력하도록 변경
    1. **불필요한 리스트 제거**
    2. **간결해진 분해합 계산**
    3. **반복문 범위 축소**
    4. **불필요한 정렬 제거**
    5. **간단한 반환처리**

**기존코드**

```python
#함수가 리스트를 반환하도록 정의
def find_candidate(target_number):
    generator_candidate = list()
    
    for i in range(1, target_number):
        original_i = i
        decomposition_sum = i
        
        while i > 0:
            decomposition_sum += i % 10
            i //= 10
        
        if decomposition_sum == target_number:
            generator_candidate.append(original_i)
    
    return sorted(generator_candidate)
...

#출력 조건은 따로 정의
if result:
    print(result[0])
else:
    print(0)
```

**수정된 코드**

```python
# map을 이용해 부분합 공식 수정 및 간단한 반환처리
def find_smallest_generator(target_number):
    for i in range(1, target_number + 1):
        decomposition_sum = i + sum(map(int, str(i)))

        if decomposition_sum == target_number:
            return i
    return 0
...
print(find_smallest_generator(target_number))
```

---

- 기존코드
    
    ```python
    def find_candidate(target_number):
        generator_candidate = list()
        
        for i in range(1, target_number):
            original_i = i
            decomposition_sum = i
            
            while i > 0:
                decomposition_sum += i % 10
                i //= 10
            
            if decomposition_sum == target_number:
                generator_candidate.append(original_i)
        
        return sorted(generator_candidate)
    
    input_data = int(input())
    result = find_candidate(input_data)
    
    if result:
        print(result[0])
    else:
        print(0)
    ```
