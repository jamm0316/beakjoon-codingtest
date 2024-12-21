import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        List<Integer> trees = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees.add(Integer.parseInt(st.nextToken()));
        }

        trees.sort(Comparator.naturalOrder());

        int result = findMaxHeight(trees, target);
        System.out.println(result);
    }

    private static int findMaxHeight(List<Integer> trees, int target) {
        int left = 0;
        int right = trees.get(trees.size() - 1);
        int maxHeight = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (sumCutting(trees, mid, target)) {
                maxHeight = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return maxHeight;
    }

    private static boolean sumCutting(List<Integer> trees, int maxHeight, int target) {
        long sumCutting = 0;
        for (int height : trees) {
            if (height > maxHeight) {
                sumCutting += height - maxHeight;
                if (sumCutting >= target) {
                    return true;
                }
            }
        }
        return false;
    }
}
