page link : [https://www.acmicpc.net/problem/17413](https://www.acmicpc.net/problem/17413)

# 💡 풀이전략

1. **클래스와 메서드 정의**
    - ReverseWord 클래스를 정의하여, 이 클래스 내부에 단어를 뒤집는 로직을 구현함.
    - reverse_word 메서드는 주어진 문자열을 순회하면서 태그와 단어를 처리하는 핵심 메서드임.
    - tag_switch 메서드는 태그 모드(tag_mode)를 관리하는 역할을 하며, <와 >의 개수를 추적하여 태그 모드를 전환함.
2. **문자열 순회**
    - reverse_word 메서드는 문자열의 각 문자를 순회하며, 현재 문자가 태그 내부에 있는지(tag_mode) 여부에 따라 다른 처리를 함.
    - tag_mode는 <와 >를 만나면 켜지거나 꺼지며, 태그 내부의 문자와 외부의 단어를 구분하는 데 사용됨.
3. **태그 모드 처리 (tag_mode)**
    - tag_mode가 True인 경우:
        - 현재 문자가 태그 내부에 있음을 의미함.
        - 태그 내부의 문자는 그대로 results에 추가됨.
        - 태그가 시작될 때("<"를 만날 때)까지 모아둔 단어(words)가 있다면, 이를 뒤집어서 결과에 추가한 후 words를 초기화함.
    - tag_mode가 False인 경우:
        - 현재 문자가 태그 외부에 있는 단어에 속함을 의미함.
        - 단어를 words에 모으다가, 공백(" ")을 만나면 모아둔 단어를 뒤집어서 results에 추가함.
4. **태그 스위치 처리**
    - tag_switch 메서드는 <와 >를 만나면 스택을 사용해 태그 내부와 외부를 구분함.
    - <를 만나면 스택에 "<"를 추가하고, >를 만나면 스택에서 제거함.
    - 스택이 비어 있으면 태그 모드를 False로 설정하고, 그렇지 않으면 True로 설정하여 태그 내부 상태를 관리함.
5. **결과 문자열 조합**
    - 순회를 마친 후, words에 남아 있는 단어가 있다면 이를 뒤집어서 results에 추가함.
    - 최종적으로, results에 저장된 모든 문자열 조각을 합쳐서 반환함.

## 🎨 사용된 알고리즘

1. **스택을 활용한 태그 모드 전환**:
    - 스택을 사용하여 <와 >를 만나면 스택에 추가하거나 제거하여 태그 내부에 있는지 추적함. 이를 통해 태그 모드를 전환함.
2. **문자열 조작**:
    - 태그 내부와 외부를 구분하여, 태그 외부에 있는 단어를 뒤집어 결과 리스트에 저장함.
    - 리스트 results를 사용하여 중간 결과를 저장하고, 마지막에 모든 요소를 합쳐 최종 결과 문자열을 만듦.
3. **조건 분기 처리**:
    - tag_mode가 True일 때와 False일 때를 구분하여, 각각의 경우에 맞는 처리를 수행함. 이는 태그 내부 문자열과 태그 외부의 단어를 올바르게 처리하기 위해 필요함.

---

# code

## Python

```python
stack = []

def main():
    answer = ReverseWord().reverse_word(input())
    print(answer)

# 단어 뒤집기 클래스
class ReverseWord:
    # 단어 뒤집기 메서드
    def reverse_word(self, input):
        words = []
        results = []
        tag_mode = False
        for char in input:
            tag_mode = ReverseWord().tag_switch(char)
            if tag_mode == True:  # tag_mode == True일 때
                if words:  # words에 단어가 있으면 뒤집어서 results에 추가
                    results.append(''.join(reversed(words)))
                    words = []
                results.append(char)  # words에 단어가 없으면 바로 results에 추가
                
            else:  #tag_mode == False일 때
                if char == ">":
                    results.append(char)
                else: 
                    words.append(char)
                    if char == " ":
                        results.append(''.join(reversed(words[:-1])))
                        results.append(" ")
                        words = []  #words 초기화
        
        if words:  #순환을 마친 후 words안에 단어가 있을 경우 뒤집어서 results에 추가
            results.append(''.join(reversed(words)))
        
        return ''.join(results) #results의 모든 요소 합쳐서 반환
    
    # tag_mode on/off 스위치
    def tag_switch(self, char):
        if char == "<":
            stack.append(char)
        elif char == ">":
            stack.pop()
    
        if len(stack) == 0:
            return False
        else:
            return True

if __name__ == '__main__':
    main()
```
