import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        Set<String> unheard = new HashSet<>();
        List<String> unseenUnHeard = new ArrayList<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            unheard.add(br.readLine());
        }

        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            if (unheard.contains(name)) {
                unseenUnHeard.add(name);
            }
        }
        unseenUnHeard.sort(Comparator.naturalOrder());

        sb.append(unseenUnHeard.size() + "\n");
        for (String name : unseenUnHeard) {
            sb.append(name + "\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
