import java.io.*;
import java.util.*;

public class Main {
    static int T, N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            int[] build = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) build[i] = Integer.parseInt(st.nextToken());

            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
            int[] indeg = new int[N + 1];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                adj.get(X).add(Y);
                indeg[Y]++;
            }

            int target = Integer.parseInt(br.readLine());

            //위상 정렬
            int[] dp = new int[N + 1];
            Queue<Integer> queue = new LinkedList<>();

            for (int i = 1; i <= N; i++) {
                if (indeg[i] == 0) {
                    dp[i] = build[i];  //선행 없는 건물은 자기 시간으로 완공
                    queue.offer(i);
                }
            }

            while (!queue.isEmpty()) {
                int x = queue.poll();
                for (int y : adj.get(x)) {
                    dp[y] = Math.max(dp[y], dp[x] + build[y]);
                    if (--indeg[y] == 0) queue.offer(y);
                }
            }

            sb.append(dp[target]).append('\n');
        }
        System.out.println(sb);
    }
}
