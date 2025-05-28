/*
1. Set 자료구조를 이용
2. Queue<Node> 이용
3. Turn {startWord, player, word}
2. 탈락인 조건 설정
  - set.contains인 경우
  - 단어가 1글자인 경우
  - 끝말이 안이어지는 경우
*/
import java.util.*;

class Solution {
    
    static Set<String> history = new HashSet<>();
    
    public int[] solution(int n, String[] words) {
        int[] result = bfs(n, words);
        return result;
    }
    
    static private int[] bfs(int n, String[] words) {
        int player = 1;
        int turn = 1;
        char prevEnd = words[0].toCharArray()[0];
        
        for (String now : words) {
            if (now.length() == 1) {
                return new int[]{player, turn};
            }
            
            if (now.toCharArray()[0] != prevEnd) {
                return new int[]{player, turn};
            }
            
            if (history.contains(now)) {
                return new int[]{player, turn};
            }
            
            //정보 갱신
            if (player == n) {
                turn++;
            }
            player = player < n ? player + 1 : 1;
            prevEnd = now.toCharArray()[now.length() - 1];
            history.add(now);
        }
        
        return new int[]{0, 0};
    }
}