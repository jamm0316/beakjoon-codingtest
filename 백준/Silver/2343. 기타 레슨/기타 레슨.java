import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int bluRay = Integer.parseInt(st.nextToken());
        int[] videos = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int result = findMinLength(N, bluRay, videos);
        System.out.println(result);
    }

    private static int findMinLength(int N, int bluRay, int[] videos) {
        int max = Arrays.stream(videos).max().getAsInt();
        int sum = Arrays.stream(videos).sum();
        int minLength = sum;

        int start = max;
        int end = sum;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (isPossible(videos, bluRay, mid)) {
                minLength = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return minLength;
    }

    private static boolean isPossible(int[] videos, int bluRay, int size) {
        int count = 1;
        int sum = 0;

        for (int lesson : videos) {
            if (sum + lesson > size) {
                count++;
                sum = lesson;
            } else {
                sum += lesson;
            }
        }
        return count <= bluRay;
    }
}
