import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<Integer> budget = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            budget.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        int totalBudget = Integer.parseInt(st.nextToken());

        budget.sort(Comparator.naturalOrder());

        int limit = upperBudgetLimit(budget, totalBudget);
        System.out.println(limit);
    }

    private static int upperBudgetLimit(List<Integer> budget, int totalBudget) {
        int left = 0;
        int right = budget.get(budget.size() - 1);
        int maxLimit = 0;

        while (left <= right) {
            int limit = (left + right) / 2;

            if (canDistributeBudget(budget, limit, totalBudget)) {
                maxLimit = limit;
                left = limit + 1;
            } else {
                right = limit - 1;
            }
        }
        return maxLimit;
    }

    private static boolean canDistributeBudget(List<Integer> budget, int limit, int totalBudget) {
        int sum = 0;
        for (int b : budget) {
            sum += Math.min(b, limit);
        }

        return sum <= totalBudget;
    }
}
