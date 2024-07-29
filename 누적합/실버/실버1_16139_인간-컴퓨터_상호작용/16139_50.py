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
