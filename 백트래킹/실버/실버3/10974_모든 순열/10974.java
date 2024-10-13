import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        ArrayList<Integer> sequence = new ArrayList<Integer>();
        boolean[] visited = new boolean[N];

        backtrack(N, sequence, visited);
    }

    private static void backtrack(int N, ArrayList<Integer> sequence, boolean[] visited) {
        if (sequence.size() == N) {
            printSequence(sequence);
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                sequence.add(i + 1);
                visited[i] = true;
                backtrack(N, sequence, visited);

                sequence.remove(sequence.size() - 1);
                visited[i] = false;
            }
        }
    }
    
    private static void printSequence(ArrayList<Integer> sequence) {
        String result = sequence.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(" "));
        System.out.println(result);
    }
}
