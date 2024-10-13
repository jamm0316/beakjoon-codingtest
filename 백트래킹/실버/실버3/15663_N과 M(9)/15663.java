import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[] numbers = new int[N];
        
        for (int i = 0; i < N; i++) {
            numbers[i] = scanner.nextInt();
        }

        Arrays.sort(numbers);  // 입력된 숫자 정렬
        boolean[] visited = new boolean[N];
        List<Integer> sequence = new ArrayList<>();
        
        backtrack(N, M, sequence, numbers, visited);
        scanner.close();
    }

    public static void backtrack(int N, int M, List<Integer> sequence, int[] numbers, boolean[] visited) {
        if (sequence.size() == M) {
            for (int num : sequence) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }

        Integer lastUsed = null;
        for (int i = 0; i < N; i++) {
            if (!visited[i] && (lastUsed == null || numbers[i] != lastUsed)) {
                sequence.add(numbers[i]);
                visited[i] = true;
                lastUsed = numbers[i];

                backtrack(N, M, sequence, numbers, visited);

                visited[i] = false;
                sequence.remove(sequence.size() - 1);
            }
        }
    }
}
