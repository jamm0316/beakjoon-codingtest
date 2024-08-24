import sys

def main():
    word = sys.stdin.read().strip()
    parse = ParseCroatiaAlphabet()
    parse.count_char(word)

class ParseCroatiaAlphabet():
    def __init__(self):
        self.croatia_alphabets = ["c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="]

    def count_char(self, word):
        for alphabet in self.croatia_alphabets:
            word = word.replace(alphabet, "!")
        print(len(word))

if __name__ == '__main__':
    main()
