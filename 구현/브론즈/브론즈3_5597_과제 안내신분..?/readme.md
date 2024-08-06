page link : [https://www.acmicpc.net/problem/5597](https://www.acmicpc.net/problem/5597)

# 💡 풀이전략
1. 입력값 받기
    1. submit 배열의 형태로 받기
2. 번호별 학생부 초기화
    1. 학생부 = [0] * 30
3. 배열 순회
    1. 각 번호가 존재할 시 학생부의 해당 번호 += 1
    2. for i in submit:
        students_list[i -1] += 1
4. 학생부를 순회하며 0인 인덱스 반환
    1. do_not_submit = []
    2. for i in range(len(students_list)):
         if stdents_list[i] == 0:
            do_not_submit.append(i + 1)
5. 2개의 값 반환
    1. print(do_not_submit[0])
    2. print(do_not_submit[1])

## 🎨 사용된 알고리즘
구현(implementation)

---

# code

## Python

```python
import sys
input = sys.stdin.read()

# 입력값 읽기
submit = list(map(int, input.split()))

# 상수설정
N = 30  # 학생 수
students_list = [0] * N  # 학생부 초기화

# 제출한 인원 업데이트
for i in submit:
    students_list[i - 1] += 1

# 업데이트 된 학생부에서 0인 학생 추출
do_not_submit = []
for i in range(len(students_list)):
    if students_list[i] == 0:
        do_not_submit.append(i + 1)

# 2개의 값 반환
print(do_not_submit[0])
print(do_not_submit[1])
```
