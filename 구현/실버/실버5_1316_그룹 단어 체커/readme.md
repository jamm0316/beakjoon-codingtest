page link : [https://www.acmicpc.net/problem/1316](https://www.acmicpc.net/problem/1316)

# 💡 풀이전략
1. **문제 이해 및 요구사항 분석**
    - **문제:** 주어진 단어 목록에서 “그룹 단어”의 개수를 계산하는 프로그램 작성.
    - **그룹 단어:** 단어에 있는 모든 문자가 연속해서 나타나는 경우.
    - **입력:** 첫째 줄에 단어의 개수 N, 둘째 줄부터 N개의 단어.
    - **출력:** 그룹 단어의 개수.

**2. 입력 데이터 처리**

- **입력 형식:** 표준 입력을 통해 첫 줄에 단어의 개수, 이후 줄에 단어가 주어짐.
- **입력 파싱:** 입력 데이터를 표준 입력에서 읽어와 처리.

**3. 그룹 단어 판별 로직**

- **알고리즘:** 주어진 단어가 그룹 단어인지 판별하는 함수 작성.
- 각 문자를 순차적으로 읽으면서 이전에 나온 적이 있는 문자와 현재 문자를 비교.
- 이전에 나온 적이 있는 문자가 연속되지 않으면 그룹 단어가 아님.
- 이를 위해 집합(`set`)을 사용해 이미 등장한 문자를 기록.

**4. 그룹 단어 개수 계산**

- **클래스 설계:** `GroupWordChecker` 클래스를 만들어 쿼리 목록을 처리.
- `count_group_words` 메서드: 모든 쿼리를 검사해 그룹 단어의 개수를 계산.
- `is_group_word` 메서드: 개별 쿼리가 그룹 단어인지 판별.

**5. 메인 함수 작성**

- **메인 함수:** 입력을 읽고 `GroupWordChecker` 클래스를 사용해 결과를 출력.

## 🎨 사용된 알고리즘
**구현(implement)**

---

# code

## Python

```python
import sys

def main():
    """입력을 읽고 그룹 단어를 확인하는 메인 함수."""
    input_data = sys.stdin.read()
    data = input_data.split()

    # 입력 데이터 파싱
    query_count = int(data[0])  # 쿼리 갯수
    queries = data[1:]  # 쿼리 목록

    # 쿼리로 초기화된 체커 생성
    checker = GroupWordChecker(queries)
    
    # 결과 출력
    print(checker.count_group_words())
    
class GroupWordChecker:
    def __init__(self, queries):
        self.queries = queries

    def count_group_words(self):
        """제공된 쿼리에서 그룹 단어의 수를 계산합니다."""
        group_word_count = 0
        for query in self.queries:
            if self.is_group_word(query):
                group_word_count += 1
        return group_word_count

    def is_group_word(self, query):
        """주어진 쿼리가 그룹 단어인지 확인합니다."""
        seen_chars = set()
        previous_char = None

        for char in query:
            if char != previous_char:
                if char in seen_chars:
                    return False
                seen_chars.add(char)
                previous_char = char
        return True
    
if __name__ == '__main__':
    main()
```

## 해결한 오류

### 1. 절차지향 프로그래밍 → 객체지향 프로그래밍

1. **구조적 수정**:
    - 객체지향 프로그래밍(OOP) 스타일로 작성되어 코드의 재사용성과 확장성이 높힘.
    - GroupWordChecker 클래스는 쿼리 처리 로직으로 캡슐화
2. **가독성 향상**:
    - 메서드와 클래스 이름 직관적으로 변경
    - 각 메서드가 한 가지 역할만 수행합니다.
    - 주석 추가
3. **유지보수성**:
    - 새로운 기능을 추가하거나 기존 기능을 수정할 때 클래스를 확자 용이
    - `GroupWordChecker` 클래스는 쿼리 처리와 그룹 단어 체크 기능을 분리하여 더 명확하게 관리

---

- 기존코드
    
    ```python
    import sys
    
    input = sys.stdin.read()
    data = list(input.split())
    
    #입력값 읽기
    K = int(data[0])  #쿼리 갯수
    queries = data[1:]  # 쿼리들
    
    def main(queries):
        count = 0
        for qeury in queries:
            if test_group_word(qeury):
                count += 1
        return count
    
    # 그룹단어 체커
    def test_group_word(qeury):
        seen = set()
        previous_word = None
    
        for char in qeury:
            if char != previous_word:
                if char in seen:
                    return False
                seen.add(char)
                previous_word = char
        return True
    
    if __name__ == '__main__':
        print(main(queries))
    ```
