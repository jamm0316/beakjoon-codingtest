page link : https://www.acmicpc.net/problem/11659
# 풀이전략
```
1. 입력 읽기:
  • 표준 입력으로부터 모든 데이터를 읽어옵니다.
  • 첫 번째 줄에서 숫자 배열의 크기 N과 쿼리의 개수 M을 읽습니다.
  • 두 번째 줄부터 N개의 숫자를 읽습니다.
  • 이후 M개의 쿼리를 읽습니다.
2. 누적 합 배열 생성:
  • 숫자 배열의 각 위치까지의 합을 저장하는 누적 합 배열을 생성합니다.
  • 누적 합 배열을 사용하면 특정 구간의 합을 빠르게 계산할 수 있습니다.
3. 쿼리 처리:
  • 각 쿼리에 대해 구간 i부터 j까지의 합을 누적 합 배열을 사용하여 계산합니다.
  • 계산된 결과를 리스트에 저장합니다.
4. 결과 출력:
  • 각 쿼리에 대한 결과를 표준 출력으로 출력합니다.
```

---

## 사용알고리즘

```
  • 누적 합 배열 (Prefix Sum Array):
  • 배열의 앞에서부터 현재 위치까지의 합을 저장하는 배열입니다.
  • 구간 합을 빠르게 계산할 수 있도록 도와줍니다.
  • prefix\\_sum[k]는 1번째 수부터 k번째 수까지의 합을 저장합니다.
  • 구간 합 계산:
  • 주어진 구간 i부터 j까지의 합은 prefix\\_sum[j] - prefix\\_sum[i-1]로 계산할 수 있습니다.
  • 이 방법을 사용하면 각 쿼리를 O(1) 시간에 처리할 수 있습니다.
```

---

# code

## Python

```python
import sys
input = sys.stdin.read()

def main():
    # 표준 입력으로부터 모든 데이터를 읽고 공백으로 분리하여 리스트로 저장
    data = input.split()
    
    # 첫 번째와 두 번째 값을 N과 M으로 변환
    N = int(data[0])
    M = int(data[1])
    
    # 다음 N개의 숫자를 리스트로 변환
    numbers = list(map(int, data[2:N+2]))
    
    # 쿼리 리스트 초기화 및 데이터에서 쿼리 추출
    queries = []
    for i in range(N+2, len(data), 2):
        queries.append((int(data[i]), int(data[i+1])))
    
    # 누적 합 배열 생성
    prefix_sum = [0] * (N + 1)
    for i in range(1, N + 1):
        prefix_sum[i] = prefix_sum[i - 1] + numbers[i - 1]
    
    # 쿼리 처리 및 결과 저장
    results = []
    for (i, j) in queries:
        result = prefix_sum[j] - prefix_sum[i - 1]
        results.append(result)
    
    # 결과를 한 줄씩 출력
    return '\n'.join(map(str, results))

print(main())
    
```

## 해결한 오류

### 1. 리스트 컴프리핸션 문제
#### 💡 리스트 컴프리 핸션을 쓰려면 반복문 필요
- 기존 코드
```python
numbers = [map(int, data[2 : N + 2])]
```
  - 여기서 리스트 컴프리핸션을 사용하려면 [] 안에 반복문에 대한 리턴값이 들어가야함

- 수정된 코드
```python
# 1. list comprehension
# 반복문을 통해 data를 순회하며 값 appending
numbers = [int(data[i]) for i in range(2, N + 2)]


# 2. list 함수 사용
# data 리스트를 slicing한 후 map을 통해 int로 appending
numbers = list(map(int, data[2 : N + 2])
```

### 2. data(문자열)를 int(숫자열, 정수)로 변경

#### 💡 data를 문자열로 사용하고 있음

```python
# 함수 정의
def main():
    data = input.split()
    N = data[0]
    M = data[1]
```

위 코드에서 data를 문자열로 받아 사용하므로 숫자로 인식하지 못함.  
따라서 int 추가 필요

```python
# 함수 정의
def main():
    data = input.split()
    N = int(data[0])
    M = int(data[1])
```

### 3. prefix_sum 배열 구성 시 정확한 인덱스에 바인딩

#### 💡 prefix_sum → prefix_sum[i]

```python
# 누적합 배열 만들기
prefix_sum = [0] * (N + 1)
for i in range(1, N + 1):
    prefix_sum = numbers[i - 1] + prefix_sum[i - 1]
```

위 코드는 prefix_sum에 append하는 행위도 아니고 그냥 숫자를 바인딩 하는 행위이므로 prefix_sum이 list로 반환되지 않는다.

따라서 prefix_sum → preix_sum[i]로 변경하여, 정확한 인덱스에 변경된 값을 바인딩 시켜준다.

```python
# 누적합 배열 만들기
prefix_sum = [0] * (N + 1)
for i in range(1, N + 1):
    prefix_sum[i] = numbers[i - 1] + prefix_sum[i - 1]
```

### 기존 code
    
```python
import sys

# 인자 받기
input = sys.stdin.read()

# 함수 정의
def main():
    data = input.split()
    N = data[0]
    M = data[1]
    
    # 배열 만들기
    numbers = [map(int, data[2 : N + 2])]
    
    # queries 정의
    queries = []
    for i in range(N + 2, len(data), 2):
        queries.append((int(data[i]), int(data[i + 1])))
    
    # 누적합 배열 만들기
    prefix_sum = [0] * (N + 1)
    for i in range(1, N + 1):
        prefix_sum = numbers[i - 1] + prefix_sum[i - 1]
    
    # querie에 따른 누적합 구하기
    results = []
    for i, j in queries:
        result = prefix_sum[j] - prefix_sum[i - 1]
        results.append(result)
    
    return '\n'.join(map(str, results))

print(main())
```

---

[질문저장소](https://www.notion.so/ca228c2f14eb49a3a009697fe98d95b4?pvs=21)
