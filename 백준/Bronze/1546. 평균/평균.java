import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] sequence = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        double maxScore = Arrays.stream(sequence).max().getAsInt();

        double totalScore = 0;
        for (int eachScore : sequence) {
            double editScore = ((double) eachScore * 100 / (double) maxScore * 100) / 100;
            totalScore += editScore;
        }

        double average = totalScore / N;
        System.out.println(average);
    }
}
