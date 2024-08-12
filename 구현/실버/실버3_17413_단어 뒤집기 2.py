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
