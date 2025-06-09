import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }
        tree.get(0).add(Integer.parseInt(br.readLine()));

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = 0;
            while (st.hasMoreTokens()) {
                int cur = Integer.parseInt(st.nextToken());
                int prev = 0;
                if (i == 1) {
                    prev = tree.get(0).get(0);
                    tree.get(1).add(cur + prev);
                } else {
                    if (idx == 0) {
                        prev = tree.get(i - 1).get(idx);
                        tree.get(i).add(cur + prev);
                    } else if (idx == i) {
                        prev = tree.get(i - 1).get(idx - 1);
                        tree.get(i).add(cur + prev);
                    } else {
                        prev = Math.max(tree.get(i - 1).get(idx - 1), tree.get(i - 1).get(idx));
                        tree.get(i).add(cur + prev);
                    }
                    idx++;
                }
            }
        }

        Integer i = tree.get(N - 1).stream().max(Comparator.naturalOrder()).get();
        System.out.println(i);
    }
}
