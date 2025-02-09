import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<List<Integer>> map = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            map.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                int curNum = Integer.parseInt(st.nextToken());
                map.get(i).add(curNum);
                if (i > 0) {
                    int left = (j > 0) ? map.get(i - 1).get(j - 1) : 0;
                    int right = (j < i) ? map.get(i - 1).get(j) : 0;
                    map.get(i).set(j, Math.max(left, right) + curNum);
                }
            }
        }
        int maxSum = Collections.max(map.get(N - 1));

        bw.write(String.valueOf(maxSum));
        bw.close();
        br.close();
    }
}
