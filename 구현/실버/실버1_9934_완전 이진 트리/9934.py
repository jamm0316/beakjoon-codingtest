import sys

def main():
    input = sys.stdin.read()
    tree = binaryTree()
    tree.parse_data(input)
    tree.print_tree()

class binaryTree:
    def __init__(self):
        self.K = 0
        self.nodes = []
    
    def parse_data(self, input):
        data = list(map(int, input.split()))
        self.K = data[0]
        self.nodes = data[1:]
        self.levels = [[] for _ in range(self.K)]

    def build_tree(self, levels, nodes, depth, start, end):
        if start > end:
            return
        mid = (start + end) // 2
        levels[depth].append(nodes[mid])
        self.build_tree(levels, nodes, depth + 1, start, mid - 1)
        self.build_tree(levels, nodes, depth + 1, mid +1, end)
    
    def print_tree(self):
        self.build_tree(self.levels, self.nodes, 0, 0, len(self.nodes) - 1)
        for level in self.levels:
            print(" ".join(map(str, level)))

if __name__ == '__main__':
    main()
