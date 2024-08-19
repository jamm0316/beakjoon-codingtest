import sys

def main():
    input = sys.stdin.read()
    josephus = Josephus()
    josephus.parse_data(input)
    josephus.itinerate()

class Josephus:
    def __init__(self):
        self.N = 0
        self.K = 0
    
    def parse_data(self, input):
        data = list(map(int, input.split()))
        self.N = data[0]
        self.K = data[1]
        self.A = []
        for i in range(self.N):
            self.A.append(i + 1)

    def itinerate(self):
        index = 0
        que = []
        while self.A:
            index = (index + self.K - 1) % len(self.A)
            que.append(self.A.pop(index))
        print("<" + ", ".join(map(str, que)) + ">")

if __name__ == '__main__':
    main()
