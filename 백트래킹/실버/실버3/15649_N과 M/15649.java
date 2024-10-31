import java.util.Scanner;

public class Backtracking {
    
    public static void backtrack(int N, int M, java.util.List<Integer> sequence, boolean[] visited) {
        if (sequence.size() == M) {
            for (int num : sequence) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }
        
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sequence.add(i);
                
                backtrack(N, M, sequence, visited);
                
                sequence.remove(sequence.size() - 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        
        boolean[] visited = new boolean[N + 1];
        backtrack(N, M, new java.util.ArrayList<>(), visited);
        
        scanner.close();
    }
}
