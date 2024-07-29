
page link : 
---

# 100점

## 💡 풀이전략

1. **전처리 (Preprocessing) 단계**:
    1. 문자열의 각 문자가 등장하는 누적 횟수를 계산.
    2. 이후 각 쿼리에 대해 빠르게 답변.
2. **쿼리 처리 (Query Processing) 단계**:
    1. 각 쿼리마다 주어진 구간에서 특정 문자의 등장 횟수를 누적 등장 횟수 배열을 사용하여 빠르게 계산.

## 🎨 사용된 알고리즘

1. **전처리 (Preprocessing)**:
    1. 문자열의 길이 N에 대해, 각 문자의 누적 등장 횟수를 저장하는 배열 작성.
    2. 문자열을 순회하면서, 각 문자의 누적 등장 횟수를 계산하여 배열에 저장.
2. **쿼리 처리 (Query Processing)**:
    1. 누적 등장 횟수 배열을 사용하여 주어진 구간에서 특정 문자의 등장 횟수를 O(1) 시간에 계산.

---

## pseudo code

```python
1.	전처리 (Preprocessing):
	•	입력 문자열 S와 질문의 수 q를
	•	길이 (N+1) x 26의 2차원 배열 ‘counts’를 모두 0으로 초기화
	•	문자열 S의 각 문자를 순회:
	•	현재 문자의 인덱스 계산 (‘a’를 0으로, ‘b’를 1로, …, ‘z’를 25로).
	•	이전 행의 값을 현재 행에 복사.
	•	현재 문자의 횟수를 증가.
2.	쿼리 처리 (Query Processing):
	•	각 쿼리에 대해:
	•	특정 문자와 시작 인덱스 l, 끝 인덱스 r.
	•	쿼리 문자의 인덱스를 계산.
	•	‘counts’ 배열을 사용하여 구간 [l, r]에서 해당 문자의 등장 횟수를 계산.
	•	결과 리스트에 추가.
	•	모든 결과를 출력.
```

# 50점

## 💡 풀이전략

1. **입력 처리 단계**:  
    2. 각 질문을 처리하여 문자, 시작 인덱스, 끝 인덱스를 분리.
2. **쿼리 처리 단계**:

## 🎨 사용된 알고리즘
1. **입력 처리:**
    1. 문자열과 쿼리 데이터를 리스트로 분리하여 저장.
2. **슬라이싱 및 문자 세기**:
    1. 각 쿼리마다 문자열을 슬라이싱하고, 슬라이싱된 부분 문자열에서 특정 문자의 등장 횟수를 계산.

---

## pseudo code

```python
	1.	입력 처리:
	•	표준 입력으로부터 전체 입력.
	•	문자열 S와 질문의 수 K.
	•	각 질문을 분리하여 문자, 시작 인덱스, 끝 인덱스를 저장.
	2.	쿼리 처리:
	•	각 질문에 대해:
	•	시작 인덱스와 끝 인덱스를 사용하여 문자열 S를 슬라이싱.
	•	슬라이싱된 부분 문자열에서 특정 문자의 등장 횟수를 계산.
	•	결과를 출력.
```

---

# code

## Python — 100점

```python
import sys

# 입력 읽기
input = sys.stdin.read
data = input().split()

# 문자열 S와 질문의 수 q
s = data[0]
q = int(data[1])

# 전처리 (Preprocessing) 단계
def preprocess_string(s):
    n = len(s)
    # 각 문자의 누적 등장 횟수를 저장할 배열 초기화. 배열 크기는 (N+1) x 26.
    counts = [[0] * 26 for _ in range(n + 1)]
    
    # 문자열의 각 문자를 순회하며 누적 등장 횟수를 계산.
    for i in range(n):
        char_index = ord(s[i]) - ord('a')  # 현재 문자의 인덱스 계산 ('a'를 0으로)
        for j in range(26):
            counts[i + 1][j] = counts[i][j]  # 이전까지의 누적 횟수를 복사
        counts[i + 1][char_index] += 1  # 현재 문자의 횟수 증가
    
    return counts

# 주어진 구간에서 특정 문자의 등장 횟수 계산.
def query_count(counts, char, l, r):
    char_index = ord(char) - ord('a')  # 문자의 인덱스 계산 ('a'를 0으로)
    # 누적 횟수를 사용하여 구간 [l, r]에서의 등장 횟수 계산
    return counts[r + 1][char_index] - counts[l][char_index]

# 문자열 S에 대한 누적 등장 횟수 배열 전처리.
counts = preprocess_string(s)

# 각 질문에 대해 결과를 계산하고 저장.
index = 2
results = []
for _ in range(q):
    char = data[index]  # 특정 문자
    l = int(data[index + 1])  # 구간 시작 인덱스
    r = int(data[index + 2])  # 구간 끝 인덱스
    results.append(query_count(counts, char, l, r))  # 쿼리 결과 저장
    index += 3

# 모든 결과를 출력.
print("\n".join(map(str, results)))
```

## Python — 50점

```python
import sys

# 입력을 표준 입력으로부터 읽어옵니다.
input = sys.stdin.read()
data = input.split('\n')

# 입력값 받기
s = list(data[0])  # 문자열 S
K = int(data[1])  # 질문의 수 K
queries = []
for i in range(2, K + 2):
    queries.append(data[i].split())

# 각 질문에서 특정 문자를 분리하여 저장
char_queries = []
for char, start, end in queries:
    char_queries.append(char)

# 각 질문에서 구간을 슬라이싱하여 부분 문자열을 저장
str_queries = []
for char, start, end in queries:
    str_queries.append(s[int(start) : int(end) + 1])

# 각 질문에 대해 결과를 계산하고 출력
for i in range(K):
    result = str_queries[i].count(char_queries[i])
    print(result)
```

## 해결한 오류

### 1. 시간복잡도 O(Q * N) → O(N + Q)

- **시간 복잡도 분석**
    1. **첫 번째 코드 (효율적인 코드)**
        1. **전처리 시간**: O(N * 26) (N은 문자열의 길이)
        2. **각 쿼리 처리 시간**: O(1)
        3. **전체 시간 복잡도**: O(N * 26 + Q * 1) = O(N + Q) (여기서 N은 문자열의 길이, Q는 질문의 수)
    2. **두 번째 코드 (비효율적인 코드)**
        1. **전처리 시간**: 없음
        2. **각 쿼리 처리 시간**: O(r - l) (r과 l은 구간의 크기)
        3. **전체 시간 복잡도**: O(Q * (r - l)), 최악의 경우 O(Q * N)
- **효율적인 접근 방법**
    
    전처리 시간을 투자하여 쿼리 시간을 줄이는 것이 전체 시간 복잡도를 줄이는 데 효과적입니다. 따라서, 전처리 시간이 길더라도 각 쿼리를 매우 빠르게 처리할 수 있는 알고리즘이 더 효율적입니다.
    
