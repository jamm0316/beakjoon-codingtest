import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer> sequence;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
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

        sequenceDfs(new ArrayList<>(), 0);

        bw.write(sb.toString());
        bw.close();
        br.close();

    }

    private static void sequenceDfs(List<Integer> result, int startIdx) {
        if (result.size() == M) {
            for (int num : result) {
                sb.append(num).append(" ");
            }
            sb.append('\n');
            return;
        }
        for (int i = startIdx; i < sequence.size(); i++) {
            result.add(sequence.get(i));
            sequenceDfs(result, i);  //현재 인덱스를 넘겨서 비내림차순 유지
            result.remove(result.size() - 1);
        }
    }
}
