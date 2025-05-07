import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static StringBuilder sb = new StringBuilder();;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        backtracking(1, new ArrayList<>());
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void backtracking(int start, List<Integer> list) {
        if (list.size() == M) {
            for (int i : list) {
                sb.append(i).append(" ");
            }
            sb.append('\n');
            return;
        }

        for (int i = start; i <= N; i++) {
            list.add(i);
            backtracking(i + 1, list);
            list.remove(list.size() - 1);
        }
    }
}
