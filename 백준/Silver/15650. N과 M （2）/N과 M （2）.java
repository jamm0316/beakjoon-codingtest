import java.io.*;
import java.util.*;
/**
 *  구하고자 하는 값: 사전 순으로 증가하는 수열
 *
 *  풀이 전략
 *  1. 입력값 받기: N, M
 *  2. DFS(백트래킹) 사용
 *      - list의 size가 M이면 return
 *      - return 되고나면 백트래킹(list.remove())
 */

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
