import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer> sequence;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Set<Integer> set = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        sequence = new ArrayList<>(set);
        sequence.sort(Comparator.naturalOrder());

        backtrackDfs(new ArrayList<>(), 0);

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void backtrackDfs(List<Integer> result, int startIdx) {
        if (result.size() == M) {
            for (int num : result) {
                sb.append(num).append(" ");
            }
            sb.append('\n');
            return;
        }

        for (int i = startIdx; i < sequence.size(); i++) {
            result.add(sequence.get(i));
            backtrackDfs(result, i);
            result.remove(result.size() - 1);
        }
    }
}
