import sys

def main():
    #입력값 파싱
    input = sys.stdin.read()
    data = input.split()
    
    N = int(data[0])  # 쿼리 갯수
    queries = data[1:]  # 쿼리들

    # 그룹 단어 갯수 세기
    count_group_word = GroupWordChecker().count_word(queries)
    print(count_group_word)

class GroupWordChecker:
    # mothod. 그룹 단어인지 체크 // return boolean
    def check_word(self, query):
        seen = set()
        previous_word = None
        for word in query:
            if word != previous_word:
                if word in seen:
                    return False
            seen.add(word)
            previous_word = word
        return True

    # mothod. 그룹 갯수 체크 // return int
    def count_word(self, queries):
        total_count = 0
        for query in queries:
            check_group_word = GroupWordChecker().check_word(query)
            if check_group_word:
               total_count += 1
        return total_count

if __name__ == '__main__':
    main()
