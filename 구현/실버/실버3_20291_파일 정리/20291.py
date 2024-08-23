import sys

def main():
    input = sys.stdin.read()
    parsing = ParseFiles()
    parsing.parse_data(input)
    parsing.print_extension()

class ParseFiles:
    def __init__(self):
        self.N = 0
        self.files = []
        self.extension_count = {}

    def parse_data(self, input):
        lines = input.split('\n')
        self.N = int(lines[0])
        self.files = [(lines[i]) for i in range(1, self.N + 1)]

    def organize_files(self):
        for file in self.files:
            extension = file.split(".")[-1]
            if extension in self.extension_count:
                self.extension_count[extension] += 1
            else:
                self.extension_count[extension] = 1

    def print_extension(self):
        self.organize_files()
        sorted_extension = sorted(self.extension_count)
        for extension in sorted_extension:
            print(extension, self.extension_count[extension])

if __name__ == '__main__':
    main()
