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
