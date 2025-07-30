import java.io.*;
import java.util.*;

public class Main {

    static int N, d, k, c;
    static int[] sushi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 접시 수
        d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
        k = Integer.parseInt(st.nextToken()); // 연속 먹을 접시 수
        c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        sushi = new int[N];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        Deque<Integer> window = new ArrayDeque<>();
        Map<Integer, Integer> countMap = new HashMap<>();

        int max = 0;

        for (int i = 0; i < k; i++) {
            window.offerLast(sushi[i]);
            countMap.put(sushi[i], countMap.getOrDefault(sushi[i], 0) + 1);
        }

        max = countMap.containsKey(c) ? countMap.size() : countMap.size() + 1;

        for (int i = 1; i < N; i++) {
            int out = window.poll();
            countMap.put(out, countMap.get(out) - 1);
            if (countMap.get(out) == 0) {
                countMap.remove(out);
            }

            int in = sushi[(i + k - 1) % N];
            window.offerLast(in);
            countMap.put(in, countMap.getOrDefault(in, 0) + 1);

            int current = countMap.containsKey(c) ? countMap.size() : countMap.size() + 1;
            max = Math.max(max, current);
        }

        System.out.println(max);
    }
}
