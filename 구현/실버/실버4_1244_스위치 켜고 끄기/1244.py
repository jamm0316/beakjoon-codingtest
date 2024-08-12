import sys

def main():
    input = sys.stdin.read()
    switch_controller = SwitchController()  # 명확한 클래스 이름
    switch_controller.parse_input(input)
    switch_controller.process_queries()
    switch_controller.print_switch_states()

class SwitchController:
    def __init__(self):
        self.N = 0  # 스위치 개수
        self.A = []  # 스위치 상태 리스트
        self.queries = []  # 쿼리 리스트

    def parse_input(self, input):
        lines = input.strip().split('\n')
        self.N = int(lines[0])
        self.A = list(map(int, lines[1].split()))
        query_count = int(lines[2])

        for i in range(3, 3 + query_count):
            gender, number = map(int, lines[i].split())
            self.queries.append((gender, number))

    def toggle_switch(self, index):
        self.A[index] = 1 - self.A[index]

    def process_queries(self):
        for gender, number in self.queries:
            if gender == 1:  # 남학생의 경우
                self.toggle_by_male(number)
            elif gender == 2:  # 여학생의 경우
                self.toggle_by_female(number)

    def toggle_by_male(self, number):
        for i in range(number - 1, self.N, number):
            self.toggle_switch(i)

    def toggle_by_female(self, number):
        index = number - 1
        self.toggle_switch(index)
        left = index - 1
        right = index + 1
        while left >= 0 and right < self.N and self.A[left] == self.A[right]:
            self.toggle_switch(left)
            self.toggle_switch(right)
            left -= 1
            right += 1

    def print_switch_states(self):
        for i in range(0, self.N, 20):
            print(' '.join(map(str, self.A[i:i+20])))

if __name__ == '__main__':
    main()
