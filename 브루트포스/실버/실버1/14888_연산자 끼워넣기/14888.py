import sys
import itertools

def main(input_data):
    parsed_data = ParseData(input_data)
    numbers, operators = parsed_data.numbers, parsed_data.operators

    calculator = ExpressionCalculator(numbers, operators)
    max_value, min_value = calculator.find_max_min()
    print(max_value)
    print(min_value)

class ParseData:
    def __init__(self, input_data):
       self.numbers, self.operators = self.parse_input(input_data)
    
    def parse_input(self, input_data):
        lines = input_data.strip().splitlines()  # 공백 제거
        N = int(lines[0])
        numbers = list(map(int, lines[1].split()))
        add, sub, mul, div = map(int, lines[2].split())

        operators = ['+'] * add + ['-'] * sub + ['*'] * mul + ['/'] * div
        return numbers, operators

class ExpressionCalculator:
    def __init__(self, numbers, operators):
        self.numbers = numbers
        self.operators = operators
        self.operations = {
            '+': lambda a, b: a + b,
            '-': lambda a, b: a - b,
            '*': lambda a, b: a * b,
            '/': self.divide
        }
    
    def divide(self, a, b):
        # a * b < 0이면 음수 나눗셈
        if a * b < 0:
            return -(-a // abs(b))
        else:
            return a // b
        
    def calculate_expression(self, operator_perm):
        result = self.numbers[0]
        for i in range(1, len(self.numbers)):
            operator = operator_perm[i - 1]
            result = self.operations[operator](result, self.numbers[i])
        return result
    
    def find_max_min(self):
        max_value = -1e9
        min_value = 1e9

        # 중복을 제거한 연산자 순열을 생성합니다.
        for operator_perm in itertools.permutations(self.operators, len(self.numbers) - 1):
            result = self.calculate_expression(operator_perm)
            max_value = int(max(max_value, result))
            min_value = int(min(min_value, result))
        return max_value, min_value
    
if __name__ == '__main__':
    input_data = sys.stdin.read()
    main(input_data)
