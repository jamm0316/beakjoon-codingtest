import sys

def main():
    input = sys.stdin.read()
    rainwater = StagnantWater()
    rainwater.is_stagnant_water(input)

class StagnantWater():
    def __init__(self):
        self.H = 0
        self.W = 0
        self.A = []
    
    def parse_data(self, input):
        data = list(map(int, input.split()))
        self.H = data[0]
        self.W = data[1]
        self.A = data[2:]
        self.left_water_line = [0] * self.W
        self.right_water_line = [0] * self.W

    def calculate_left_water_line(self):
        self.left_water_line[0] = self.A[0]
        for i in range(1, self.W):
            self.left_water_line[i] = max(self.left_water_line[i - 1], self.A[i])
    
    def calculate_right_water_line(self):
        self.right_water_line[self.W - 1] = self.A[self.W - 1]
        for i in range(self.W - 2, -1, -1):
            self.right_water_line[i] = max(self.right_water_line[i + 1], self.A[i])
    
    def is_stagnant_water(self, input):
        self.parse_data(input)
        self.calculate_left_water_line()
        self.calculate_right_water_line()
        total_water = 0
        water_line = [0] * self.W
        for i in range(self.W):
            water_line[i] = min(self.left_water_line[i], self.right_water_line[i])
            total_water += water_line[i] - self.A[i]
        return print(total_water)
    
if __name__ == '__main__':
    main()
