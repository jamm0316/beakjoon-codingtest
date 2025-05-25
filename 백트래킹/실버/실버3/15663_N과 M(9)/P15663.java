import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    static int N, M;
    static boolean[] visited;
    static List<Integer> arr;
    static Set<String> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        arr.sort(Comparator.naturalOrder());

        dfs(new ArrayList<>());

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(List<Integer> list) {
        if (list.size() == M) {
            String seq = list.stream().map(String::valueOf).collect(Collectors.joining(" "));
            if (!set.contains(seq)) {
                set.add(seq);
                sb.append(seq).append('\n');
            }
        }

        for (int i = 0; i < arr.size(); i++) {
            if (!visited[i]){
                visited[i] = true;
                list.add(arr.get(i));

                dfs(list);

                visited[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }
}
