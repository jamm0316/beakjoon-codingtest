import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        if (!Arrays.asList(words).contains(target)) return 0;
        
        Queue<WordNode> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.offer(new WordNode(begin, 0));
        visited.add(begin);
        
        while (!queue.isEmpty()) {
            WordNode current = queue.poll();
            
            if (current.word.equals(target)) return current.step;
            
            for (String word : words) {
                if (!visited.contains(word) && canTransform(current.word, word)) { 
                    queue.offer(new WordNode(word, current.step + 1));
                    visited.add(word);
                    }
            }
        }
        return 0;
    }
    
    private boolean canTransform(String word1, String word2) {
        int diff = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) diff++;
            if (diff > 1) return false;
        }
        return diff == 1;
    }
    
    class WordNode {
        String word;
        int step;
        
        WordNode(String word, int step) {
            this.word = word;
            this.step = step;
        }
    }
}