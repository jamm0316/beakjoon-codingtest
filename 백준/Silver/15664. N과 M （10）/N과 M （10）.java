import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);

        backtrack(0, new ArrayList<>());

        System.out.println(sb);
    }

    private static void backtrack(int start, List<Integer> sequence) {
        if (sequence.size() == M) {
            sequence.forEach(i -> sb.append(i).append(" "));
            sb.append('\n');
        }

        int prev = -1;

        for (int i = start; i < N; i++) {
            if (arr[i] == prev) continue;
            prev = arr[i];
            sequence.add(arr[i]);
            backtrack(i + 1, sequence);
            sequence.remove(sequence.size() - 1);
        }
    }
}
