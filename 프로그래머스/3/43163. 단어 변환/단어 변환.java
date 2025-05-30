import java.util.*;

class Solution {
    static class ConvertedWord {
        String word;
        int converedCount;
        
        ConvertedWord (String word, int converedCount) {
            this.word = word;
            this.converedCount = converedCount;
        }
        @Override
        public String toString() {
            return "ConvertedWord{"+word+","+converedCount+"}";
         }
    }
    
    static int N;
    static List<String> words;
    static boolean[] visited;
    public int solution(String begin, String target, String[] input) {
        N = input.length;
        words = new ArrayList<>();
        visited = new boolean[N];
        for (String s : input) {
            words.add(s);
        }
        
        //target이 없을 경우
        if (!words.contains(target)) {
            return 0;
        }
        
        int result = bfs(begin, target);
        return result;
    }
    
    private static int bfs(String word, String target) {
        Queue<ConvertedWord> queue = new LinkedList<>();
        queue.offer(new ConvertedWord(word, 0));
        int wordLength = word.length();
        
        while (!queue.isEmpty()) {
            ConvertedWord now = queue.poll();
            if (now.word.equals(target)) {
                return now.converedCount;
            }
            
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    int count = 0;
                    for (int j = 0; j < wordLength; j++) {
                        if (words.get(i).charAt(j) == now.word.charAt(j)) {
                            count++;
                        }
                    }
                    if (count == wordLength - 1) {
                        queue.offer(new ConvertedWord(words.get(i), now.converedCount + 1));
                        visited[i] = true;
                    }
                }
            }
        }
        
        return 0;
    }
}