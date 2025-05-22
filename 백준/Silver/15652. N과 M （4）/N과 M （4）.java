import java.io.*;

public class Main {
    static int N, M;
    static StringBuilder sb = new StringBuilder();
    static int[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        selected = new int[M];

        dfs(0, 1);
        System.out.print(sb);
    }

    private static void dfs(int depth, int start) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = start; i <= N; i++) {
            selected[depth] = i;
            dfs(depth + 1, i);
        }
    }
}