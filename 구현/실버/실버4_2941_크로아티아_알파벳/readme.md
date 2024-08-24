page link : [https://www.acmicpc.net/problem/2941](https://www.acmicpc.net/problem/2941)


# 💡 풀이전략


1. 크로아티아 알파벳 저장
2. 저장된 크로아티아 알파벳을 순환하며 문자열 replace (croatia_alphabet → !)
3. len(word)

# code

## Python

```python
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
```
