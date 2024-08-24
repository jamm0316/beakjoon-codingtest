import sys

def main():
    input = sys.stdin.read()
    command = StackCommand()
    command.parse_data(input)
    command.print_command()

class StackCommand:
    def __init__(self):
        self.N = 0
        self.queries = []
        self.stack = []
    
    def parse_data(self, input):
        lines = input.strip().split('\n')
        self.N = lines[0]
        self.queries = [line.split() for line in lines[1:]]
        
    def print_command(self):
        for query in self.queries:
            if query[0] == "push":
                self.stack.append(int(query[1]))
            elif query[0] == "top":
                if len(self.stack) == 0:
                    print(-1)
                else:
                    print(self.stack[-1])
            elif query[0] == "size":
                print(len(self.stack))
            elif query[0] == "empty":
                if len(self.stack) == 0:
                    print(1)
                else:
                    print(0)
            elif query[0] == "pop":
                if len(self.stack) != 0:
                    pop_num = self.stack.pop()
                    print(pop_num)
                else:
                    print(-1)
    
if __name__ == '__main__':
    main()
