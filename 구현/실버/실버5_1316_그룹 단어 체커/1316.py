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
