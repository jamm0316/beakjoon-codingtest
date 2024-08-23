page link : [https://www.acmicpc.net/problem/20291](https://www.acmicpc.net/problem/20291)


# 💡 풀이전략

- 아이디어
    1. 인터페이스 초기화
        1. 배열갯수, 배열, 확장자 dictionary
    2. 배열을 순회하며 `.`이후의 확장자 추출하여 dict구성
        1. `split(”.”)[-1]`
        2. dictionay에 extension있으면 숫자세기
        
        ```python
        if extension in dictionary:
            extension += 1
        else:
            extension = 1
        ```
        
    3. `sorted(dictionary)` 함수로 문자열 정렬
    4. 순회하며 print(key value)

## 🎨 사용된 알고리즘

> [!tip]
> 구현(implement)<br>
> 자료 구조<br>
> 정렬

---

# code

## Python

```python
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
```

- 숏코드
    
    ```python
    # Step 1: 입력 받기
    n = int(input())
    files = [input().strip() for _ in range(n)]
    
    # Step 2: 확장자 개수 세기
    extension_count = {}
    
    for file in files:
        # 확장자 추출
        ext = file.split('.')[-1]
        if ext in extension_count:
            extension_count[ext] += 1
        else:
            extension_count[ext] = 1
    
    # Step 3: 확장자 사전순으로 정렬 후 출력
    for ext in sorted(extension_count.keys()):
        print(ext, extension_count[ext])
    ```
    

## 해결한 오류

### sort()와 sorted()의 차이점

1. 메서드와 함수
    - `sort()`는 `list` 메서드이고 `sorted()`는 함수다.
    - 따라서, `dictionary`와 같은 자료구조에는 `sorted()`만 사용 가능
    - ex) `array.sort()`  // `sorted(array)`
2. 원본이 바뀌는가?
    - `sort()`는 원본을 바꾼다.
    - `sorted()`는 원본을 건드리지 않는다.

### 2. dictionary 자료구조에도 sorted()가 사용가능한가?

- `dictionary` 자료구조에도 사용 가능하며, 기본적으로 `key`값을 기준으로 `list`를 반환함.
    
    ```python
    extension = {'txt': 3, 'spc': 2, 'icpc': 2, 'orld': 1}
    sorted_extension = sorted(extension)  #알파벳 순서로 정렬
    print(sorted_extension)
    #출력: ['icpc', 'orld', 'spc', 'txt']
    ```
