import sys

def main():
    # 주어진 입력 데이터를 문자열로 저장
    input_data = sys.stdin.read()
    
    # SeatingArrangement 클래스의 인스턴스를 생성하고, 입력 데이터를 전달
    seating = SeatingArrangement(input_data)
    
    # 모든 학생 자리 초기화
    seating.arrangeSeats()

    # 모든 학생의 만족도를 계산하여 total_satisfaction 변수에 저장
    total_satisfaction = seating.surveySatisfaction()
    
    # 최종적으로 계산된 만족도를 출력
    print(total_satisfaction)

class SeatingArrangement():
    def __init__(self, input_data):
        self.N = 0  # 교실의 크기 (NxN)
        self.student_info = {}  # 학생 번호와 각 학생이 좋아하는 학생 번호를 저장하는 딕셔너리
        self.order = []  # 학생들이 자리에 앉는 순서를 저장하는 리스트
        self.class_room = []  # 교실의 상태를 나타내는 2차원 배열

        # 입력 데이터를 파싱하여 교실 크기, 학생 정보, 순서 등을 초기화
        self.parse_data(input_data)

    def parse_data(self, input_data):
        # 입력 데이터를 줄 단위로 분할
        lines = input_data.split('\n')
        # 교실의 크기 N을 첫 번째 줄에서 가져옴
        self.N = int(lines[0])
        # N x N 크기의 빈 교실을 초기화
        self.class_room = [[0] * self.N for _ in range(self.N)]

        # 학생 수 (N^2) 만큼 반복하면서 각 학생의 정보를 저장
        for i in range(self.N ** 2):
            # 각 학생의 번호와 좋아하는 학생들 번호를 리스트로 저장
            data = list(map(int, lines[i + 1].split()))
            # 학생 번호를 키로, 좋아하는 학생들의 번호를 값으로 저장
            self.student_info[data[0]] = data[1:]
            # 학생들이 앉는 순서를 저장
            self.order.append(data[0])
    
    def arrangeSeats(self):
        # 상하좌우 방향을 나타내는 이동 벡터
        dx = [-1, 1, 0, 0]
        dy = [0, 0, -1, 1]

        # 학생들을 순서대로 배치
        for student in self.order:
            best_position = (-1, -1)  # 최적의 자리를 저장할 변수 초기화
            max_like_count = -1  # 인접한 칸에 있는 좋아하는 학생 수의 최대값
            max_empty_count = -1  # 인접한 칸 중 비어있는 칸 수의 최대값

            # 교실의 모든 자리를 탐색
            for i in range(self.N):
                for j in range(self.N):
                    if self.class_room[i][j] != 0:  # 이미 학생이 앉아 있는 자리라면 건너뜀
                        continue
                    
                    like_count = 0  # 인접한 좋아하는 학생 수 초기화
                    empty_count = 0  # 인접한 빈 칸 수 초기화

                    # 상하좌우 인접한 칸을 탐색
                    for k in range(4):
                        ni = i + dx[k]
                        nj = j + dy[k]
                        if 0 <= ni < self.N and 0 <= nj < self.N:  # 교실 범위 내에서만 탐색
                            if self.class_room[ni][nj] in self.student_info[student]:  # 인접한 칸에 좋아하는 학생이 있는지 확인
                                like_count += 1
                            if self.class_room[ni][nj] == 0:  # 인접한 칸이 빈 칸인지 확인
                                empty_count += 1
                    
                    # 최적의 자리를 찾는 조건문
                    # 현재 위치가 최적의 위치인지 확인하는 플래그 변수
                    is_better_position = False

                    # 좋아하는 학생 수가 더 많으면 현재 위치가 더 나음
                    if like_count > max_like_count:
                        is_better_position = True
                    # 좋아하는 학생 수가 동일하면 빈 칸 수로 비교
                    elif like_count == max_like_count:
                        if empty_count > max_empty_count:
                            is_better_position = True
                        # 좋아하는 학생 수와 빈 칸 수가 동일하면 행과 열의 순서로 비교
                        elif empty_count == max_empty_count:
                            if best_position == (-1, -1) or i < best_position[0] or (i == best_position[0] and j < best_position[1]):
                                is_better_position = True

                    # 최적의 위치를 갱신
                    if is_better_position:
                        max_like_count = like_count
                        max_empty_count = empty_count
                        best_position = (i, j)

            # 최적의 위치에 학생을 배치
            self.class_room[best_position[0]][best_position[1]] = student
    
    def surveySatisfaction(self):
        total_satisfaction = 0  # 총 만족도를 저장할 변수 초기화
        satisfaction_score = [0, 1, 10, 100, 1000]  # 인접한 좋아하는 학생 수에 따른 만족도 점수
        dx = [-1, 1, 0, 0]  # 상하좌우 방향 벡터
        dy = [0, 0, -1, 1]

        # 교실의 모든 자리를 탐색
        for i in range(self.N):
            for j in range(self.N):
                student = self.class_room[i][j]  # 현재 자리에 앉은 학생 번호
                like_count = 0  # 인접한 좋아하는 학생 수 초기화
                for k in range(4):
                    ni = i + dx[k]
                    nj = j + dy[k]
                    if 0 <= ni < self.N and 0 <= nj < self.N:  # 교실 범위 내에서 탐색
                        if self.class_room[ni][nj] in self.student_info[student]:  # 인접한 칸에 좋아하는 학생이 있는지 확인
                            like_count += 1
                # 인접한 좋아하는 학생 수에 따른 만족도를 누적
                total_satisfaction += satisfaction_score[like_count]

        return total_satisfaction  # 최종 만족도를 반환

if __name__ == '__main__':
    main()  # main 함수 실행
