import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    public static void main(String[] args) throws IOException {
        /**
         * 산술평균: sum(N) / N
         * 중앙값: N 중 중앙값
         * 최빈값: N 중 많이 나타나는 값
         * 범위: N 중 최대 - 최소
        */

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int[] sequence = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sequence[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sequence);

        writeBuffer(findAverage(sequence));
        writeBuffer(findMid(sequence));
        writeBuffer(findPopular(sequence));
        writeBuffer(findRange(sequence));

        bw.close();
        br.close();

    }
    private static int findRange(int[] sequence) {
        return sequence[sequence.length - 1] - sequence[0];
    }
    private static int findPopular(int[] sequence) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : sequence) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> keySet = new ArrayList<>(map.keySet());
        keySet.sort((a, b) -> {
            int compareNum = map.get(b) - map.get(a);
            if (compareNum == 0) {
                return a - b;
            }
            return compareNum;
        });

        if (keySet.size() > 1 && map.get(keySet.get(0)).equals(map.get(keySet.get(1)))) {
            return keySet.get(1);
        } else {
            return keySet.get(0);
        }
    }
    private static int findMid(int[] sequence) {
        int mid = N / 2;
        return sequence[mid];
    }
    private static int findAverage(int[] sequence) {
        int sum = Arrays.stream(sequence).sum();
        double avg = (double) sum / N;
        return (int) Math.round(avg);

    }
    private static void writeBuffer(int x) throws IOException {
        bw.write(String.valueOf(x));
        bw.newLine();
    }
}
