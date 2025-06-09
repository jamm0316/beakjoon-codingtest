import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<List<Integer>> tree = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                tree.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = N - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                int cur = tree.get(i).get(j);
                int belowLeft = tree.get(i + 1).get(j);
                int belowRight = tree.get(i + 1).get(j + 1);
                tree.get(i).set(j, cur + Math.max(belowLeft, belowRight));
            }
        }
        System.out.println(tree.get(0).get(0));
    }
}
