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
