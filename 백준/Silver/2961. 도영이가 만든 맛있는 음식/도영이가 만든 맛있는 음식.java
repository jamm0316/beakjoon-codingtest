import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int queries = 1 << N;
        List<int[]> taste = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] query = br.readLine().split(" ");

            taste.add(Arrays.stream(query)
                    .mapToInt(Integer::valueOf)
                    .toArray());
        }


        int minValue = Integer.MAX_VALUE;

        for (int i = 1; i < queries; i++) {
            int S = 1;
            int B = 0;

            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {

                    S *= taste.get(j)[0];
                    B += taste.get(j)[1];
                }
            }
            minValue = Math.min(minValue, Math.abs(S - B));
        }

        System.out.println(minValue);
    }
}
